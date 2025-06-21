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

        int opcion = 1;
        printMenuCashier();
        opcion = chooseOption(scan);

        String id;
        String searchParam;

        while (opcion != 0) {

            switch (opcion) {

                case 1:  //filtrar listas por nombre de compañia
                    System.out.println("Ingrese el nombre de la compañia");
                    searchParam = scan.nextLine();
                    System.out.println("   Procesando...");


                    supplierProductListDTOS = manager.Get("productSupplier/filter", searchParam, SupplierProductListDTO.class);

                    System.out.println(supplierProductListDTOS.toString());

                    printMenuCashier();
                    opcion = chooseOption(scan);

                    break;
                case 2:  //filtrar listas por id prodcuto

                    System.out.println("Ingrese el Id del producto");
                    id = scan.nextLine();
                    System.out.println("   Procesando...");

                    productPricesDTO = manager.Get("productSupplier/filter-product", id, ProductPricesDTO.class);

                    System.out.println(productPricesDTO.toString());

                    printMenuCashier();
                    opcion = chooseOption(scan);

                    break;

                case 3:

                    System.out.println("Ingrese nombre del producto");
                    searchParam = scan.nextLine();
                    System.out.println("   Procesando...");

                    supplierProductListsDto = manager.Get("productSupplier/filter/product", searchParam, SupplierProductListDTO[].class);

                    for (SupplierProductListDTO supplierProduct : supplierProductListsDto) {
                        System.out.println(supplierProduct.toString());
                    }

                    printMenuCashier();
                    opcion = chooseOption(scan);
                    break;

                default:
                    System.out.println("Opción invalida");
                    printMenuAdmin();
                    opcion = chooseOption(scan);
            }
        }
        System.out.println("-- No apague su pc --");
        System.out.println("   Saliendo...");
    }
}
