package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.ProductDto;
import ua.ithillel.lms.model.Product;
import ua.ithillel.lms.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ObjectMapper objectMapper;

  /**
   * Adds Product to database
   *
   * @param productDto DTO of Product adding
   * @return DTO of Product added
   */
  public ProductDto add(ProductDto productDto) {
    Product product = objectMapper.convertValue(productDto, Product.class);
    Product savedProduct = productRepository.save(product);
    productDto.setId(savedProduct.getId());
    return objectMapper.convertValue(savedProduct, ProductDto.class);
  }
}
