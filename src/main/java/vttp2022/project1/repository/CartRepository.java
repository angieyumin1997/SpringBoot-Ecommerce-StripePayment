package vttp2022.project1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;

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

    public List<Cart> selectAllCartItems(Cart cart) {
        List <Cart> cartItems = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CART_ITEMS, cart.getUsername());
        while(rs.next()){
            Cart cartItem = Cart.convert(rs);
            cartItems.add(cartItem);
        }

        return cartItems;
    }

    public boolean deleteCartItem(int cart_id){
        int count = template.update(SQL_REMOVE_CART_ITEM,
        cart_id);

        return count == 1;
    }

    public boolean updateCartItem(Cart cart){
       
        int count = template.update(SQL_UPDATE_CART_ITEM,
        cart.getQuantity(),
        cart.getCart_id());

        return count == 1;
    }

    public Double grandTotal(Cart cart) {
        Double grandTotal = 0.0;
        SqlRowSet rs = template.queryForRowSet(SQL_GRAND_TOTAL, cart.getUsername());
        while(rs.next()){
            Double subTotal = rs.getDouble("subtotal");
            grandTotal += subTotal;
        }

        return grandTotal;
    }

}
