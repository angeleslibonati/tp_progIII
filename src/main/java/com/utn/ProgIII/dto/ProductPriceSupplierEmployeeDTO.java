package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductPriceSupplierEmployeeDTO(
        @Schema(example = "1")
        Long idSupplier,
        @Schema(example = "Empresa")
        String companyName,
        @Schema(example = "200")
        BigDecimal price
) {

        @Override
        public String toString() {
                return String.format(
                        "EmployeePrice[\n" +
                                "\tsupplierName :  %s,\n" +
                                "\tprice        :  $%s\n" +
                                "]",
                        companyName,
                        format(price)
                );
        }

        private String format(BigDecimal val) {
                return val.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
}
