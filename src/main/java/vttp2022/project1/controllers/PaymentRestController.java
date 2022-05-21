package vttp2022.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.project1.models.Cart;
import vttp2022.project1.models.Order;
import vttp2022.project1.service.CartService;
import vttp2022.project1.service.OrderService;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
public class PaymentRestController {

    @Autowired
    private OrderService orderSvc;
  
    @Autowired
    private CartService cartSvc;

  static class CreatePayment {
    @SerializedName("items")
    Object[] items;

    public Object[] getItems() {
      return items;
    }
  }

  static class CreatePaymentResponse {
    private String clientSecret;
    public CreatePaymentResponse(String clientSecret) {
      this.clientSecret = clientSecret;
    }
  }

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment, Cart cart)throws StripeException{
      
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        cart.setUsername(username);
        double d = cartSvc.grandTotal(cart); 
        long l = (new Double(d)).longValue();

        long millis=System.currentTimeMillis();  
        Date date=new Date(millis);  

        Order order = new Order();
        order.setUsername(username);
        order.setOrder_date(date);
        order.setTotal_amount(d);
        String order_id = Integer.toString(orderSvc.addNewOrder(order));

        List <Integer> cartItemsIdList = new LinkedList<>();
        cartItemsIdList = cartSvc.selectAllCartItemsId(cart);
        String listString = cartItemsIdList.stream().map(Object::toString)
                        .collect(Collectors.joining(", "));

        PaymentIntentCreateParams params =
          PaymentIntentCreateParams.builder()
            .putMetadata("order_id", order_id)
            .setDescription(listString)
            .setAmount(l * 100L)
            .setCurrency("sgd")
            .setAutomaticPaymentMethods(
            PaymentIntentCreateParams.AutomaticPaymentMethods
              .builder()
              .setEnabled(true)
              .build()
          )
            .build();
          
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse payResp = new CreatePaymentResponse(paymentIntent.getClientSecret());

        return payResp;

      }
    
}
