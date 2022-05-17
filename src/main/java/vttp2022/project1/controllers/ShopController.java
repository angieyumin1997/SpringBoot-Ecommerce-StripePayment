package vttp2022.project1.controllers;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.project1.models.Product;
import vttp2022.project1.models.Account;
import vttp2022.project1.models.Cart;
import vttp2022.project1.service.CartService;
import vttp2022.project1.service.ProductService;
import vttp2022.project1.service.ShopService;

@Controller
@RequestMapping
public class ShopController {
    
    @Autowired
    private ProductService productSvc;

    @Autowired
    private ShopService shopSvc;

    @Autowired
    private CartService cartSvc;
    
     
    @GetMapping(path="/shop")
    public ModelAndView Shop() throws SQLException{
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("shop");
        List<Product> products = productSvc.getAllProductImages();
        mvc.addObject("products",products);
        
        return mvc;
   
    }

    @GetMapping(path="/shop/product/{prod_id}")
    public ModelAndView Product(@PathVariable(name="prod_id") Integer prod_id) throws SQLException{
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("shopproduct");
        Product product = productSvc.getOneProductImage(prod_id);
        mvc.addObject("product",product);
        System.out.println(">>>>>> prod_id: " +product.getProd_id());
        
        return mvc;
   
    }

    @GetMapping(path="/register")
    public ModelAndView Register(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("register");
        return mvc;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path="/register/success")
    public ModelAndView registerSuccess(@RequestBody MultiValueMap<String, String> form){
        String username = form.getFirst("username");
        String password = form.getFirst("password");
        String bycrptPassword = passwordEncoder.encode(password);

        String name = form.getFirst("name");
        String address = form.getFirst("address");
        String number = form.getFirst("number");

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(bycrptPassword);
        account.setCustomer_name(name);
        account.setCustomer_address(address);
        account.setCustomer_number(number);

        shopSvc.addNewUser(account);
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("registersuccess");
        return mvc;
    }

    
    @GetMapping(path="/login")
    public ModelAndView login(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("login");
        return mvc;
    }

    @GetMapping(path="/cart")
    public ModelAndView cart(Cart cart){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        cart.setUsername(username);

        List <Cart> cartItems = new LinkedList<>();
        cartItems = cartSvc.getAllCartItems(cart);

        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("cart");
        mvc.addObject("cartItems",cartItems);
        
        return mvc;
    }


    @GetMapping(path="/addToCart")
    public ModelAndView addToCart(
        @RequestParam (name="size") String size, 
        @RequestParam (name="quantity") Double quantity,
        @RequestParam (name="prod_id") Integer prod_id) throws SQLException{

            Cart cart = new Cart();
    
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();

            Product product = productSvc.selectProduct(prod_id);
            Double price = product.getPrice();
            String prod_name = product.getName();

            cart.setSize(size);
            cart.setQuantity(quantity);
            cart.setUsername(username);
            cart.setProd_id(prod_id);
            cart.setPrice(price);
            cart.setProd_name(prod_name);

            cartSvc.addNewCartItem(cart);

            ModelAndView mvc = new ModelAndView();
            mvc.setViewName("addtocart");
            mvc.addObject("cart",cart);
        
            return mvc;

    }

    @GetMapping(path="cart/remove/{cart_id}")
    public ModelAndView deleteCartItem(@PathVariable(name="cart_id") Integer cart_id, Cart cart) throws SQLException{

        cartSvc.removeCartItem(cart_id);

        ModelAndView mvc = new ModelAndView();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        cart.setUsername(username);
        List <Cart> cartItems = new LinkedList<>();
        cartItems = cartSvc.getAllCartItems(cart);
        mvc.addObject("cartItems",cartItems);
        mvc.setViewName("cart");
        
        return mvc;
    }

    @GetMapping(path="cart/update/{cart_id}")
    public ModelAndView updateCartItem(@PathVariable(name="cart_id") Integer cart_id, Cart cart,@RequestParam (name="quantity") Double quantity) throws SQLException{

        cart.setCart_id(cart_id);
        cart.setQuantity(quantity);
        cartSvc.updateCartItem(cart);

        ModelAndView mvc = new ModelAndView();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        cart.setUsername(username);
        List <Cart> cartItems = new LinkedList<>();
        cartItems = cartSvc.getAllCartItems(cart);
        mvc.addObject("cartItems",cartItems);
        mvc.setViewName("cart");
        
        return mvc;
    }

}
