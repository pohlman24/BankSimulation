import java.util.*;

public class Customer {
	private long entryTime, tellerTime, departureTime;
	private int serviceTime;
	
	/*
	 * create a new customer where their entry time and service time are determined by a random number generator 
	 */
	public Customer(long currentTime) {
		this(currentTime, new Random());
	}
	
	/*
	 * create a new customer where their entry time and service time are determined using numbers generated from the given random
	 * number generator
	 * 
	 * currentTime = the current time in the simulation
	 * generator = a random number generator to use for generating arrival and service time
	 */
	public Customer(long currentTime, Random generator) {
		entryTime = currentTime + generator.nextInt(299) + 1;
		serviceTime = generator.nextInt(510) + 90; // makes minimum of 90 and max of 600 
		tellerTime =  0;
		departureTime = 0;
	}
	/*
	 * the amount of time the customer spent waiting in line
	 */
	public long getWaitTime() {
		return departureTime - entryTime - serviceTime;
	}
	
	/*
	 * start service for this customer, determines the departureTime in doing so 
	 */
	public void startService(long currentTime) {
		tellerTime = currentTime;
		departureTime = currentTime + serviceTime; 
	}
	
	/*
	 * test if the customer is finished with their service 
	 * returns true if the customer has/is departing the simulation
	 */
	public boolean isFinished(long currentTime) {
		return currentTime >= departureTime;
	}
	
	/*
	 * return the service time for the customer
	 */
	public long getServiceTime() {
		return serviceTime;
	}
	
	/*
	 * return the entry time into the simulation for this customer
	 */
	public long getEntryTime() {
		return entryTime;
	}
}
