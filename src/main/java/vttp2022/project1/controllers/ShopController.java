package vttp2022.project1.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.project1.models.Product;
import vttp2022.project1.models.Account;
import vttp2022.project1.service.ProductService;
import vttp2022.project1.service.ShopService;

@Controller
@RequestMapping
public class ShopController {
    
    @Autowired
    private ProductService productSvc;

    @Autowired
    private ShopService shopSvc;
    
     
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
    public ModelAndView RegisterSuccess(@RequestBody MultiValueMap<String, String> form){
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
    public ModelAndView Login(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("login");
        return mvc;
    }

    @GetMapping(path="/cart")
    public ModelAndView Cart(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("cart");
        return mvc;
    }




}
