package jonathan.furminger.catandmouse;

public class MouseWaitState implements State {

	private Mouse mouse;
	
	public MouseWaitState(Mouse mouse) {
		this.mouse = mouse;
	}
	
	@Override
	public void enter() {
		mouse.stop();
	}

	@Override
	public void performAction() {
		
	}

	@Override
	public void exit() {
		
	}

}
