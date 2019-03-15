import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TSG_Transaction {

	public boolean doPaymnet(Date inTime) {
		Scanner scan = new Scanner(System.in);
		int price = 10;
		double park_time = 0;
		double amount;
		String card_number = null;

		try {
			Date outTime = new Date();
			double parkingHrs = calculateParkingHours(inTime, outTime);
			amount = price * parkingHrs;
			System.out.println("Your parking price is: " + amount);
     
			System.out.println("\n\nEnter Debit/Credit card number(16 digit):");
			card_number = scan.next();
			if (isNumeric(card_number)) {
				System.out.println("\nPayment Successful!!\n");
				System.out.println("Have a nice day!!\n Welcome Again!!\n");
			return true;
			}
			else {
			System.out.println("\nInvalid Card Number!! \n\n");
			return false;
			}
		}

		catch (Exception e) {
			System.out.println("Try again");

		}
		return false;

	}

	public boolean isNumeric(String cardNu) {
		return cardNu != null && cardNu.matches("[0-9]+") &&  cardNu.length() == 16;
	}

	private double calculateParkingHours(Date inTime, Date outTime) {
		// Date outTime;
		double parkingHrs = outTime.getSeconds() - inTime.getSeconds();
		return parkingHrs;

	}

}
