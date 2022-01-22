package jonathan.furminger.speedwords;

import java.awt.Font;

import jonathan.furminger.mytimer.TimerPanel;

public class SpeedWordsTimerPanel extends TimerPanel {

		private static final long serialVersionUID = 1L;
		private static final Font FONT = new Font(Font.DIALOG, Font.BOLD, 24);
		
		private SpeedWords speedWords;
		
		public SpeedWordsTimerPanel(SpeedWords speedWords, int time) {
			super(time, FONT);
			this.speedWords = speedWords;
		}

		protected void timesUp() {
			speedWords.outOfTime();
		}
		
}
