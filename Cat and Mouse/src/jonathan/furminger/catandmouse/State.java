package jonathan.furminger.catandmouse;

public interface State {

	public void enter();
	
	public void performAction();
	
	public void exit();
}
