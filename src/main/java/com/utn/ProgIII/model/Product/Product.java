package com.utn.ProgIII.model.Product;

import com.utn.ProgIII.model.ProductSupplier.ProductSupplier;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(min = 3, max = 50, message = "El nombre del producto debe contener entre 3 y 50 caracteres")
    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<ProductSupplier> productSuppliers;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
