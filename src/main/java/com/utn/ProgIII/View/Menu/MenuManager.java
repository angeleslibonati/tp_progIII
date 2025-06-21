package com.utn.ProgIII.View.Menu;

import com.utn.ProgIII.View.ApiManager.ApiManagerImp;
import com.utn.ProgIII.dto.*;
import com.utn.ProgIII.mapper.ProductMapper;
import com.utn.ProgIII.mapper.ProductSupplierMapper;
import com.utn.ProgIII.mapper.SupplierMapper;
import com.utn.ProgIII.model.Address.Address;
import com.utn.ProgIII.model.Product.Product;
import com.utn.ProgIII.model.Product.ProductStatus;
import com.utn.ProgIII.model.ProductSupplier.ProductSupplier;
import com.utn.ProgIII.model.Supplier.Supplier;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;

import static com.utn.ProgIII.View.Menu.Menu.*;

public class MenuManager {


    public static void menuManager (Scanner scan, ApiManagerImp manager) throws IOException, InterruptedException {

        int opcion = 1;
        printMenuManager();
        opcion = chooseOption(scan);

        Map<String,String> queryParams;

        Product newProduct = new Product();
        ProductDTO productDTO;
        ProductMapper productMapper = new ProductMapper();
        ProductDTO[] productsDTO;

        Supplier newSupplier = new Supplier();
        Supplier supplier;
        Address newAddress = new Address();
        SupplierMapper supplierMapper = new SupplierMapper();
        ViewSupplierDTO supplierDTO;
        ViewSupplierDTO[] suppliersDTO;

        ProductSupplier newProductSupplier = new ProductSupplier();
        ProductSupplierMapper productSupplierMapper = new ProductSupplierMapper();
        ResponseProductSupplierDTO productSupplierDTO;
        SupplierProductListDTO supplierProductListDTOS;
        ProductPricesDTO productPricesDTO;

        String[] productNullCsv;

        ViewDolarDTO dolarDTO;

        String id;
        String searchParam;


        while (opcion != 0){

            switch (opcion) {

                case 1:  //gestion producto

                    printSubMenuproduct();
                    opcion = chooseOption(scan);

                    switch (opcion) {

                        case 1:
                            System.out.println("-- Nuevo producto --\n");

                            System.out.println("Nombre");
                            newProduct.setName(scan.nextLine());

                            newProduct.setStatus(ProductStatus.ENABLED);

                            productDTO = productMapper.toProductDTO(newProduct);
                            System.out.println("   Procesando...");
                            productDTO = manager.Post("product", productDTO, ProductDTO.class);

                            System.out.println("-- Carga exitosa -- ");
                            System.out.println(productDTO.toString());


                            break;

                        case 2:

                            //Baja Logica de un producto
                            System.out.println("-- Eliminar un producto --\n");

                            System.out.println("Ingrese el id del producto");
                            id = scan.nextLine();

                            System.out.println("   Procesando...");

                            productDTO = manager.Delete("product", id, ProductDTO.class);

                            System.out.println("-- Eliminacion exitosa --");
                            System.out.println(productDTO.toString());

                            break;

                        case 3:

                            System.out.println("-- Modificar un producto --\n");

                            System.out.println("Ingrese el id del producto");
                            id = scan.nextLine();

                            productDTO = manager.Get("product", id, ProductDTO.class);

                            System.out.println(productDTO.toString());

                            newProduct = productMapper.toEntity(productDTO);

                            while (opcion != 0) {

                                System.out.println("-- Ingrese el campo que desea modificar --\n");

                                System.out.println("1. Nombre");
                                System.out.println("2. Estado");
                                opcion = chooseOption(scan);

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Nombre");
                                        newProduct.setName(scan.nextLine());
                                        break;

                                    case 2:
                                        System.out.println("Estado");

                                        if (newProduct.getStatus().equals(ProductStatus.ENABLED)) {
                                            newProduct.setStatus(ProductStatus.DISABLED);
                                        } else {
                                            newProduct.setStatus(ProductStatus.ENABLED);
                                        }

                                        break;

                                    default:
                                        System.out.println("Opción invalida");
                                }

                                System.out.println("¿Desea modificar otro campo?");
                                System.out.println("1. Si");
                                System.out.println("0. No");
                                opcion = scan.nextInt();
                                scan.nextLine();

                                productDTO = manager.Put("product", newProduct, id, ProductDTO.class);

                                System.out.println(productDTO.toString());
                            }
                            opcion = 1;
                            break;

                        case 4:

                            System.out.println("-- Visualizar todos los productos --\n");

                            productsDTO = manager.Get("product", null, ProductDTO[].class);

                            for (ProductDTO productDto : productsDTO) {
                                System.out.println(productDto.toString());
                            }

                            opcion = 1;
                            break;

                        case 5:

                            printSubMenuFilterProduct();
                            opcion = chooseOption(scan);

                            switch (opcion) {

                                case 1:

                                    System.out.println("Ingrese el Id del producto");
                                    id = scan.nextLine();

                                    productDTO = manager.Get("product", id, ProductDTO.class);

                                    System.out.println(productDTO.toString());

                                    break;

                                case 2:

                                    System.out.println("Ingrese el nombre del producto");
                                    searchParam = scan.nextLine();

                                    productsDTO = manager.Get("product/search/name", searchParam, ProductDTO[].class);

                                    for (ProductDTO productDto : productsDTO) {

                                        System.out.println(productDto.toString());
                                    }

                                    break;

                                case 3:

                                    System.out.println("-- Estado del producto --");

                                    System.out.println("1. Activo");  //enabled
                                    System.out.println("2. Inactivo");     //disabled

                                    opcion = chooseOption(scan);

                                    if (opcion == 1) {
                                        searchParam = "ENABLED";
                                    } else {
                                        searchParam = "DISABLED";
                                    }
                                    System.out.println("   Procesando...");
                                    productsDTO = manager.Get("product/search/status", searchParam, ProductDTO[].class);

                                    for (ProductDTO productDto : productsDTO) {
                                        System.out.println(productDto.toString());
                                    }

                                    break;

                                case 0:
                                    //volver atras
                                    printSubMenuproduct();
                                    opcion = chooseOption(scan);
                                    break;

                                default:
                                    System.out.println("Opción invalida");

                            }

                            opcion = 1;
                            break;

                        case 0:
                            //volver atras
                            printMenuAdmin();
                            opcion = chooseOption(scan);
                            break;
                        default:
                            System.out.println("Opción invalida");
                    }

                case 2:  //Gestion proveedor

                    printSubMenuSupplier();
                    opcion = chooseOption(scan);

                    switch (opcion) {

                        case 1: //alta

                            System.out.println("-- Nuevo proveedor --\n");

                            System.out.println("Nombre compañia");
                            newSupplier.setCompanyName(scan.nextLine());
                            System.out.println("Cuit");
                            newSupplier.setCuit(scan.nextLine());
                            System.out.println("Telefono");
                            newSupplier.setPhoneNumber(scan.nextLine());
                            System.out.println("Email");
                            newSupplier.setEmail(scan.nextLine());

                            System.out.println("Calle");
                            newAddress.setStreet(scan.nextLine());
                            System.out.println("Numero");
                            newAddress.setNumber(scan.nextLine());
                            System.out.println("Ciudad");
                            newAddress.setCity(scan.nextLine());

                            newSupplier.setAddress(newAddress);

                            supplierDTO = supplierMapper.toViewSupplierDTO(newSupplier);

                            supplierDTO = manager.Post("supplier", supplierDTO, ViewSupplierDTO.class);

                            System.out.println("-- Carga exitosa");

                            System.out.println(supplierDTO.toString());

                            opcion = 3;
                            break;

                        case 2:  //baja

                            System.out.println("-- Eliminar proveedor --\n");

                            System.out.println("Ingrese el id del proveedor");
                            id = scan.nextLine();

                            System.out.println("   Procesando...");
                            supplierDTO = manager.Delete("supplier", id, ViewSupplierDTO.class);
                            System.out.println("\n-- Eliminación exitosa --");

                            opcion = 3;
                            break;

                        case 3:  //modificar

                            System.out.println("-- Modificación proveedor --\n");

                            System.out.println("Ingrese el id del proveedor");
                            id = scan.nextLine();

                            supplierDTO = manager.Get("supplier", id, ViewSupplierDTO.class);

                            System.out.println(supplierDTO.toString());
                            supplier = supplierMapper.toObjectFromViewSupplierDTO(supplierDTO);

                            while (opcion != 0) {

                                printSubMenuUpdateSupplier();
                                opcion = chooseOption(scan);

                                newAddress = supplier.getAddress();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Nombre compañia");
                                        supplier.setCompanyName(scan.nextLine());
                                        break;
                                    case 2:
                                        System.out.println("Cuit");
                                        supplier.setPhoneNumber(scan.nextLine());
                                        break;
                                    case 3:
                                        System.out.println("Telefono");
                                        supplier.setPhoneNumber(scan.nextLine());
                                        break;
                                    case 4:
                                        System.out.println("Email");
                                        supplier.setEmail(scan.nextLine());
                                        break;
                                    case 5:
                                        System.out.println("Calle");
                                        newAddress.setStreet(scan.nextLine());
                                        break;
                                    case 6:
                                        System.out.println("Numero");
                                        newAddress.setNumber(scan.nextLine());
                                        break;
                                    case 7:
                                        System.out.println("Ciudad");
                                        newAddress.setCity(scan.nextLine());
                                        break;
                                    default:
                                        System.out.println("Opcion invalida");

                                }

                                System.out.println("¿Desea modificar otro campo?");
                                System.out.println("1. Si");
                                System.out.println("0. No");
                                opcion = scan.nextInt();
                                scan.nextLine();

                                supplier.setAddress(newAddress);
                                supplierDTO = manager.Put("supplier", supplier, id, ViewSupplierDTO.class);

                                System.out.println(supplierDTO.toString());
                            }

                            opcion = 3;
                            break;

                        case 4:  //visualiza todos los proveedores

                            System.out.println("-- Visualizar todos los proveedores --\n");

                            suppliersDTO = manager.Get("supplier", null, ViewSupplierDTO[].class);

                            for (ViewSupplierDTO supplierDto : suppliersDTO) {
                                System.out.println(supplierDto.toString());
                            }

                            opcion = 3;
                            break;

                        case 5:  //filtra

                            System.out.println("Ingrese el Id del proveedor");
                            id = scan.nextLine();
                            System.out.println("   Procesando...");

                            supplierDTO = manager.Get("supplier", id, ViewSupplierDTO.class);


                            System.out.println(supplierDTO.toString());

                            opcion = 3;
                            break;

                        case 0:
                            //volver atras
                            printMenuAdmin();
                            opcion = chooseOption(scan);
                            break;

                        default:
                            System.out.println("opción invalida");
                    }

                case 3:  //Gestion producto-proveedor

                    printSubMenuProductSupplier();
                    opcion = chooseOption(scan);

                    switch (opcion) {

                        case 1:  //crear relacion
                            System.out.println("Ingrese el Id del proveedor");
                            id = scan.nextLine();
                            System.out.println("   Procesando...");

                            supplierDTO = manager.Get("supplier", id, ViewSupplierDTO.class);
                            supplier = supplierMapper.toObjectFromViewSupplierDTO(supplierDTO);

                            newProductSupplier.setSupplier(supplier);

                            System.out.println("Ingrese el Id del producto");
                            id = scan.nextLine();
                            System.out.println("   Procesando...");

                            productDTO = manager.Get("product", id, ProductDTO.class);

                            newProduct = productMapper.toEntity(productDTO);

                            newProduct.setIdProduct(productDTO.idProduct());

                            newProductSupplier.setProduct(newProduct);

                            System.out.println("Ingrese el costo");
                            newProductSupplier.setCost(scan.nextBigDecimal());
                            scan.nextLine();
                            System.out.println("Ingrese el margen de ganancia");
                            newProductSupplier.setProfitMargin(scan.nextBigDecimal());
                            scan.nextLine();
                            newProductSupplier.setPrice(newProductSupplier.getCost().add(newProductSupplier.getCost().multiply(newProductSupplier.getProfitMargin()).divide(BigDecimal.valueOf(100), RoundingMode.CEILING)));

                            productSupplierDTO = productSupplierMapper.fromEntityToDto(newProductSupplier);

                            productSupplierDTO = manager.Post("productSupplier", productSupplierDTO, ResponseProductSupplierDTO.class);

                            System.out.println("-- Carga exitosa --");

                            System.out.println(productSupplierDTO.toString());
                            opcion = 4;
                            break;

                        case 2:  // modificar costo o margen ganancia  (ver patch)

                            System.out.println("Ingese el Id producto-proveedor");
                            id = scan.nextLine();
                            System.out.println("   Procesando...");

                            productSupplierDTO = manager.Get("productSupplier/relationship", id, ResponseProductSupplierDTO.class);

                            System.out.println(productSupplierDTO.toString());

                            newProductSupplier = productSupplierMapper.fromDtoToEntity(productSupplierDTO);

                            while (opcion != 0) {

                                System.out.println("-- Ingrese el campo que desea modificar --\n");

                                System.out.println("1. Costo");
                                System.out.println("2. Porcentaje de ganancia");
                                opcion = chooseOption(scan);

                                if (opcion == 1) {
                                    System.out.println("Costo");
                                    newProductSupplier.setCost(scan.nextBigDecimal());
                                    scan.nextLine();
                                } else {
                                    System.out.println("Porcentaje de ganancia");
                                    newProductSupplier.setProfitMargin(scan.nextBigDecimal());
                                    scan.nextLine();
                                }

                                productSupplierDTO = manager.Patch("productSupplier", id, newProductSupplier, ResponseProductSupplierDTO.class);


                                System.out.println("¿Desea modificar otro campo?");
                                System.out.println("1. Si");
                                System.out.println("0. No");
                                opcion = scan.nextInt();
                                scan.nextLine();

                                System.out.println(productSupplierDTO.toString());

                            }

                            opcion = 4;
                            break;

                        case 3:  // filtrar por id de producto , nombre compañia, por id de relacion
                            printSubMenuFilterProductSupplier();
                            opcion = chooseOption(scan);

                            switch (opcion) {
                                case 1: //id relacion

                                    System.out.println("Ingrese el Id de producto-proveedor");
                                    id = scan.nextLine();
                                    System.out.println("   Procesando...");

                                    productSupplierDTO = manager.Get("productSupplier/relationship", id, ResponseProductSupplierDTO.class);

                                    System.out.println(productSupplierDTO.toString());

                                    break;

                                case 2: //nombre compañia

                                    System.out.println("Ingrese el nombre de la compañia");
                                    searchParam = scan.nextLine();
                                    System.out.println("   Procesando...");


                                    supplierProductListDTOS = manager.Get("productSupplier/filter", searchParam, SupplierProductListDTO.class);

                                    System.out.println(supplierProductListDTOS.toString());

                                    break;

                                case 3: //id producto  /filter-product
                                    System.out.println("Ingrese el Id del producto");
                                    id = scan.nextLine();
                                    System.out.println("   Procesando...");

                                    productPricesDTO = manager.Get("productSupplier/filter-product", id, ProductPricesDTO.class);

                                    System.out.println(productPricesDTO.toString());

                                    break;

                                case 0:
                                    //volver atras
                                    printSubMenuFilterProductSupplier();
                                    opcion = chooseOption(scan);
                                    break;

                                default:
                                    System.out.println("Opcion invalida");
                            }

                            opcion = 4;
                            break;

                        case 0:
                            //volver atras
                            printMenuAdmin();
                            opcion = chooseOption(scan);
                            break;

                        default:
                            System.out.println("Opcion invalida");
                    }

                case 4:  //actalizacin lista de precios

                    System.out.println("-- Visualizar todos los proveedores --\n");

                    suppliersDTO = manager.Get("supplier", null, ViewSupplierDTO[].class);

                    for (ViewSupplierDTO supplierDto : suppliersDTO) {
                        System.out.println(supplierDto.toString());
                    }

                    System.out.println("Ingese el Id del proveedor");
                    id = scan.nextLine();

                    System.out.println("   Procesando...");

                    supplierDTO = manager.Get("supplier",id, ViewSupplierDTO.class);
                    System.out.println(supplierDTO.toString());

                    System.out.println("¿Desea agregar un costo general a los productos?");

                    System.out.println("1. Si");
                    System.out.println("2. No");

                    opcion = scan.nextInt();
                    scan.nextLine();

                    switch (opcion){

                        case 1: //carga general de margen de ganancia

                            System.out.println("Indique el margen de ganancia que desea aplicar");
                            BigDecimal profitMargin = scan.nextBigDecimal();
                            scan.nextLine();

                            System.out.println("\nIngrese el nombre del archivo");
                            String newFile = scan.nextLine();

                            queryParams = Map.of("file", newFile,
                                    "idSupplier", id,
                                    "bulkProfitMargin", profitMargin.toString());

                            productNullCsv = manager.Post("productSupplier/uploadNonRelatedProducts", supplierDTO,String[].class, queryParams);

                            break;

                        case 2:

                            System.out.println("\nIngrese el nombre del archivo");
                            newFile = scan.nextLine();

                            queryParams = Map.of("file", newFile,
                                    "idSupplier", id);

                            productNullCsv = manager.Post("productSupplier/upload", supplierDTO,String[].class, queryParams);

                            break;

                        default:
                            System.out.println("opcion invalida");
                    }

                    System.out.println("-- Carga exitosa --");
                    printMenuAdmin();
                    opcion = chooseOption(scan);
                    break;

                case 5:  //moneda extranjera

                    System.out.println("-- Cotizacion Dolar --");

                    dolarDTO = manager.Get("misc/dollar", null, ViewDolarDTO.class);
                    System.out.println("   Procesando...");

                    System.out.println(dolarDTO.toString());

                    printMenuAdmin();
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
