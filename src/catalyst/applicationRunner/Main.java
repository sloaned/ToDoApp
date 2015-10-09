package catalyst.applicationRunner;

import catalyst.application.ToDoServiceImpl;
import catalyst.data.ToDoArrayList;
import catalyst.presentation.ToDoApp;

public class Main {

	public static void main(String[] args) 
	{
		ToDoApp toDoApp = new ToDoApp();
		ToDoArrayList toDoArrayList = new ToDoArrayList();
		ToDoServiceImpl toDoService = new ToDoServiceImpl();
		toDoApp.setToDoService(toDoService);
		toDoService.setToDoData(toDoArrayList);
		
		int userInput = 0;
		
		do
		{
			 toDoApp.displayMenu();
			 userInput = toDoApp.getInput();
		     toDoApp.userChoice(userInput);
		}while (userInput != 9);
		
		System.exit(0);	
	}

}

