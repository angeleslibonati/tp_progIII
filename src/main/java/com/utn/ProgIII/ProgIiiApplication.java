package com.utn.ProgIII;

import com.utn.ProgIII.View.ApiManager.ApiManagerImp;
import com.utn.ProgIII.View.Menu.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class ProgIiiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProgIiiApplication.class, args);

		ApiManagerImp manager = new ApiManagerImp();
//		LoginRequestDTO cred = new LoginRequestDTO("cebollin","dferg532");

        try {

			Menu.menu(new Scanner(System.in),manager);

		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
