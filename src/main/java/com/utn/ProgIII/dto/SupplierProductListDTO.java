package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

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
                if (products == null || products.isEmpty()) {
                        return "[]";
                }

                StringBuilder sb = new StringBuilder("[\n");
                for(Object product : products) {
                        if(product instanceof  ExtendedProductEmployeeDTO){
                                sb.append(((ExtendedProductEmployeeDTO)product).toString());
                        }
                        else if(product instanceof  ExtendedProductManagerDTO){
                                sb.append(((ExtendedProductManagerDTO)product).toString());
                        }
                }
                sb.append("\t]");

//                int count = 1;
//                for (Object product : products) {
//                        sb.append(String.format("\t\t#%d: %s,\n", count++, product.toString().replace("\n", "\n\t\t")));
//                }
                return sb.toString();
        }
}
