package vttp2022.project1.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Category {
    
    private Integer category_id;
    private String category_name;
    private String description;

    public Integer getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public static Category convert(SqlRowSet rs){
        Category category = new Category();
        category.setCategory_id(rs.getInt("category_id"));
        category.setCategory_name(rs.getString("category_name"));
        category.setDescription(rs.getString("description"));
        return category;
    }
}
