package vttp2022.project1.repository;

public interface Queries {

    public static final String SQL_INSERT_CATEGORY = 
    "insert into category(category_name,description) value (?,?)";

    public static final String SQL_SELECT_ALL_CATEGORY =
    "select category_id,category_name,description from category order by category_name";

    public static final String SQL_DELETE_CATEGORY =
    "delete from category where category_id=?";

    public static final String SQL_UPDATE_CATEGORY = 
    "update category set category_name = ?, description = ? where category_id = ?";

    public static final String SQL_SELECT_CATEGORY = 
    "select category_id,category_name,description from category where category_id = ?";

    public static final String SQL_INSERT_PRODUCT =
    "insert into products(image,name,description,price,category_id) value (?,?,?,?,?)";
   
    public static final String SQL_SELECT_CATEGORYID =
    "select category_id from category where category_name=?";

    public static final String SQL_SELECT_PRODUCT_CATEGORY =
    "select category_name from category where category_id=?";

    public static final String SQL_SELECT_ALL_PRODUCT =
    "select prod_id,name,description,price,category_id from products order by name";

    public static final String SQL_DELETE_PRODUCT =
    "delete from products where prod_id=?";

    public static final String SQL_SELECT_PRODUCT=
    "select prod_id,name,description,price,category_id from products where prod_id=?";

    public static final String SQL_UPDATE_PRODUCT=
    "update products set name=?,description=?,price=?,category_id=?,image=? where prod_id=?";

}
