//A class that stores flight information.
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class Flight {
    ArrayList<String> flights = new ArrayList<>();
    Random generator = new Random();
    String flightPicked;
    /*
    Constructor of class Flight, adds the flights and assigns a default flight.
     */
    public Flight()
    {
        addFlights();
        int flightChooser = generator.nextInt(10);
        flightPicked = flights.get(flightChooser);
    }
    /*
    Add flights to the arrayList flights
     */
    public void addFlights()
    {
        flights.add("1: Jersey City, NJ, to Charlotte, NC, DELTA, DEPARTING AT 1:00 PM, ARRIVING AT 4:00 PM, PRICE: $67");
        flights.add("2: Durham, NC, to Buffalo, NY, JETBLUE, DEPARTING AT 4:00 PM, ARRIVING AT 7:00 PM, PRICE: $34");
        flights.add("3: Oklahoma City, OK, to Atlanta, GA, FRONTIER AIRLINES, DEPARTING AT 5:00 PM, ARRIVING AT 7:00 PM, PRICE: $43");
        flights.add("4: Washington DC to San Jose, CA, AMERICAN AIRLINES, DEPARTING AT 1:00 PM, ARRIVING AT 3:00 PM, PRICE: $65");
        flights.add("5: Omaha, NE, to Birmingham, AL, DELTA, DEPARTING AT 1:00 PM, ARRIVING AT 3:00 PM, PRICE: $65");
        flights.add("6: Houston, TX, to Honolulu, HI, UNITED AIRLINES, DEPARTING AT 4:00 PM, ARRIVING AT 7:00 PM, PRICE: $34");
        flights.add("7: San Diego, CA, to El Paso, TX, JETBLUE, DEPARTING AT 1:00 PM, ARRIVING AT 3:00 PM, PRICE: $65");
        flights.add("8: Modesto, CA, to Dallas, TX, DELTA, DEPARTING AT 1:00 PM, ARRIVING AT 4:00 PM, PRICE: $67");
        flights.add("9: Akron, OH, to Memphis, TN, AMERICAN AIRLINES, DEPARTING AT 4:00 PM, ARRIVING AT 7:00 PM, PRICE: $34");
        flights.add("10: Nashville, TN, to Long Beach, CA, SPIRIT, DEPARTING AT 1:00 PM, ARRIVING AT 3:00 PM, PRICE: $65");
    }

    /*
    Prints all flights
     */
    public void printAllFlights()
    {
        Date theDate = new Date();
        System.out.println("Current flights for " + theDate + ":");
        for (String flight : flights) {
            System.out.println(flight);
        }
        System.out.println(" ");
    }

    /*
    Changes the currently selected flight
    Parameter: index, index of the flight selected.
     */
    public void changeFlight(int index)
    {
        flightPicked = flights.get(index);
    }

    /*
    Prints out a format of a ticket based on the currently selected flight.
     */
    public void printFlightTicket()
    {
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       YOUR TICKET:");
        System.out.println(flightPicked);
        System.out.println("--------------------------------------------------------------");
    }
}
