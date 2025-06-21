package com.utn.ProgIII.dto;

import com.utn.ProgIII.model.Credential.Credential;
import com.utn.ProgIII.model.User.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Formato que adquieren los datos recibidos desde el mapper previo a ser devueltos como respuesta a una request
 */
public record UserWithCredentialDTO(
        @Schema(example = "1")
        Long idUser,
        @Schema(example = "Carlitos")
        String firstname,
        @Schema(example = "Testeador")
        String lastname,
        @Schema(example = "12345678")
        String dni,
        @Schema(implementation = UserStatus.class)
        String status,
        ViewCredentialsDTO credential /*Necesita ser un DTO, esto es la implementacion de la clase*/
) {

        @Override
        public String toString() {
                return String.format(
                        "User[\n" +
                                "\tid        :  %d,\n" +
                                "\tfirstname :  %s,\n" +
                                "\tlastname  :  %s,\n" +
                                "\tdni       :  %s,\n" +
                                "\tstatus    :  %s,\n" +
                                "\tcredential:  %s\n" +
                                "]",
                        idUser, firstname, lastname, dni, formatStatus(status), formatCredential(credential)
                );
        }

        private String formatStatus(String status) {
                return "ENABLED".equalsIgnoreCase(status) ? "Activo" : "Inactivo";
        }

        private String formatCredential(ViewCredentialsDTO credential) {
                return String.format(
                        "Credentials[\n" +
                                "\t\tusername:  %s,\n" +
                                "\t\trole    :  %s\n" +
                                "\t]",
                        credential.username(), credential.role()
                );
        }
}
