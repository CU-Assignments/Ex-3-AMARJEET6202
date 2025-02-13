/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;

// Base class for an Account Holder
class AccountHolder {
    protected String name;
    protected String accountNumber;

    public AccountHolder(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + name);
        System.out.println("Account Number: " + accountNumber);
    }
}

// Abstract class for Bank Deposit
abstract class BankDeposit extends AccountHolder {
    protected double rate;
    protected int time;

    public BankDeposit(String name, String accountNumber, double rate, int time) {
        super(name, accountNumber);
        this.rate = rate;
        this.time = time;
    }

    // Abstract method to calculate interest
    public abstract double calculateInterest();
}

// Fixed Deposit (FD) class
class FixedDeposit extends BankDeposit {
    private double principal;

    public FixedDeposit(String name, String accountNumber, double principal, double rate, int time) {
        super(name, accountNumber, rate, time);
        this.principal = principal;
    }

    @Override
    public double calculateInterest() {
        // Compound Interest formula: A = P(1 + r/100)^t
        double amount = principal * Math.pow((1 + rate / 100), time);
        return amount - principal;
    }

    // Display FD details
    public void displayFDDetails() {
        displayAccountDetails();
        System.out.println("Fixed Deposit Principal: " + principal);
        System.out.println("Interest Rate: " + rate + "%");
        System.out.println("Duration: " + time + " years");
        System.out.printf("FD Interest Earned: %.2f%n", calculateInterest());
    }
}

// Recurring Deposit (RD) class
class RecurringDeposit extends BankDeposit {
    private double monthlyInstallment;

    public RecurringDeposit(String name, String accountNumber, double monthlyInstallment, double rate, int time) {
        super(name, accountNumber, rate, time);
        this.monthlyInstallment = monthlyInstallment;
    }

    @Override
    public double calculateInterest() {
        // RD Interest formula: Interest = P * n(n+1) * r / (2 * 12 * 100)
        int n = time * 12; // Total months
        return (monthlyInstallment * n * (n + 1) * rate) / (2 * 12 * 100);
    }

    // Display RD details
    public void displayRDDetails() {
        displayAccountDetails();
        System.out.println("Recurring Deposit Monthly Installment: " + monthlyInstallment);
        System.out.println("Interest Rate: " + rate + "%");
        System.out.println("Duration: " + time + " years");
        System.out.printf("RD Interest Earned: %.2f%n", calculateInterest());
    }
}

// Main class to run the application
public class BankInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for Fixed Deposit
        System.out.println("Enter Fixed Deposit Details:");
        System.out.print("Account Holder Name: ");
        String fdName = scanner.nextLine();
        System.out.print("Account Number: ");
        String fdAccountNumber = scanner.nextLine();
        System.out.print("Principal Amount: ");
        double fdPrincipal = scanner.nextDouble();
        System.out.print("Interest Rate (%): ");
        double fdRate = scanner.nextDouble();
        System.out.print("Time Period (years): ");
        int fdTime = scanner.nextInt();

        // Creating FD object
        FixedDeposit fd = new FixedDeposit(fdName, fdAccountNumber, fdPrincipal, fdRate, fdTime);
        System.out.println("\n*** Fixed Deposit Details ***");
        fd.displayFDDetails();

        // Input for Recurring Deposit
        scanner.nextLine(); // Consume newline
        System.out.println("\nEnter Recurring Deposit Details:");
        System.out.print("Account Holder Name: ");
        String rdName = scanner.nextLine();
        System.out.print("Account Number: ");
        String rdAccountNumber = scanner.nextLine();
        System.out.print("Monthly Installment: ");
        double rdInstallment = scanner.nextDouble();
        System.out.print("Interest Rate (%): ");
        double rdRate = scanner.nextDouble();
        System.out.print("Time Period (years): ");
        int rdTime = scanner.nextInt();

        // Creating RD object
        RecurringDeposit rd = new RecurringDeposit(rdName, rdAccountNumber, rdInstallment, rdRate, rdTime);
        System.out.println("\n*** Recurring Deposit Details ***");
        rd.displayRDDetails();

        scanner.close();
    }
}
