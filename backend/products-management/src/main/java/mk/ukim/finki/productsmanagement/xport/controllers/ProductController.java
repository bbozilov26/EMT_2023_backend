package mk.ukim.finki.productsmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.productsmanagement.domain.converters.ProductConverter;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductCreationDTO;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductDTO;
import mk.ukim.finki.productsmanagement.domain.dtos.ProductFilter;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.productsmanagement.services.impl.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/all")
    public List<ProductDTO> findAll(){
        return productConverter.toProductDTOList(productService.findAll());
    }

    @PostMapping("/all-paged")
    public Page<ProductDTO> findAllPaged(
            @PageableDefault Pageable pageable,
            @RequestBody ProductFilter productFilter){
        return productService.findAllPaged(productFilter, pageable)
                .map(productConverter::toProductDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable ProductId id){
        return productConverter.toProductDTO(productService.findById(id).get());
    }

    @PostMapping("/create")
    public ProductDTO create(@RequestBody ProductCreationDTO productCreationDTO){
        return productConverter.toProductDTO(productService.create(productCreationDTO));
    }

    @PutMapping("/edit/{id}")
    public ProductDTO edit(@PathVariable ProductId id, @RequestBody ProductCreationDTO productCreationDTO){
        return productConverter.toProductDTO(productService.edit(id, productCreationDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable ProductId id){
        productService.delete(id);
    }
}
