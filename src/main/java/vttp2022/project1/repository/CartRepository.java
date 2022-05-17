package vttp2022.project1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.project1.models.Cart;

@Repository
public class CartRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public boolean insertCartItem(Cart cart){
        
        int count = template.update(SQL_INSERT_CART,
        cart.getPrice(),
        cart.getQuantity(),
        cart.getSize(),
        cart.getProd_id(),
        cart.getUsername(),
        cart.getProd_name());

        return count == 1;
    }

    
}
