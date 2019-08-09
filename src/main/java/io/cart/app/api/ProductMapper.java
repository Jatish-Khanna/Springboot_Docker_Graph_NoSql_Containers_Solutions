package io.cart.app.api;

import java.util.Collection;
import org.mapstruct.Mapper;
import io.cart.app.entity.ProductEntity;
import io.cart.app.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  Product productEntityToProduct(ProductEntity productDao);

  Collection<Product> productEntityListToProductList(Collection<ProductEntity> products);
}
