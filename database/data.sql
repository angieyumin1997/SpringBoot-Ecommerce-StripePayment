use ecommerce;

insert into users (username,password,role,customer_name,customer_address,customer_number)
    values ('yumin','yumin','ROLE_USER','yumin','singapore','1234567');

insert into category(category_id,category_name,description)
    value (1,'Bra','Sports bra');

insert into products(prod_id,name,description,price,category_id)
    values(1,'Enchanted sports bra','Good for training',66,'1');

insert into cart(cart_id,price,quantity,prod_name,prod_id,username,size)
    values(1,132,2,'Enchanted sports bra',1,'yumin','s');

insert into orders(order_id,shipping_address,total_amount,order_date,username,size)
    values(1,'singapore',132,'2022-04-30','yumin','s');

update cart set order_id=1 where cart_id=1;

