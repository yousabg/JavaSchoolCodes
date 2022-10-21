/* Name: Yousab Grees
# Date: 04/19/2022
# Class: CSC 1120
# Pledge: I have neither given nor received unauthorized aid on this
program.
# Description: This is a program that mimcks an airlines program. Allowing the user to
create an account, and from that account, choose from various options that
let the user buy a plane ticket, view available flights, etc.
# Input: The user types inputs asked by the program. For the account creation,
a name, birthday, password, and pin will be asked. Afterwards, the menu options
will be given and a user will type which option they want to select.
# Output:The program will tell the user if the password inputted is a palindrome
or not. Based on the user menu option selection, it could also show available flights,
tickets, etc.
*/
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        String name, birthday, password, response;
        Random random = new Random();
        int pin;
        boolean done = false, format = true;

        System.out.println("--------------------------------------------------------------");
        System.out.println("                       aeroAirLines"); //Starting by printing an introduction
        System.out.println("--------------------------------------------------------------");
        System.out.println("Thanks for choosing aeroAirlines!\nWe currently offer 10 flights, and you can view these from the account menu.");
        System.out.println("To begin, please enter an account name.");
        name = input.nextLine();
        System.out.println("Next, please enter your birthday in the format DD/MM/YYYY");
        birthday = input.nextLine();
        try { //First attempt to input birthday in a try/catch statement
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday); //Creating date using date class in the simple date format (another class)
        } catch (ParseException e) {
            System.out.println("That format was incorrect. Please input in the format DD/MM/YYYY");
            birthday = input.nextLine();
            format = false; //If an exception was caught, format will be false, and the program continues to the while loop.
        }
        while (!format) { //program will stick to this while loop until try is successful (when format is equal to true).
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
                format = true;
            } catch (ParseException e) {
                System.out.println("That format was incorrect. Please input in the format DD/MM/YYYY"); //Print this statement if exception is caught
                birthday = input.nextLine();
            }
        }
        System.out.println("Please enter the account password.");
        password = input.nextLine();
        System.out.println("Enter the 4 digit account pin?");
        try { //Making sure pin is only digits
            pin = input.nextInt();
        } catch (Exception e) {
            System.out.println("The pin must contain only numbers. A random pin has been generated. You can change your pin in the menu."); //Assign random pin if exception is caught to move on
            random = new Random();
            pin = random.nextInt(8999) + 1000;
        }
        if (pin > 9999 || pin < 1000) { //Making sure pin is only 4 digits
            System.out.println("Pin can only be 4 digits. A random pin has been generated. You can change your pin in the menu.");
            pin = random.nextInt(8999) + 1000;
        }
        System.out.println("--------------------------------------------------------------");
        Flight flight = new Flight(); //Creating a new instance of the Flight class I created
        while (!done) { //Menu continues to print until a string that is not 1-5 is stated.
            printInfo(name, birthday, password, pin);
            printMenu();
            response = input2.nextLine();
            switch (response) {
                case "1" -> flight.printAllFlights();
                case "2" -> buyAFlight(flight);
                case "3" -> flight.printFlightTicket();
                case "4" -> password = changePassword(password);
                case "5" -> pin = changePin();
                default -> {
                    System.out.println("Thank you for choosing aeroAirlines. We hope you have a good day!");
                    done = true;
                }
            }
        }
    }

    /*
    Checks to see if the password inputted is a palindrome (the word is the same backwards).
    Parameters: password, the password to be checked
    Return: true, if password is a palindrome. false, if password is not a palindrome.
     */
    public static boolean check_Palindrome(String password) {
        password = password.toLowerCase();
        String bwPassword = "";

        for (int i = password.length() - 1; i >= 0; i--) { //Creates a new string of the backwards version of password
            bwPassword += password.charAt(i);
        }

        return password.equals(bwPassword); //Returns true if the new string equals password, or false if not.
    }
    /*
    Prints out the account information, and prints if the password is a palindrome using check_Palindrome
    Parameters: name, account name, birthday, account birthday, password, account password, pin, account pin
     */
    public static void printInfo(String name, String birthday, String password, int pin) {
        System.out.println(name + "'s Account details:");
        System.out.println(" Birthday: " + birthday);
        System.out.println(" Password: " + password);
        System.out.println(" Pin: " + pin);
        if (check_Palindrome(password)) {//Uses check_Palindrome method to see if the password is a palindrome, then prints if it is or isn't.
            System.out.println("Your password " + password + " is a palindrome.");
        } else {
            System.out.println("Your password " + password + " is not a palindrome.");
        }
        System.out.println(" ");
    }
    /*
    Prints out the collection of menu options
     */
    public static void printMenu() {
        System.out.println("Please choose one of the menu options:");
        ArrayList<String> menu = new ArrayList<>(); //Creates arraylist of menu and adds different options, then prints it out in a for loop.
        menu.add("1 : View available flights");
        menu.add("2 : Book a ticket");
        menu.add("3 : View your ticket");
        menu.add("4 : Change your password");
        menu.add("5 : Change your pin");
        menu.add("6 : Quit");

        for (String menuOptions : menu) {
            System.out.println(menuOptions);
        }
    }

    /*
    Simulates buying a flight ticket
    Parameters: flight, The flight instance where the current flight needs to be changed.
     */
    public static void buyAFlight(Flight flight) {
        Scanner input = new Scanner(System.in);
        flight.printAllFlights();
        System.out.println("Which flight would you like to buy?");
        int choice = input.nextInt();
        choice = choice - 1;
        while (choice < 0 || choice > 9) { //Makes sure flight chosen is one of the options
            System.out.println("We can't seem to find that flight. Please pick again.");
            choice = input.nextInt();
        }
        flight.changeFlight(choice);
        System.out.println("Transaction completed!");
        System.out.println("Here's your ticket: ");
        flight.printFlightTicket();
    }

    /*
    Changes the account password by first verifying if the user knows the original password (added the feature for fun).
    Parameters: password, the old password to be changed.
    Return: the new password.
     */
    public static String changePassword(String password) {
        Scanner input = new Scanner(System.in);
        boolean rightPassword = false;
        while (!rightPassword) {
            System.out.println("Please enter your old password.");
            String response = input.nextLine();
            if (response.equals(password)) { //Continues until password printed is same as current password
                rightPassword = true;
            } else {
                System.out.println("Incorrect password.");
            }
        }
        System.out.println("Correct password. Please enter the new password.");
        String newPassword = input.nextLine();
        System.out.println("The new password is " + newPassword);
        if (check_Palindrome(newPassword)) { //Prints out new password, along with is palindrome or not.
            System.out.println("Your password " + newPassword + " is a palindrome.");
        } else {
            System.out.println("Your password " + newPassword + " is not a palindrome.");
        }
        return newPassword;
    }
    /*
    Changes the user pin. (Added this option because I was assigning random pins if incorrect)
    Return: The new pin
     */
    public static int changePin() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your new pin.");
        int newPin;
        if (input.hasNextInt()) { //Makes sure pin printed contains only numbers, otherwise default pin of 0 is set so the while loop can start.
            newPin = input.nextInt();
        } else {
            newPin = 0;
        }
        while (newPin > 9999 || newPin < 1000) {
            System.out.println("Error: Pin cannot contain letters and must be 4 digits. Please try again.");
            if (input.hasNextInt()) {
                newPin = input.nextInt();
            } else {
                input.nextLine();
            }
        }
        return newPin;
    }
}
