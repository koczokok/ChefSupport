package com.chefsupport.api.repository;

import com.chefsupport.api.domain.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryIT {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.0")
      .withDatabaseName("chefsupport")
      .withUsername("chefsupport")
      .withPassword("chefsupport");

  @DynamicPropertySource
  static void registerProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  RecipeRepository repository;

  @Test
  void persists_and_reads_recipe() {
    Recipe r = new Recipe();
    r.setName("Test Recipe");
    r.setServings(2);
    repository.save(r);

    List<Recipe> all = repository.findAll();
    assertEquals(1, all.size());
    assertEquals("Test Recipe", all.get(0).getName());
  }
}

