package com.utn.ProgIII.View.Menu;

import com.utn.ProgIII.View.ApiManager.ApiManagerImp;
import com.utn.ProgIII.dto.LoginRequestDTO;
import com.utn.ProgIII.dto.LoginResponseDTO;
import com.utn.ProgIII.security.JwtUtil;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void menu (Scanner scan, ApiManagerImp manager) throws IOException, InterruptedException{

        boolean continuar = true;
        String rol = "";

        System.out.println("-- Bienvenido --\n");

        while (continuar){
            try {

                rol = Login(scan,manager);

                switch (rol){
                    case "ROLE_ADMIN":

                        MenuAdmin.menuAdmin(scan,manager);
                        break;
                    case "ROLE_MANAGER":
                        MenuManager.menuManager(scan,manager);
                        break;
                    case "ROLE_EMPLOYEE":
                        MenuCashier.menuCashier(scan,manager);
                        break;
                    default:
                        System.out.println("-- Credenciales incorrectas --");
                        break;
                }

                continuar = false;

            } catch (Exception e) {
                System.out.println("⚠ Error: " + e.getMessage());
                continuar = chooseContinue(scan);
            }
        }

        System.out.println("-- No apague su pc --");
        System.out.println("   Cerrando sesion...");
        manager.CerrarSesion();
        System.exit(0);
    }

    public static String Login(Scanner scan, ApiManagerImp manager) throws IOException, InterruptedException {
        String username;
        String password;
        LoginResponseDTO tokenDTO;
        LoginRequestDTO credencialDTO;
        JwtUtil jwtUtil = new JwtUtil();

        int opcion = 1;

        System.out.println("-- Iniciar Sesion --");

        //Usuario
        System.out.println("Usuario : ");
        username = scan.nextLine();

        // Contraseña
        System.out.println("Contraseña : ");
        password = scan.nextLine();

        //logueo
        credencialDTO = new LoginRequestDTO(username,password);

        tokenDTO = manager.Post("auth/login",credencialDTO, LoginResponseDTO.class);
        return  jwtUtil.extractRole(tokenDTO.token());
    }

    //elegir opcion
    public static int chooseOption(Scanner scan){

        int opcion = 0;
        System.out.println("Ingrese una opcion:  ");
        opcion = scan.nextInt();
        scan.nextLine();
        return opcion;
    }

    //elegir opcion para continuar por error
    public static boolean chooseContinue(Scanner scan){
        String opcionContinuar = "";
        boolean continuar = true;

        System.out.print("¿Desea intentar nuevamente? (si/no): ");
        scan.nextLine();
        opcionContinuar = scan.nextLine();

        if(!opcionContinuar.equalsIgnoreCase("si")){
            continuar = false;
        }
        return continuar;
    }


    //Menu y SubMenu
    public static void printMenuAdmin (){
        System.out.println("-- Menu Administrador --\n");
        System.out.println("1. Gestion usuario");
        System.out.println("2. Gestion producto");
        System.out.println("3. Gestion proveedor");
        System.out.println("4. Gestion producto-proveedor");
        System.out.println("5. Actualización listas precios");
        System.out.println("6. Moneda extranjera");

        System.out.println("0. Salir");
    }

    public static void printMenuManager (){
        System.out.println("-- Menu Manager --\n");
        System.out.println("1. Gestion producto");
        System.out.println("2. Gestion proveedor");
        System.out.println("3. Gestion producto-proveedor");
        System.out.println("4. Actualización listas precios");
        System.out.println("5. Moneda extranjera");

        System.out.println("0. Salir");
    }

    public static void printMenuCashier(){
        System.out.println("-- Menu Cajero --\n");
        System.out.println("1. Filtrar listas por nombre compañia");
        System.out.println("2. Filtrar listas por Id de producto");
        System.out.println("3. Filtrar listas por nombre producto");

        System.out.println("0. Salir");
    }

    public static void printSubMenuUser (){
        System.out.println("-- Gestion de usuario --\n");

        System.out.println("1. Crear nuevo usuario");
        System.out.println("2. Eliminar un usuario");
        System.out.println("3. Modificar un usuario");
        System.out.println("4. Visuarlizar todos los usuario");
        System.out.println("5. Filtrar usuarios");

        System.out.println("0. Volver atras");
    }

    public static void printSubMenuUpdateUser(){
        System.out.println("-- Ingrese el campo que desea modificar --\n");

        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. DNI");
        System.out.println("4. Usuario");
        System.out.println("5. Contraseña");
        System.out.println("6. Rol");

    }

    public static void printSubMenuRole(){
        System.out.println("Indique el rol");

        System.out.println("1. Administrador");
        System.out.println("2. Manager");
        System.out.println("3. Cajero");
    }

    public static void printSubMenuFilter(){
        System.out.println("-- Filtrar usuarios --");

        System.out.println("1. Numero Id");
        System.out.println("2. Rol");
        System.out.println("3. Estado");

        System.out.println("0. Volver atras");
    }

    public static void printSubMenuproduct(){
        System.out.println("-- Gestion producto --\n");

        System.out.println("1. Crear nuevo producto");
        System.out.println("2. Eliminar un producto");
        System.out.println("3. Modificar un producto");
        System.out.println("4. Visualizar todos los productos");
        System.out.println("5. Filtrar productos");

        System.out.println("0. Volver atras");

    }

    public static void printSubMenuFilterProduct(){
        System.out.println("-- Filtrar productos --");

        System.out.println("1. Numero Id");
        System.out.println("2. Nombre");
        System.out.println("3. Estado");

        System.out.println("0. Volver atras");
    }

    public static void printSubMenuSupplier(){
        System.out.println("-- Gestion proveedor --\n");

        System.out.println("1. Crear nuevo proveedor");
        System.out.println("2. Eliminar un proveedor");
        System.out.println("3. Modificar un proveedor");
        System.out.println("4. Visualizar todos los proveedores");
        System.out.println("5. Filtrar proveedores por Id");

        System.out.println("0. Volver atras");
    }

    public static void printSubMenuUpdateSupplier(){
        System.out.println("-- Ingrese el campo que desea modificar --\n");

        System.out.println("1. Nombre Compañia");
        System.out.println("2. Cuit");
        System.out.println("3. Telefono");
        System.out.println("4. Email");
        System.out.println("5. Calle");
        System.out.println("6. Numero");
        System.out.println("7. Ciudad");

    }

    public static void printSubMenuProductSupplier(){
        System.out.println("-- Gestion producto-proveedor --\n");

        System.out.println("1. Crear nueva asignación producto-proveedor");
        System.out.println("2. Modificar datos de relación");
        System.out.println("3. Filtrar producto-proveedor");

        System.out.println("0. Volver atras");
    }

    public static void printSubMenuFilterProductSupplier(){
        System.out.println("-- Filtrar producto-proveedor --");

        System.out.println("1. Numero Id relacion producto-proveedor");
        System.out.println("2. Nombre Compañia");
        System.out.println("3. Numero Id producto");

        System.out.println("0. Volver atras");
    }






}
