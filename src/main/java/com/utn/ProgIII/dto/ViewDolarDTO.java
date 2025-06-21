package com.utn.ProgIII.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record ViewDolarDTO(
        @Schema(example = "USD")
        String moneda,
        @Schema(example = "oficial")
        String nombre,
        @Schema(example = "oficial")
        String casa,
        @Schema(example = "1200")
        BigDecimal venta,
        @Schema(example = "1150")
        BigDecimal compra,
        @Schema(example = "2025-06-11T09:46:00.000Z")
        String ultima_actualizacion
){

        @Override
        public String toString() {
                return String.format(
                        "DolarInfo[\n" +
                                "\tmoneda              :  %s,\n" +
                                "\tnombre              :  %s,\n" +
                                "\tcasa                :  %s,\n" +
                                "\tventa               :  U$D %s,\n" +
                                "\tcompra              :  U$D %s,\n" +
                                "\tultima_actualizacion:  %s\n" +
                                "]",
                        moneda,
                        nombre,
                        casa,
                        formatDecimal(venta),
                        formatDecimal(compra),
                        formatFecha(ultima_actualizacion)
                );
        }

        private String formatDecimal(BigDecimal value) {
                return value.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }

        private String formatFecha(String iso) {
                try {
                        ZonedDateTime dateTime = ZonedDateTime.parse(iso);
                        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                } catch (Exception e) {
                        return iso; // en caso de error, devolvemos el original
                }
        }
}
