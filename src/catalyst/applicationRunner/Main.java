package catalyst.applicationRunner;

import catalyst.presentation.ToDoApp;

public class Main {

	public static void main(String[] args) 
	{
		ToDoApp toDoApp = new ToDoApp();
		
		toDoApp.displayMenu();
		int userInput = toDoApp.getInput();
		
		while (userInput != 9)
		{
		     toDoApp.userChoice(toDoApp.getInput());
		     toDoApp.displayMenu();
		     
		}
		
		System.exit(0);	
	}

}
