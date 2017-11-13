/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopky;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukstankovic
 */
public class Watch implements Runnable {

	private int startTime; 
	private int endTime;
	
	private int minutes;
	private int seconds;
	private int miliSeconds;
	
	private String timeString;
	
	private WatchGUI window;
	
	public Watch(WatchGUI window) {
		this.startTime = 0;
		this.window = window;
		reset();
	}
	
	
	@Override
	public void run() {
		while (true) {
			try {
				miliSeconds++;
				if (miliSeconds > 999) {
					miliSeconds = 0;
					seconds++;
				}
				
				if (seconds > 59) {
					seconds = 0;
					minutes++;
				}
				
				if (minutes > 59) {
					reset();
				}
				
				setTimerString();
				display();
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				System.err.println("Chyba!");
			}
		}
	}
	
	private void startWatch() {
		
	}
	
	public void reset() {
		minutes = 0;
		seconds = 0;
		miliSeconds = 0;
		timeString = "0:00,000";
	}
	
	public void display() {
		window.getTime().setText(timeString);
	}
	
	private void setTimerString() {
		timeString = minutes + ":";
		
		if (seconds < 10) {
			timeString += "0" + seconds + ",";
		} else {
			timeString += seconds + ",";
		}
		
		if (miliSeconds < 10) {
			timeString +=  "00" + miliSeconds;
		} else if (miliSeconds < 100) {
			timeString +=  "0" + miliSeconds;
		} else {
			timeString += miliSeconds;
		}
		
		
	}
}
