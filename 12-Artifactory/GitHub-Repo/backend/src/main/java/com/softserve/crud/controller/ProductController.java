package com.softserve.crud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.softserve.crud.model.request.NewProduct;
import com.softserve.crud.model.request.PatchProduct;
import com.softserve.crud.model.response.ProductCreated;
import com.softserve.crud.model.response.ProductDeleted;
import com.softserve.crud.model.response.ProductDto;
import com.softserve.crud.model.response.ProductUpdated;
import com.softserve.crud.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;

    @GetMapping
    List<ProductDto> getAll(@RequestParam(value = "sort", required = false, defaultValue = "byId") String sortBy) {
        return productService.getAll(sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductCreated create(
            @Valid @RequestBody NewProduct newProduct
    ) {
        return productService.create(newProduct);
    }

    @GetMapping("/{id}")
    ProductDto getOne(
            @PathVariable String id
    ) {
        return new ProductDto(productService.findById(id));
    }

    @PatchMapping("/{id}")
    ProductUpdated update(
            @Valid @RequestBody PatchProduct patchProduct,
            @PathVariable String id
    ) {
        productService.patch(patchProduct, id);
        return new ProductUpdated();
    }

    @DeleteMapping("/{id}")
    ProductDeleted delete(
            @PathVariable String id
    ) {
        productService.delete(id);
        return new ProductDeleted();
    }
}
