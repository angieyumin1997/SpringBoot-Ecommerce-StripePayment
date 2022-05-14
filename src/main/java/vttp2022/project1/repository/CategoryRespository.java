package vttp2022.project1.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.project1.models.Category;

@Repository
public class CategoryRespository implements Queries{
    
    @Autowired
    private JdbcTemplate template;

    public boolean insertCategory(Category category){
        
        int count = template.update(SQL_INSERT_CATEGORY,
        category.getCategory_name(),
        category.getDescription());

        return count == 1;
    }

    public List<Category> selectAllCategories(){
        List <Category> categories = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CATEGORY);
        while(rs.next()){
            Category category = Category.convert(rs);
         
            categories.add(category);
        }

        return categories;
    }

    public boolean deleteCategory(int category_id){
        int count = template.update(SQL_DELETE_CATEGORY,
        category_id);

        return count == 1;
    }

    public boolean updateCategory(Category category){
       
        int count = template.update(SQL_UPDATE_CATEGORY,
        category.getCategory_name(),
        category.getDescription(),
        category.getCategory_id());

        return count == 1;
    }

    public Category selectCategory(int category_id){
        Category category = new Category();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_CATEGORY,category_id);
        rs.next();
        category = Category.convert(rs);
        return category;
         
    }
}
