package vttp2022.project1.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Cart {

    private Integer cart_id;
    private String payment_status;
    private Double price;
    private Integer quantity;
    private String size;
    private Integer prod_id;
    private String username;
    private Integer order_id;
    private String prod_name;
    
    public Integer getCart_id() {
        return cart_id;
    }
    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }
    public String getPayment_status() {
        return payment_status;
    }
    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getProd_id() {
        return prod_id;
    }
    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public static Cart convert(SqlRowSet rs){
        Cart cart = new Cart();
        cart.setPrice(rs.getDouble("price"));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setProd_name(rs.getString("prod_name"));
        cart.setSize(rs.getString("size"));
        cart.setCart_id(rs.getInt("cart_id"));
        return cart;
    }




}
