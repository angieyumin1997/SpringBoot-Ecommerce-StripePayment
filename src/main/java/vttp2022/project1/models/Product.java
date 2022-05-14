package vttp2022.project1.models;

import java.sql.SQLException;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class Product {
    
    private Integer prod_id;
    private byte[] image;
    private String name;
    private String description;
    private Double price;
    private Integer category_id;
    private String category;

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Integer getProd_id() {
        return prod_id;
    }
    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public static Product convert(SqlRowSet rs)  throws SQLException{
        Product product = new Product();
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setCategory_id(rs.getInt("category_id"));
        product.setProd_id(rs.getInt("prod_id"));

        return product;
    }

}
