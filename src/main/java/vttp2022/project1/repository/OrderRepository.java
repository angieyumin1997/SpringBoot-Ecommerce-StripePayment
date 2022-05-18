package vttp2022.project1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.project1.models.Cart;
import vttp2022.project1.models.Order;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class OrderRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public Integer insertOrder(final Order order){
        KeyHolder keyholder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ORDER, 
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getShipping_address());
            ps.setDouble(2, order.getTotal_amount());
            ps.setDate(3, order.getOrder_date());
            ps.setString(4, order.getUsername());
            return ps;
        }, keyholder);

        BigInteger bigint = (BigInteger) keyholder.getKey();
        return bigint.intValue();
    }

    public boolean updateOrderCartItem(Cart cart){
       
        int count = template.update(SQL_UPDATE_ORDER_CART_ITEMS,
        cart.getOrder_id(),
        cart.getUsername());

        return count == 1;
    }


}
