import java.util.TimerTask;

public class SecondsHelper extends TimerTask {
	public void run(Seconds seconds)
	{
		seconds.setSeconds(seconds.getSeconds()-1);
		//secondsNumber.setText(Integer.toString(seconds.getSeconds()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
