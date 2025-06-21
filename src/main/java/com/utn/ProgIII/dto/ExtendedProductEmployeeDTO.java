package com.utn.ProgIII.dto;


import com.utn.ProgIII.model.Product.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ExtendedProductEmployeeDTO(
        @Schema(example = "1")
        Long idProduct,
        @Schema(example = "Pasas de uva")
        String name,
        @Schema(example = "260")
        BigDecimal price) {

        @Override
        public String toString() {
                return String.format(
                        "ExtendedProductEmployeeDTO[\n" +
                                "\tidProduct :  %d,\n" +
                                "\tproduct name:  %s,\n" +
                                "\tprice   :  %s\n" +
                                "]",
                        idProduct, name, price
                );
        }
}
