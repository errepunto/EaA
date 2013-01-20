package es.errepunto.eaa.events;


/**
 * Main interface for events.
 * 
 * @author ruben
 *
 */
public interface Event {
	
	/**
	 * Checks if the event is active
	 * @return true if the event is active or false if is inactive
	 */
	public boolean isActive();
	
	/**
	 * Gather data from his sources and stores it internally. It' should check if the event is active afterwards
	 * 
	 * @return true if data is obtained or false if some error occurred.
	 */
	public boolean obtainData();
	
	
	/**
	 * Obtains last error message.
	 * @return String with the last error message.
	 */
	public String getLastError();
}
