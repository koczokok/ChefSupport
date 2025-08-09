package com.chefsupport.api.web;

import com.chefsupport.api.service.RecipeService;
import com.chefsupport.api.web.dto.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  RecipeService service;

  @Test
  void list_empty_ok() throws Exception {
    Mockito.when(service.list(null, null, null, null, 0, 20))
        .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 20), 0));

    mvc.perform(get("/api/recipes"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.items", hasSize(0)))
        .andExpect(jsonPath("$.page", is(0)))
        .andExpect(jsonPath("$.size", is(20)))
        .andExpect(jsonPath("$.total", is(0)));
  }

  @Test
  void create_then_get_update_delete_flow() throws Exception {
    UUID id = UUID.randomUUID();
    RecipeResponse created = new RecipeResponse(id, "Pasta", 2, 10, 15, List.of("dinner"), new BigDecimal("500.00"), List.of(), "Boil water");
    Mockito.when(service.create(Mockito.any())).thenReturn(created);
    Mockito.when(service.get(id)).thenReturn(created);
    Mockito.when(service.update(Mockito.eq(id), Mockito.any())).thenReturn(new RecipeResponse(id, "Pasta B", 3, 10, 20, List.of("dinner"), new BigDecimal("480.00"), List.of(), "Boil water"));

    // create
    mvc.perform(post("/api/recipes")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Pasta\",\"servings\":2}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(id.toString())))
        .andExpect(jsonPath("$.name", is("Pasta")));

    // get
    mvc.perform(get("/api/recipes/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(id.toString())))
        .andExpect(jsonPath("$.name", is("Pasta")));

    // update
    mvc.perform(put("/api/recipes/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Pasta B\",\"servings\":3}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Pasta B")))
        .andExpect(jsonPath("$.servings", is(3)));

    // delete
    mvc.perform(delete("/api/recipes/" + id))
        .andExpect(status().isNoContent());
  }
}

