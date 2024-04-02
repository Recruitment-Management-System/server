package com.interviewManagementApplication.RMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Map<String, Category> categoryMap;

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }

    @Data
    public static class Category {
        private int score;
        private int expectedCompetencyLevel;
        private String comment;
    }
}
