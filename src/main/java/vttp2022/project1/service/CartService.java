package vttp2022.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project1.models.Cart;
import vttp2022.project1.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;

    public void addNewCartItem(Cart cart) {
        cartRepo.insertCartItem(cart);
    }

    public List<Cart> getAllCartItems(Cart cart){
        return cartRepo.selectAllCartItems(cart);
    }

    public void removeCartItem(int cart_id) {
        cartRepo.deleteCartItem(cart_id);
    }

    public void updateCartItem(Cart cart) {
        cartRepo.updateCartItem(cart);
    }

    public Double grandTotal(Cart cart){
        Double grandTotal = cartRepo.grandTotal(cart);
        return grandTotal;
    }


}
