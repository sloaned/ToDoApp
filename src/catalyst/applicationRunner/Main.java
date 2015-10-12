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
		
		System.out.println("Welcome to the To-Do List App!");
		
		int userInput = 0;
		
		do
		{
			 toDoApp.displayMenu();
			 userInput = toDoApp.getInput();
		     toDoApp.userChoice(userInput);
		}while (userInput != 11);
		
		toDoApp.scan.close();
		System.out.println("\nGoodbye!");
		System.exit(0);	
	}

}

