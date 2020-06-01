package operation;
/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Entry point of Council System. The main creates an object of this class and then runs
 * the method to startApplication. The startApplication method creates a new MainMenu object
 * and executes the operateMenu() method on that object.
 */
public class CouncilSystem {

	public static void main(String[] args) {
		CouncilSystem cs = new CouncilSystem();
		cs.startApplication();
	}

	private void startApplication() {
		MainMenu mm = new MainMenu();
		mm.operateMenu();	
	}

}
