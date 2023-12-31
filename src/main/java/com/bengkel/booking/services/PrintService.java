package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;
import com.bengkel.booking.util.Util;

public class PrintService {

	public static void printMenu(String title, String[] listMenu) {
		int number = 1;
		String formatTabel = "| %-3s | %-48s |%n";
		System.out.println(title);
		System.out.println("==========================================================");
		System.out.printf(formatTabel, "No", "Menu");
		System.out.println("==========================================================");

		for (String menu : listMenu) {
			if (number == listMenu.length) {
				System.out.printf(formatTabel, 0, menu);
			}else {
				System.out.printf(formatTabel, number, menu);
			}
			number++;
		}

		System.out.println("==========================================================");
	}
	
	public static void printVechicle(String title, List<Vehicle> listVehicle) {
		int number = 1;
		String formatTable = "| %-4s | %-12s | %-10s | %-15s | %-8s | %n";
		System.out.println("=================================================================");
		System.out.format("| %-61s | %n", title);
		System.out.println("=================================================================");
		System.out.printf(formatTable, "No", "Vehicle ID", "Warna", "Tipe Kendaraan", "Tahun");
		System.out.println("=================================================================");
		String vehicleType = "";
		for (Vehicle vehicle : listVehicle) {
			if (vehicle instanceof Car) {
				vehicleType = "Car";
			}else {
				vehicleType = "Motocycle";
			}
			System.out.format(formatTable, number, vehicle.getVehiclesId(), vehicle.getColor(), vehicleType, vehicle.getYearRelease());
			number++;
		}
		System.out.println("=================================================================");


	}

	public static void showAllServices(String title, List<ItemService> listServices) {
		int number = 1;
		String formatTable = "| %-4s | %-14s | %-15s | %-15s | %-10s | %n";
		System.out.println("=========================================================================");
		System.out.format("| %-72s | %n", title);
		System.out.println("=========================================================================");
		System.out.printf(formatTable, "No", "Service ID", "Nama Service", "Tipe Kendaraan", "Harga");
		System.out.println("=========================================================================");
		for (ItemService service : listServices) {
			System.out.format(formatTable, number, service.getServiceId(), service.getServiceName(), service.getVehicleType(), Util.formatCurrency(service.getPrice()));
			number++;
		}
		System.out.println("=========================================================================");

	}

	public static void showAllBookingInformation(String title, List<BookingOrder> listBookingOrder) {
		int number = 1;
		String formatTable = "| %-4s | %-17s | %-15s | %-15s | %-14s | %-14s | %-30s | %n";
		System.out.println("===================================================================================================================================");
		System.out.format("| %-127s | %n", title);
		System.out.println("===================================================================================================================================");
		System.out.printf(formatTable, "No", "Booking ID", "Nama Customer", "Payment Method", "Total Service", "Total Payment", "List Service");
		System.out.println("===================================================================================================================================");
		for (BookingOrder order : listBookingOrder) {
			System.out.format(formatTable, number, order.getBookingId(), order.getCustomer().getName(), order.getPaymentMethod(), Util.formatCurrency(order.getTotalServicePrice()), Util.formatCurrency(order.getTotalPayment()), printServices(order.getServices()));
			number++;
		}
		System.out.println("===================================================================================================================================");

	}

	public static String printServices(List<ItemService> serviceList){
		String result = "";
		for (ItemService service : serviceList) {
			result += service.getServiceName() + ", ";
		}
		return result;
	}

}
