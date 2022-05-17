package vttp2022.project1.service;

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


}
