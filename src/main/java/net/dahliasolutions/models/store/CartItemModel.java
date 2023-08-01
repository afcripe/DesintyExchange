package net.dahliasolutions.models.store;

import java.math.BigInteger;

public record CartItemModel(
        BigInteger id,
        BigInteger userId,
        String details,
        int count

) {
}
