package net.dahliasolutions.models.store;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @Column(name="id")
    private BigInteger id;  // same as user id, only one cart per user
    transient int itemCount;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<CartItem> cartItems;

    public int getItemCount() {
        int counter = 0;
        for (CartItem item : cartItems) {
            counter = counter+item.getCount();
        }
        return counter;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", itemCount=" + itemCount +
                '}';
    }
}
