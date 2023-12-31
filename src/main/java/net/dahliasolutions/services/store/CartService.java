package net.dahliasolutions.services.store;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dahliasolutions.data.CartItemRepository;
import net.dahliasolutions.data.CartRepository;
import net.dahliasolutions.models.store.Cart;
import net.dahliasolutions.models.store.CartItem;
import net.dahliasolutions.models.user.User;
import net.dahliasolutions.services.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService implements CartServiceInterface {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    @Override
    public Cart createCart(BigInteger userId) {
        Cart cart = new Cart(userId, 0, new ArrayList<>());
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(BigInteger id) {
        Cart cart = cartRepository.findById(id).orElse(createCart(id));
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        for (CartItem item : cartItems){
            cart.getCartItems().add(item);
        }
        return cart;
    }

    @Override
    public Cart findByUsername(String userName) {
        Optional<User> user = userService.findByUsername(userName);
        Cart cart = cartRepository.findById(user.get().getId()).orElse(createCart(user.get().getId()));

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        for (CartItem item : cartItems){
            cart.getCartItems().add(item);
        }
        return cart;
    }

    @Override
    public Cart save(Cart cart) {
        for (CartItem item : cart.getCartItems()) {
            cartItemRepository.save(item);
        }
        return cartRepository.save(cart);
    }

    @Override
    public int getItemCount(BigInteger userId) {
        return cartRepository.findById(userId).orElse(createCart(userId)).getItemCount();
    }

    @Override
    public Cart emptyCart(BigInteger id) {
        Cart cart = findById(id);
        // loop through items and remove items
        for (CartItem item : cart.getCartItems()) {
            cartItemRepository.deleteById(item.getId());
        }
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
