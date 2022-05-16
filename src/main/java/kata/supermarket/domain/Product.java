package kata.supermarket.domain;

import java.util.Set;

public interface Product {
    /**
     * get the id of this product
     *
     * @return integer that uniquely identifies this product
     */
    Integer id();

    /**
     * what categories this product falls under, e.g. vegetable, fruit, bread, cheese, etc
     *
     * @return set of categories this product falls uder
     */
    Set<String> categories();
}
