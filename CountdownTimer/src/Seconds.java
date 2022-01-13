
/**
 * This class creates an instance of Seconds,
 * which holds the number of seconds elapsed
 * @author jonathanfurminger
 *
 */

public class Seconds {

	public int seconds;
	public boolean secondsUpdated;
	
	/**
	 * Constructor for object Seconds
	 */
	public void Seconds() {
		seconds = 0;
		secondsUpdated = false;
		
	}
	
	/**
	 * Getter, returns the value of Seconds
	 * @return int representing the number of seconds remaining
	 */
	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * Getter for SecondsUpdated flag
	 */
	public boolean isSecondsUpdated() {
		return secondsUpdated;
	}
	
	/**
	 * Setter for Seconds
	 * @param seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
		secondsUpdated = true;
	}
	
	/**
	 * Setter for secondsUpdated
	 * @param secondsUpdated
	 */
	public void setSecondsUpdated(boolean isSecondsUpdated) {
		secondsUpdated = isSecondsUpdated;
	}
	
}
