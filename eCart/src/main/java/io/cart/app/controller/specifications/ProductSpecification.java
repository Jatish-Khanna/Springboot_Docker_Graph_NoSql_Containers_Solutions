package io.cart.app.controller.specification;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import io.cart.app.entity.ProductEntity;

@Component
public class ProductSpecification
    extends BaseSpecification<ProductEntity, MultiValueMap<String, String>> {
  
  private static final String PRODUCT_SPEC_KEY = "productName";
  private static final String DESCRIPTION_SPEC_KEY = "description";
  private static final String SELLER_NAME_SPEC_KEY = "sellerName";

  @Override
  public Specification<ProductEntity> getFilter(MultiValueMap<String, String> filterMap) {

    return (root, query, criteriaBuilder) -> {
      query.distinct(true);
      Specification<ProductEntity> productNameSpecifications = Specification.where(null);
      Specification<ProductEntity> finalSpecifications = Specification.where(null);

      if (filterMap.containsKey(PRODUCT_SPEC_KEY)) {
        productNameSpecifications = getSpecificationsFromList(PRODUCT_SPEC_KEY, filterMap.get(PRODUCT_SPEC_KEY));
        finalSpecifications = productNameSpecifications;
      }

      Specification<ProductEntity> productDescSpecifications = Specification.where(null);


      if (filterMap.containsKey(DESCRIPTION_SPEC_KEY)) {
        productDescSpecifications = getSpecificationsFromList(DESCRIPTION_SPEC_KEY, filterMap.get(DESCRIPTION_SPEC_KEY));
        finalSpecifications = finalSpecifications.and(productDescSpecifications);
      }

      Specification<ProductEntity> productSellerSpecification = Specification.where(null);

      if (filterMap.containsKey(SELLER_NAME_SPEC_KEY)) {
        productSellerSpecification = getSpecificationsFromList(SELLER_NAME_SPEC_KEY, filterMap.get(SELLER_NAME_SPEC_KEY));
        finalSpecifications = finalSpecifications.and(productSellerSpecification);
      }
      return finalSpecifications.toPredicate(root, query, criteriaBuilder);
    };
  }

  private Specification<ProductEntity> getSpecificationsFromList(String forFilter, List<String> specifications) {

    Specification<ProductEntity> collectedSpecifications = Specification.where(null);

    if (specifications == null) {
      return collectedSpecifications;
    }

    for (String specification : specifications) {
      collectedSpecifications = collectedSpecifications.or(textSpecificationContains(forFilter, specification));
    }
    return collectedSpecifications;
  }


//  private Specification<ProductEntity> productNameContains(String productName) {
//    return textSpecificationContains("productName", productName);
//  }
//
//  private Specification<ProductEntity> descriptionContains(String description) {
//    return textSpecificationContains("description", description);
//  }
//
//  private Specification<ProductEntity> sellerNameContains(String sellerName) {
//    return textSpecificationContains("sellerName", sellerName);
//  }

  public Specification<ProductEntity> discountGreaterThan(double discount) {
    return numericSpecificationCompare("discount", discount, ">");
  }
}
