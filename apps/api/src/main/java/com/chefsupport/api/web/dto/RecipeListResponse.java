package com.chefsupport.api.web.dto;

import java.util.List;

public class RecipeListResponse {
  private List<RecipeSummary> items;
  private int page;
  private int size;
  private long total;

  public RecipeListResponse() {}

  public RecipeListResponse(List<RecipeSummary> items, int page, int size, long total) {
    this.items = items; this.page = page; this.size = size; this.total = total;
  }

  public List<RecipeSummary> getItems() { return items; }
  public void setItems(List<RecipeSummary> items) { this.items = items; }
  public int getPage() { return page; }
  public void setPage(int page) { this.page = page; }
  public int getSize() { return size; }
  public void setSize(int size) { this.size = size; }
  public long getTotal() { return total; }
  public void setTotal(long total) { this.total = total; }
}
