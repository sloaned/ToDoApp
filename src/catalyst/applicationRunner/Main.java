package catalyst.applicationRunner;

import catalyst.application.ToDoServiceImpl;
import catalyst.data.ToDoArrayList;
import catalyst.presentation.ToDoApp;

public class Main {

	public static void main(String[] args) 
	{
		ToDoApp toDoApp = new ToDoApp();
		ToDoArrayList toDoData = new ToDoArrayList();
		ToDoServiceImpl toDoService = new ToDoServiceImpl();
		toDoApp.setToDoService(toDoService);
		toDoService.setToDoData(toDoData);
		
		
		toDoApp.displayMenu();
		int userInput = toDoApp.getInput();
		
		while (userInput != 9)
		{
		     toDoApp.userChoice(userInput);
		     toDoApp.displayMenu();
		}
		
		System.exit(0);	
	}

}

