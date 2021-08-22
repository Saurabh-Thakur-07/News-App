package com.example.newsfetcher;

public class CategoryRVModal {
    private String category;
    private int categoryImageUrl ;

    public CategoryRVModal(String category, int categoryImageUrl) {
        this.category = category;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(int categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
