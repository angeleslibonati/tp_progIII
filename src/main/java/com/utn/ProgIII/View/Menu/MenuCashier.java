package com.utn.ProgIII.View.Menu;

import com.utn.ProgIII.View.ApiManager.ApiManagerImp;
import com.utn.ProgIII.dto.ProductPricesDTO;
import com.utn.ProgIII.dto.SupplierProductListDTO;

import java.io.IOException;
import java.util.Scanner;

import static com.utn.ProgIII.View.Menu.Menu.*;

public class MenuCashier {

    public static void menuCashier (Scanner scan, ApiManagerImp manager)throws IOException, InterruptedException{

        SupplierProductListDTO supplierProductListDTOS;
        ProductPricesDTO productPricesDTO;
        SupplierProductListDTO[] supplierProductListsDto;

        boolean continuar = true;
        int opcion = 0;
        while (continuar){
            try {
                printMenuCashier();
                opcion = chooseOption(scan);
                continuar = false;
            } catch (Exception e) {
                System.out.println("⚠ Error: " + e.getMessage());
                continuar = chooseContinue(scan);
            }
        }
        continuar = true;

        String id;
        String searchParam;

        do {

            switch (opcion) {

                case 1:  //filtrar listas por nombre de compañia

                    while (continuar){
                        try {

                            System.out.println("Ingrese el nombre de la compañia");
                            searchParam = scan.nextLine();
                            System.out.println("   Procesando...");


                            supplierProductListDTOS = manager.Get("productSupplier/filter", searchParam, SupplierProductListDTO.class);

                            System.out.println(supplierProductListDTOS.toString());

                            continuar = false;

                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    while (continuar){
                        try {
                            printMenuCashier();
                            opcion = chooseOption(scan);
                            continuar = false;
                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    break;
                case 2:  //filtrar listas por id prodcuto

                    while (continuar){
                        try {

                            System.out.println("Ingrese el Id del producto");
                            id = scan.nextLine();
                            System.out.println("   Procesando...");

                            productPricesDTO = manager.Get("productSupplier/filter-product", id, ProductPricesDTO.class);

                            System.out.println(productPricesDTO.toString());

                            continuar = false;

                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    while (continuar){
                        try {
                            printMenuCashier();
                            opcion = chooseOption(scan);
                            continuar = false;
                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    break;

                case 3:

                    while (continuar){
                        try {

                            System.out.println("Ingrese nombre del producto");
                            searchParam = scan.nextLine();
                            System.out.println("   Procesando...");

                            supplierProductListsDto = manager.Get("productSupplier/filter/product", searchParam, SupplierProductListDTO[].class);

                            for (SupplierProductListDTO supplierProduct : supplierProductListsDto) {
                                System.out.println(supplierProduct.toString());
                            }

                            continuar = false;

                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    while (continuar){
                        try {
                            printMenuCashier();
                            opcion = chooseOption(scan);
                            continuar = false;
                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    break;

                default:
                    System.out.println("Opción invalida");
                    while (continuar){
                        try {
                            printMenuCashier();
                            opcion = chooseOption(scan);
                            continuar = false;
                        } catch (Exception e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            continuar = chooseContinue(scan);
                        }
                    }
                    continuar = true;
                    break;
            }

        }while (opcion != 0);
    }
}
