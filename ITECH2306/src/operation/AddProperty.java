package operation;
/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Concrete class of screen that extends FunctionalDialog and allows for the addition of a Property for a RatePayer.
 */
import java.util.Scanner;

public class AddProperty extends FunctionalDialog {

	public AddProperty(Scanner console) {
		super(1,console);
	}

	@Override
	public void obtainInput(int i) {
		System.out.println("Not implemented yet");
		setStillRunning(false);	
	}

	@Override
	public void respondToInput() {
		// TODO Auto-generated method stub
		
	}

}
