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

    public static final String SQL_SELECT_CATEGORYNAME=
    "select category_name from category where category_id=?";

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

    public static final String SQL_SELECT_ALL_IMAGES=
    "select prod_id,name,description,price,category_id,image from products";

    public static final String SQL_SELECT_AN_IMAGE=
    "select prod_id,name,description,price,category_id,image from products where prod_id=?";

    public static final String SQL_INSERT_USER=
    "insert into users(username,password,customer_name,customer_address,customer_number) value (?,?,?,?,?)";

    public static final String SQL_AUTHETICATE_USER=
    "select username,password,role from users where username=?";

    public static final String SQL_INSERT_CART=
    "insert into cart(price,quantity,size,prod_id,username,prod_name) value (?,?,?,?,?,?)"; 

    public static final String SQL_SELECT_ALL_CART_ITEMS=
    "select cart_id,prod_name,size,quantity,price from cart where username=? and payment_status='np'";

    public static final String SQL_REMOVE_CART_ITEM=
    "delete from cart where cart_id=?";

    public static final String SQL_UPDATE_CART_ITEM=
    "update cart set quantity=? where cart_id=?";

    public static final String SQL_SELECT_ALL_CART_ITEMS_ID=
    "select cart_id from cart where username=? and payment_status='np'";

    public static final String SQL_GRAND_TOTAL=
    "select price * quantity as subtotal from cart where username=? and payment_status='np'";

    public static final String SQL_INSERT_ORDER=
    "insert into orders(total_amount,order_date,username) value (?,?,?)";

    public static final String SQL_UPDATE_ORDER_CART_ITEMS=
    "update cart set payment_status='p',order_id=? where cart_id=?";

    public static final String SQL_UPDATE_ORDER_PAYMENT=
    "update orders set payment_intent=?,payment_intent_client_secret=? where order_id=?";

    public static final String SQL_SEARCH_PRODUCT_BY_NAME_AND_CATEGORY=
    "select * from products where name like concat('%',?,'%') and category_id=?";

    public static final String SQL_SEARCH_PRODUCT_BY_NAME=
    "select * from products where name like concat('%',?,'%')";

    public static final String SQL_SELECT_USER_PAID_ORDERS=
    "select * from orders where username=? and payment_intent is not null and payment_intent_client_secret is not null;";

    public static final String SQL_SELECT_ALL_PAID_ORDERS=
    "select * from orders where payment_intent is not null and payment_intent_client_secret is not null";

    public static final String SQL_SELECT_USER_PAID_CART_ITEMS=
    "select * from cart where order_id=?";
}
