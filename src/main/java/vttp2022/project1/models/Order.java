package vttp2022.project1.models;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Order {

    private Integer order_id;
    private Double total_amount;
    private Date order_date;
    private String payment_intent;
    private String payment_intent_client_secret;
    private String username;

    public String getPayment_intent() {
        return payment_intent;
    }
    public void setPayment_intent(String payment_intent) {
        this.payment_intent = payment_intent;
    }
    public String getPayment_intent_client_secret() {
        return payment_intent_client_secret;
    }
    public void setPayment_intent_client_secret(String payment_intent_client_secret) {
        this.payment_intent_client_secret = payment_intent_client_secret;
    }
    
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public static Order convert(SqlRowSet rs){
        Order order = new Order();
        order.setOrder_id(rs.getInt("order_id"));
        order.setTotal_amount(rs.getDouble("total_amount"));
        order.setOrder_date(rs.getDate("order_date"));
        order.setUsername(rs.getString("username"));
        return order;
    }

}
