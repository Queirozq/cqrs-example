package com.queirozq.ProductService.query.api.projection;

import com.queirozq.ProductService.command.api.data.Product;
import com.queirozq.ProductService.command.api.data.ProductRepository;
import com.queirozq.ProductService.command.api.model.ProductRestModel;
import com.queirozq.ProductService.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
          List<Product> products = productRepository.findAll();

          List<ProductRestModel> productRestModels = products.stream().map(x -> ProductRestModel.builder()
                  .price(x.getPrice())
                  .name(x.getName())
                  .quantity(x.getQuantity())
                  .build()).collect(Collectors.toList());
          return productRestModels;
    }
}
