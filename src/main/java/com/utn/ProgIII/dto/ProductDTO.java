package com.utn.ProgIII.dto;

import com.utn.ProgIII.model.Product.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder

public record ProductDTO (
        @Schema(example = "1")
        Long idProduct,
        @Schema(example = "Pasas de uva")
        String name,
        @Schema(implementation = ProductStatus.class)
        String status) {


        @Override
        public String toString() {

                return String.format(
                        "Product[\n\tid    :  %d,\n\tname  :  %s,\n\tStatus:  %s,\n]",
                        idProduct, name, status
                );
        }
}
