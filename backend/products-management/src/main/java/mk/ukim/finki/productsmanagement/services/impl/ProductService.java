package mk.ukim.finki.productsmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductCreationDTO;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductFilter;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.productsmanagement.domain.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Page<Product> findAllPaged(ProductFilter filter, Pageable pageable){
        return productRepository.findAllPaged(filter, pageable);
    }

    public Optional<Product> findById(ProductId id){
        Product product = productRepository.findById(id).isPresent() ?
                productRepository.findById(id).get() : null;

        return Optional.ofNullable(product);
    }

    public Product create(ProductCreationDTO productCreationDTO){
        Product product = new Product();
        product.setDateCreated(OffsetDateTime.now());

        return fillProperties(product, productCreationDTO);
    }

    public Product edit(ProductId id, ProductCreationDTO productCreationDTO){
        return fillProperties(this.findById(id).get(), productCreationDTO);
    }

    public Product fillProperties(Product product, ProductCreationDTO productCreationDTO){
        product.setDateModified(OffsetDateTime.now());
        product.setQuantity(productCreationDTO.getQuantity());
        product.setPrice(productCreationDTO.getPrice());
        product.setTitle(productCreationDTO.getTitle());
        product.setDescription(productCreationDTO.getDescription());
        product.setCategory(productCreationDTO.getCategory());

        if(productCreationDTO.getImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(productCreationDTO.getImage().split(",")[1]);
            product.setImage(imageBytes);
        }

        return productRepository.save(product);
    }

    public void delete(ProductId id){
        productRepository.deleteById(id);
    }
}
