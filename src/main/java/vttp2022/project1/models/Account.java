package vttp2022.project1.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Account {

    private String username;
    private String password;
    private String role;
    private String customer_name;
    private String customer_address;
    private String customer_number;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_address() {
        return customer_address;
    }
    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
    public String getCustomer_number() {
        return customer_number;
    }
    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public static Account convert(SqlRowSet rs){
        Account account = new Account();
        account.setRole(rs.getString("role"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        return account;
    }


    
}
