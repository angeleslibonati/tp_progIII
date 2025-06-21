package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ResponseProductSupplierDTO(
        @Schema(example = "1")
        Long idProductSupplier,
        @Schema(example = "1")
        Long idProduct,
        @Schema(example = "Pasas de uva")
        String productName,
        @Schema(example = "1")
        Long idSupplier,
        @Schema(example = "Compania Test")
        String supplierName,
        @Schema(example = "200")
        BigDecimal cost,
        @Schema(example = "30")
        BigDecimal profitMargin,
        @Schema(example = "260")
        BigDecimal price) {

        @Override
        public String toString() {
                return String.format(
                        "ResponseProductSupplier[\n" +
                                "\tidProductSupplier :  %d,\n" +
                                "\tidProduct         :  %d,\n" +
                                "\tproductName       :  %s,\n" +
                                "\tidSupplier        :  %d,\n" +
                                "\tsupplierName      :  %s,\n" +
                                "\tcost              :  $%s,\n" +
                                "\tprofitMargin      :  %s%%,\n" +
                                "\tprice             :  $%s\n" +
                                "]",
                        idProductSupplier, idProduct, productName, idSupplier,
                        supplierName, formatDecimal(cost), formatDecimal(profitMargin), formatDecimal(price)
                );
        }

        private String formatDecimal(BigDecimal value) {
                return value.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
}
