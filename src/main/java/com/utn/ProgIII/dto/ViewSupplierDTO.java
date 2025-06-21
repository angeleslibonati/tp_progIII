package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ViewSupplierDTO(
        @Schema(example = "5")
        Long id,
        @Schema(example = "Compania test")
        String companyName,
        @Schema(example = "23-11111111-9")
        String cuit,
        @Schema(example = "1144358129")
        String phoneNumber,
        @Schema(example = "testemail@email.com")
        String email,
        ViewAddressDTO address) {


        @Override
        public String toString() {
                return String.format(
                        "Supplier[\n" +
                                "\tid          :  %d,\n" +
                                "\tcompanyName :  %s,\n" +
                                "\tcuit        :  %s,\n" +
                                "\tphoneNumber :  %s,\n" +
                                "\temail       :  %s,\n" +
                                "\taddress     :  %s\n" +
                                "]",
                        id, companyName, cuit, phoneNumber, email, formatAddress(address)
                );
        }

        private String formatAddress(ViewAddressDTO address) {
                return String.format(
                        "Address[\n" +
                                "\t\tid     :  %d,\n" +
                                "\t\tstreet :  %s,\n" +
                                "\t\tnumber :  %s,\n" +
                                "\t\tcity   :  %s\n" +
                                "\t]",
                        address.idaddress(), address.street(), address.number(), address.city()
                );
        }
}
