import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TSG_main {

	static Scanner scan = new Scanner(System.in);

	public static void printWelcome() {
		System.out.println("     Welcome to 24Seven Garage!");
		// System.out.println("****************************************");
	}

	private static void showInfoMenu(int availableParkingCount) {
		System.out.println("****************************************");
		System.out.println("Available parking places:" + availableParkingCount);
		System.out.println("Price per hour is 10kr");
		System.out.println("****************************************");
	}

	public static void showOptionMenu() {
		// show menu 1. Park 2. exit

		System.out.println("Select Your Choice:");
		System.out.println("1: Want to park Vehicle?");
		System.out.println("2: Exit from parking?");
		System.out.println("3: Shut Down");

		System.out.print("\nChoose your Choice: ");
	}

	public static int readUsersChoice() {
		try {
			Scanner scann = new Scanner(System.in);
			return scann.nextInt();
		 	}
		 	catch(InputMismatchException e) {

		 		System.out.println("Enter valid Number!!\n");
		 	}
			return 0;
			
		}

	public static String getRegistrationNumber() {
		System.out.println("\nEnter Registration number (Follow this format:xxx12xx):");
		// Scanner scan = new Scanner(System.in);
		return scan.next();

	}

	public static boolean checkRegNumber(String v) {
		if ((v.length() == 6) || (v.length() == 7) && ((v.matches("[a-zA-Z0-9_]+")))) {
			System.out.println("You entered registration number: " + v + "\n");
			return true;
		} else {
			System.out.println("\nWrong registration number.");
			System.out.println("Try again!!\n");
			return false;
		}
	}

	public static String confirmRegistrationNumber() {
		System.out.println("\nConfirm Registration number:");
		// Scanner scan = new Scanner(System.in);
		return scan.next();

	}

	public static void main(String[] args) {

		int userChoice = 0;
		String vehicleNumber;
		Vehicle_info vehicleInfo = new Vehicle_info();
		TSG_Transaction trans = new TSG_Transaction();
		int availableParkingCount = 20;
		printWelcome();

		while (userChoice != 3) {
			availableParkingCount = 20 - vehicleInfo.getParkingCount();
			showInfoMenu(availableParkingCount);

			showOptionMenu();
			userChoice = readUsersChoice();

			switch (userChoice) {
			case 1:
				if (availableParkingCount > 0) {
					vehicleNumber = getRegistrationNumber();
					// add Vehicle in array
					if (checkRegNumber(vehicleNumber) && !vehicleInfo.checkAlreadyExist(vehicleNumber)) {
						vehicleInfo.addVehicle(vehicleNumber);
					}
				} else {
					System.out.println("Parking is Full\n");
				}

				break;

			case 2:

				vehicleNumber = getRegistrationNumber();
				// ask for payment
				Vehicle vehicleToRemove = vehicleInfo.getVehicle(vehicleNumber);
				if (vehicleToRemove != null) {
					double parkingHrs = trans.calculateParkingHours(vehicleToRemove.inTime, new Date());
					double amount = trans.getParkingPrice(parkingHrs);
					String cardNumber = getCardNumber();

					boolean isPaymentSuccessful = trans.doPaymnet(vehicleToRemove.vehicleNumber, amount, cardNumber,
							parkingHrs);

					// Remove Vehicle from array
					if (isPaymentSuccessful) {
						vehicleInfo.removeVehicle(vehicleNumber);
					} else {
						System.out.println("Try again to exit with Choice 2\n");
					}
				} else {
					System.out
							.println("No vehicle found with this number, please enter valid car registration number..");
				}
				break;

			case 3:
				userChoice = 3;

			}
		}
	}

	private static String getCardNumber() {
		System.out.println("\nEnter Debit/Credit card number(16 digit):");
		return scan.next();

	}

}
