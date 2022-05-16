package vttp2022.project1.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.project1.models.Product;
import vttp2022.project1.service.ProductService;

@RestController
@RequestMapping(path="/shop/image")
public class ImageRestController {
    
    @Autowired
    private ProductService productSvc;

    @GetMapping(path="/{prod_id}")
    public ResponseEntity<byte[]> getPostImage(@PathVariable(name="prod_id") Integer prod_id) throws SQLException{

        Product product = productSvc.getOneProductImage(prod_id);
       
        return ResponseEntity.ok()
            .body(product.getImage());
    }
}
