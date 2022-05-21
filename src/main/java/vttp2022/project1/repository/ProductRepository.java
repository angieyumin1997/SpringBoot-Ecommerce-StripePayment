package vttp2022.project1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.project1.models.Product;

@Repository
public class ProductRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public boolean insertProduct(Product product){
        
        int count = template.update(SQL_INSERT_PRODUCT,
        product.getImage(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getCategory_id());

        return count == 1;
    }

    public Integer selectCategoryID(String category){
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_CATEGORYID,category);
        rs.next();
        Integer categoryID = rs.getInt("category_id");
        return categoryID;
    }

    public String selectProductCategory(Integer id){
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_PRODUCT_CATEGORY,id);
        rs.next();
        String categoryName = rs.getString("category_name");
        return categoryName;
    }
    
    public List<Product> selectAllProducts() throws SQLException{
        List <Product> products = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_PRODUCT);
        while(rs.next()){
            Product product = Product.convert(rs);
            String categoryName = selectProductCategory(product.getCategory_id());
            product.setCategory(categoryName);
            products.add(product);
        }

        return products;
    }

    public boolean deleteProduct(Integer prod_id){
        int count = template.update(SQL_DELETE_PRODUCT,
        prod_id);

        return count == 1;
    }

    public Product selectProduct(Integer prod_id) throws SQLException{
        Product product = new Product();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_PRODUCT,prod_id);
        rs.next();
        product = Product.convert(rs);
        return product;
         
    }

    public boolean updateProduct(Product product){
       
        int count = template.update(SQL_UPDATE_PRODUCT,
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getCategory_id(),
        product.getImage(),
        product.getProd_id());

        return count == 1;
    }

    public List <Product> selectProductImage() throws SQLException{
        List <Product> products = new LinkedList<>();
        return template.query(
            SQL_SELECT_ALL_IMAGES,
            (ResultSet rs)->{
                while(rs.next()){
                final Product product = Product.populateImage(rs);
                products.add(product);}
                return products;
            }
            
        );
         
    }

    public Product selectOneProductImage(Integer prod_id) throws SQLException{
 
        return template.query(
            SQL_SELECT_AN_IMAGE,
            (ResultSet rs)->{
                rs.next();
                final Product product = Product.populateImage(rs);
          
                return product;
            },prod_id
            
        );
         
    }

    public List<Product> searchProductsByNameAndCategory(String name,Integer category_id) throws SQLException{
        List <Product> products = new LinkedList<>();
        return template.query(
            SQL_SEARCH_PRODUCT_BY_NAME_AND_CATEGORY,
            (ResultSet rs)->{
                while(rs.next()){
                final Product product = Product.populateImage(rs);
                String categoryName = selectProductCategory(product.getCategory_id());
                product.setCategory(categoryName);
                products.add(product);}
                return products;
            },name,category_id

        );
    }

    public List<Product> searchProductsByName(String name) throws SQLException{
        List <Product> products = new LinkedList<>();
        return template.query(
            SQL_SEARCH_PRODUCT_BY_NAME,
            (ResultSet rs)->{
                while(rs.next()){
                final Product product = Product.populateImage(rs);
                String categoryName = selectProductCategory(product.getCategory_id());
                product.setCategory(categoryName);
                products.add(product);}
                return products;
            },name

        );

    }


    
}
