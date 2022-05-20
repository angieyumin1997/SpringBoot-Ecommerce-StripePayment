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
            ps.setDouble(1, order.getTotal_amount());
            ps.setDate(2, order.getOrder_date());
            ps.setString(3, order.getUsername());
            return ps;
        }, keyholder);

        BigInteger bigint = (BigInteger) keyholder.getKey();
        return bigint.intValue();
    }

    public boolean updateOrderCartItem(Integer cartId,Integer orderId){
       
        int count = template.update(SQL_UPDATE_ORDER_CART_ITEMS,
        orderId,cartId);

        return count == 1;
    }

    public boolean updateOrderPayment(Order order){
       
        int count = template.update(SQL_UPDATE_ORDER_PAYMENT,
        order.getPayment_intent(),
        order.getPayment_intent_client_secret(),
        order.getOrder_id());

        return count == 1;
    }




}
