package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class MenuService {
	public static List<Customer> listAllCustomers = CustomerRepository.getAllCustomer();
	public static List<ItemService> listAllItemService = ItemServiceRepository.getAllItemService();
	public static List<BookingOrder> listBookingOrder = new ArrayList<>();
	public static Customer customerLoggedIn = new Customer();

	public static void run() {
		String[] listMenu = {"Login", "Exit"};
		boolean isLooping = true;
		int menuChoice = 0;

		do {
			PrintService.printMenu("Aplikasi Booking Bengkel", listMenu);
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu: ", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length-1, 0);

			switch (menuChoice) {
				case 1:
					login();
					break;
				case 0:
					isLooping = false;
					break;
			}
		} while (isLooping);
		
	}
	
	public static void login() {

			boolean isLogin = BengkelService.loginService();
			if (isLogin) {
				System.out.println("\nAnda Berhasil Login\n");
				mainMenu();
			}

	}
	
	public static void mainMenu() {
		String[] listMenu = {"Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout"};
		int menuChoice = 0;
		boolean isLooping = true;
		
		do {
			PrintService.printMenu( "Booking Bengkel Menu", listMenu);
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu:", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length-1, 0);
			System.out.println(menuChoice);
			
			switch (menuChoice) {
			case 1:
				//panggil fitur Informasi Customer
				BengkelService.getInfoCustomer();
				isLooping = Validation.validasiMenu("Inputkan 0 untuk kembali ke menu: ");
				break;

			case 2:
				//panggil fitur Booking Bengkel
				PrintService.printVechicle("List Kendaraan", customerLoggedIn.getVehicles());
				String vehicleID = Validation.validasiVehicleId("Masukan Vehicle ID: ", "Data Vehicle tidak ditemukan!", Validation.regexID);
				BengkelService.bookingBengkel(vehicleID, customerLoggedIn);
				isLooping = Validation.validasiMenu("Inputkan 0 untuk kembali ke menu: ");
				break;

			case 3:
				//panggil fitur Top Up Saldo Coin
				BengkelService.topUpSaldoCoin();
				isLooping = Validation.validasiMenu("Inputkan 0 untuk kembali ke menu: ");
				break;

			case 4:
				//panggil fitur Informasi Booking Order
				PrintService.showAllBookingInformation("Informasi Booking Order", listBookingOrder);
				isLooping = Validation.validasiMenu("Inputkan 0 untuk kembali ke menu: ");
				break;

			case 0:
				BengkelService.logout();
				isLooping = false;
				break;

			default:
				System.out.println("Input tidak dimengerti");
				isLooping = false;
				break;
			}
		} while (isLooping);
	}
}
