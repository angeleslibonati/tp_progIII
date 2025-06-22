package com.utn.ProgIII.dto;


import com.utn.ProgIII.model.Product.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ExtendedProductManagerDTO(
        @Schema(example = "1")
        Long idProduct,
        @Schema(example = "Pasas de uva")
        String name,
        @Schema(example = "200")
        BigDecimal cost,
        @Schema(example = "30")
        BigDecimal profitMargin,
        @Schema(example = "260")
        BigDecimal price,
        @Schema(example = "0.01")
        BigDecimal dollarPrice
) {
        @Override
        public String toString() {
                return String.format(
                        "Product[\n" +
                                "\tidProduct      :  %d,\n" +
                                "\tproduct name   :  %s,\n" +
                                "\tcost           :  $%s\n" +
                                "\tprofitMargin   :  %s%%\n" +
                                "\tprice          :  $%s\n" +
                                "\tdollarPrice    :  U$D%s\n" +
                                "]",
                        idProduct, name, cost, profitMargin, price, dollarPrice
                );
        }
}
