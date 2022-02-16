package jonathan.furminger.catandmouse;

public class CatHuntState implements State {
	
	private Cat cat;
	
	public CatHuntState(Cat cat) {
		this.cat = cat;
	}

	@Override
	public void enter() {
		cat.setSpeed(Cat.SPEED_HUNT);
	}

	@Override
	public void performAction() {
		cat.hunt();
	}

	@Override
	public void exit() {
		cat.moveIntoCell();
	}

}
