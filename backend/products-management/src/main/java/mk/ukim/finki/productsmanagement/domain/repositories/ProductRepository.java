package mk.ukim.finki.productsmanagement.domain.repositories;

import mk.ukim.finki.productsmanagement.domain.dtos.ProductFilter;
import mk.ukim.finki.productsmanagement.domain.models.Product;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {

    @Query("select distinct p from Product p " +
            "where " +
            "(:#{#filter.minPrice} is null or p.price >= :#{#filter.minPrice}) " +
            "and " +
            "(:#{#filter.maxPrice} is null or p.price <= :#{#filter.maxPrice}) " +
            "and " +
            "(:#{#filter.title} is null or p.title like %:#{#filter.title}%) " +
            "and " +
            "(:#{#filter.category.toString()} is null or lower(p.category) like #{#filter.category}) ")
    Page<Product> findAllPaged(ProductFilter filter, Pageable pageable);
}
