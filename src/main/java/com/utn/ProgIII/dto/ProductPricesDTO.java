package com.utn.ProgIII.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.LinkedHashMap;
import java.util.List;

public record ProductPricesDTO(
        @Schema(example = "1")
        Long idProduct,
        @Schema(example = "Papas")
        String name,
        @ArraySchema(schema = @Schema(oneOf = {ProductPriceSupplierEmployeeDTO.class,
                ProductPriceSupplierManagerDTO.class}))
        List<?> prices
) {

        @Override
        public String toString() {
                return String.format(
                        "ProductPrices[\n" +
                                "\tidProduct :  %d,\n" +
                                "\tname      :  %s,\n" +
                                "\tprices    :  %s\n" +
                                "]",
                        idProduct,
                        name,
                        formatPrices(prices)
                );
        }

        private String formatPrices(List<?> list) {
                ObjectMapper objectMapper = new ObjectMapper();

                if (list == null || list.isEmpty()) {
                        return "[]";
                }

                StringBuilder sb = new StringBuilder("[\n");

                for (Object price : list) {
                        if (((LinkedHashMap<?, ?>) price).containsKey("profitMargin")) {
                                ProductPriceSupplierManagerDTO dto = objectMapper.convertValue(price, ProductPriceSupplierManagerDTO.class);
                                sb.append(dto.toString());
                        } else {
                                ProductPriceSupplierEmployeeDTO dto = objectMapper.convertValue(price, ProductPriceSupplierEmployeeDTO.class);
                                sb.append(dto.toString());
                        }
                }

                sb.append("\t]");
                return sb.toString();
        }
}
