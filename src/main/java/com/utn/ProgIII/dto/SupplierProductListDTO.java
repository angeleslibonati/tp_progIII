package com.utn.ProgIII.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.LinkedHashMap;
import java.util.List;

public record SupplierProductListDTO(
        @Schema(example = "1")
        Long idSupplier,
        @Schema(example = "Compania test")
        String companyName,
        List<?> productsList) {

        @Override
        public String toString() {
                return String.format(
                        "SupplierProductList[\n" +
                                "\tidSupplier :  %d,\n" +
                                "\tcompanyName:  %s,\n" +
                                "\tproducts   :  %s\n" +
                                "]",
                        idSupplier, companyName, formatProducts(productsList)
                );
        }

        private String formatProducts(List<?> products) {
                ObjectMapper objectMapper = new ObjectMapper();

                if (products == null || products.isEmpty()) {
                        return "[]";
                }

                StringBuilder sb = new StringBuilder("[\n");
                for(Object product : products) {

                        if (((LinkedHashMap<?, ?>) product).containsKey("profitMargin")) {
                                ExtendedProductManagerDTO dto = objectMapper.convertValue(product, ExtendedProductManagerDTO.class);
                                sb.append(dto.toString());
                        } else {
                                ExtendedProductEmployeeDTO dto = objectMapper.convertValue(product, ExtendedProductEmployeeDTO.class);
                                sb.append(dto.toString());
                        }
                }
                sb.append("\t]");

                return sb.toString();
        }
}
