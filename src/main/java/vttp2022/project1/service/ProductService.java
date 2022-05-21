package vttp2022.project1.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project1.models.Product;
import vttp2022.project1.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;


    public void addNewProduct(Product product){
        productRepo.insertProduct(product);
    }

    public Integer selectCategoryID(String category){
        Integer categoryId = productRepo.selectCategoryID(category);
        return categoryId;
    }

    public String selectProductCategory(Integer id){
        String categoryName = productRepo.selectProductCategory(id);
        return categoryName;
    }

    public List<Product> getAllProducts() throws SQLException{
        return productRepo.selectAllProducts();
    }

    public void deleteProduct(Integer prod_id) {
        productRepo.deleteProduct(prod_id);
    }

    public Product selectProduct(Integer prod_id) throws SQLException {
        Product product = productRepo.selectProduct(prod_id);
        return product;
    }

    public void updateProduct(Product product) {
        productRepo.updateProduct(product);
    }

    public List<Product> getAllProductImages( ) throws SQLException{
        return productRepo.selectProductImage();
    }

    public Product getOneProductImage(Integer prod_id) throws SQLException{
        return productRepo.selectOneProductImage(prod_id);
    }

    public List<Product> searchProductsByNameAndCategory(String name,Integer category_id) throws SQLException{
        return productRepo.searchProductsByNameAndCategory(name,category_id);
    }

    public List<Product> searchProductsByName(String name) throws SQLException{
        return productRepo.searchProductsByName(name);
    }

}
