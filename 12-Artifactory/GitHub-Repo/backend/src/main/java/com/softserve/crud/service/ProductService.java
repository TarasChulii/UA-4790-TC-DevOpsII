package com.softserve.crud.service;

import lombok.RequiredArgsConstructor;
import org.artifactory.firstpack.UniqueSortAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.crud.exception.ProductNotFoundException;
import com.softserve.crud.model.entity.Product;
import com.softserve.crud.model.request.NewProduct;
import com.softserve.crud.model.request.PatchProduct;
import com.softserve.crud.model.response.ProductCreated;
import com.softserve.crud.model.response.ProductDto;
import com.softserve.crud.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {
    final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> getAll(String sortBy) {
        List<Product> products = productRepository.findAll();

        if (sortBy.equals("byPrice")) {
            Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
            UniqueSortAlgorithm.sortList(products, comparator);
        }

        return products.stream()
                .map(ProductDto::new)
                .toList();
    }

    public ProductCreated create(NewProduct newProduct) {
        Product p = Product.builder()
                .name(newProduct.name())
                .price(newProduct.price())
                .build();
        p = productRepository.save(p);
        return new ProductCreated(p.getId().toString());
    }

    @Transactional(readOnly = true)
    public Product findById(String id) {
        try {
            Long productId = Long.parseLong(id);
            return productRepository.findById(productId).get();
        } catch (NumberFormatException | NoSuchElementException ex) {
            // pass
        }
        throw new ProductNotFoundException();
    }

    public void patch(PatchProduct patchProduct, String id) {
        Product p = findById(id);

        String newName = patchProduct.name();
        if (hasText(newName)) {
            p.setName(newName);
        }

        BigDecimal newPrice = patchProduct.price();
        if (newPrice != null) {
            p.setPrice(newPrice);
        }
        // saved back on transaction closing
    }

    public void delete(String id) {
        Product p = findById(id);
        productRepository.delete(p);
    }
}
