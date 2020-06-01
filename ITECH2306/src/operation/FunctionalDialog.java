package operation;
/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Abstract class specifying an iterative getInput method that must be run i times
 * Once the iterations are complete some action is taken.
 * As it is a text based client interface we include validation after each input instead
 * of at the end of all iterations or when an action button is pressed.
 */
import java.util.Scanner;

public abstract class FunctionalDialog {
	private Scanner console;
	private boolean stillRunning;
	private int numberOfInputs;
	private String title;

	public FunctionalDialog(int noOfInputs, Scanner console) {
		this.setNumberOfInputs(noOfInputs);
		this.setScanner(console);
	}
	
	protected void operateDialog() {
		setStillRunning(true);
		System.out.println(getTitle());
		while (getStillRunning())
		{
			for(int i= 0; i < getNumberOfInputs(); i++) {
				obtainInput(i);
				if (!getStillRunning())
					break;
			}
			if (getStillRunning())
				respondToInput();
		}
		return;
	}

	private void setScanner(Scanner console) {
		this.console = console;
	}
	
	protected Scanner getScanner() {
		return this.console;
	}
	
	private boolean getStillRunning() {
		return this.stillRunning;
	}

	protected void setStillRunning(boolean b) {
		this.stillRunning = b;
	}

	protected abstract void obtainInput(int i);
		
	protected abstract void respondToInput();
	
	protected void setNumberOfInputs(int noOfInputs) {
		this.numberOfInputs = noOfInputs;
	}
	protected int getNumberOfInputs() {
		return this.numberOfInputs;
	}

	public void setTitle(String title) {
		this.title = title;		
	}
	public String getTitle() {
		return this.title;		
	}

}
