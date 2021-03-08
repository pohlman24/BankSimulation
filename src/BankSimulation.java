import java.util.*;

/**
*
* This is the main class to simulate a bank with a single
* waiting line and a number of tellers. The tellers are
* available to any customer that is waiting but can only handle
* a single customer at a time...
*/
public class BankSimulation {
	private ArrayList line;
	private Teller[] bankTellers; // creates an array named BankTallers that can only store the Teller object 
	private Customer nextArrival;
	private long currentTime;
	
	public BankSimulation(int numberOfTellers) {
		int currentTeller;
		
		line = new ArrayList();
		bankTellers = new Teller[numberOfTellers]; // activates the bankTeller variable with a given number of tellers 
		for (currentTeller = 0;currentTeller < numberOfTellers; currentTeller++) {
			bankTellers[currentTeller] = new Teller();
		}
		// Generate the arrival time for the first customer...
		currentTime = 0;
		nextArrival = new Customer(currentTime);
	}
	
	public void simulateEvent() {
		long timeOfNextEvent;
		int currentTeller;
		
		timeOfNextEvent = nextArrival.getEntryTime();
		for (currentTeller = 0;currentTeller < bankTellers.length;currentTeller++) {
			if(bankTellers[currentTeller].isBusy()) {
				if (bankTellers[currentTeller].getFinishTime() < timeOfNextEvent) {
					timeOfNextEvent = bankTellers[currentTeller].getFinishTime();
				}	
			}
		}
		/*
		* If the event is an arrival then go ahead and
		* add it to a queue. Update the current time
		* for this simulation regardless.
		*/
		if (timeOfNextEvent == nextArrival.getEntryTime()) {
			currentTime = timeOfNextEvent;
			line.add(nextArrival);
			nextArrival = new Customer(currentTime);
		} 
		else {
			currentTime = timeOfNextEvent;
		}
		/*
		* Check all the tellers and update them to the
		* current time. If the teller is idle and
		* somebody is waiting in line start that customers
		* service.
		*/
		for (currentTeller = 0;currentTeller < bankTellers.length;currentTeller++) {
			bankTellers[currentTeller].updateTime(currentTime);
			if (bankTellers[currentTeller].isIdle() && !line.isEmpty()) {
				bankTellers[currentTeller].addCustomer((Customer)line.remove(0), currentTime);
			}
		}
	}
	
	public void simulateUntil(long endTime) {
		int currentTeller;
		
		while (currentTime < endTime) {
			simulateEvent();
		}
		for (currentTeller = 0;currentTeller < bankTellers.length;currentTeller++){
				System.out.println("Bank Teller " + currentTeller + " has served " + bankTellers[currentTeller].getCustomersServed() + 
						" customers" + " and was idle for " + bankTellers[currentTeller].getIdleTime() + " seconds.");
		}
		System.out.println("There are currently " + line.size()+" customers in line");
	}
	
	public static void main (String arguments[]) {
		BankSimulation test = new BankSimulation(2);
		test.simulateUntil(4 * 3600);// Simulate for 4 hrs
		System.exit(0);
		}

}
