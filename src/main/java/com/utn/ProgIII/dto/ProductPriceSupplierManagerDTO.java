package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductPriceSupplierManagerDTO(
        @Schema(example = "1")
        Long idSupplier,
        @Schema(example = "Empresa")
        String companyName,
        @Schema(example = "100")
        BigDecimal cost,
        @Schema(example = "100")
        BigDecimal profitMargin,
        @Schema(example = "200")
        BigDecimal price,
        @Schema(example = "0.01")
        BigDecimal dollarPrice
) {

        @Override
        public String toString() {
                return String.format(
                        "ManagerPrice[\n" +
                                "\tsupplierName :  %s,\n" +
                                "\tcost         :  $%s,\n" +
                                "\tprofitMargin :  %s%%,\n" +
                                "\tfinalPrice   :  $%s,\n" +
                                "\tdollarPrice   : U$D %s\n" +
                                "]",
                        companyName,
                        format(cost),
                        format(profitMargin),
                        format(price),
                        format(dollarPrice)
                );
        }

        private String format(BigDecimal val) {
                return val.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }

}
