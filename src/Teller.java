
/*
* This class represents a teller in the simulation. A teller
* who is idle may have a new customer added and then will
* remain busy until the service for that customer is finished.
* In addition, the current time for a Teller may be updated
* by passing a new (revised?) time to the teller.
*/

public class Teller {
	private Customer currentCustomer;
	private long idleTime, busyTime;
	private long finishTime;
	private int customersServed;
	
	/*
	 * creates a teller. Originally the teller has no idle time or busy time and no customer to wait on
	 */
	public Teller() {
		idleTime = 0;
		busyTime = 0;
		currentCustomer = null;
		finishTime = 0;
		customersServed = 0;
	}
	
	public long getIdleTime() {
		return idleTime;
	}
	
	public int getCustomersServed() {
		return customersServed;
	}
	
	public void addCustomer(Customer nextCustomer, long currentTime) {
		if(currentCustomer == null) { // if the teller isn't busy with a currentCustomer
			currentCustomer = nextCustomer;
			idleTime = idleTime + currentTime - finishTime;
			finishTime = currentTime + currentCustomer.getServiceTime();
		}
		else {
			System.out.println("Teller busy -- Customer can't be helped");
		}
		
	}
	
	public void updateTime(long currentTime) {
		if(currentTime == finishTime) {
			busyTime = busyTime + currentCustomer.getServiceTime();
			customersServed++;
			currentCustomer = null; // sets the teller to open
		}
		else {}
	}
	
	public long getFreeTime(long currentTime) {
		if(currentCustomer == null) {
			return currentTime;
		}
		else {
			return finishTime;
		}
	}
	
	public boolean isBusy() {
		return currentCustomer != null;
	}
	
	public boolean isIdle() {
		return currentCustomer == null;
	}
	
	public long getFinishTime() {
		return finishTime;
	}
	
	
	
	
	
	
	
}
