
import java.util.Date;

public class TSG_Transaction {

	public double getParkingPrice(double parkingHrs) {
		int price = 10;

		double amount = price * parkingHrs / 3600;
		amount = Math.round(amount * 100.0) / 100.0;
		System.out.println("Your parking price is: " + amount);

		return amount;
	}

	public boolean doPaymnet(String vehicleNumber, double amount, String cardNumber, double parkingHrs) {
		// int price = 10;

		try {
			if (isNumeric(cardNumber)) {
				System.out.println("\nPayment Successful!!\n");
				showReceipt(vehicleNumber, parkingHrs, amount);
				return true;
			} else {
				System.out.println("\nInvalid Card Number!! \n");
				return false;
			}
		}

		catch (Exception e) {
			System.out.println("Try again");

		}
		return false;

	}

	public static void showReceipt(String vehicleNumber, double parkingHrs, double amount) {
		System.out.println("*************** Receipt ******************");
		System.out.println("Registration Number is: " + vehicleNumber);
		System.out.println("Parking Time is: " + parkingHrs + " seconds");
		System.out.println("Parking Amount: " + amount + " SEK");
		System.out.println("******************************************\n");
		System.out.println("Have a nice day!!\nWelcome again\n\n");
	}

	public boolean isNumeric(String cardNu) {
		return cardNu != null && cardNu.matches("[0-9]+") && cardNu.length() == 16;
	}

	long calculateParkingHours(Date inTime, Date outTime) {
		long parkingSeconds = (outTime.getTime() - inTime.getTime()) / 1000;
		return parkingSeconds;

	}
}
