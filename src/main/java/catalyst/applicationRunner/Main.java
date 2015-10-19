package catalyst.applicationRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import catalyst.applicationRunner.daos.impl.ToDoArrayList;
import catalyst.applicationRunner.services.impl.ToDoServiceImpl;

@SpringBootApplication
public class Main {

	public static void main(String[] args) 
	{
		SpringApplication.run(Main.class);
		/*ToDoApp toDoApp = new ToDoApp();
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
		}while (userInput != 12);
		
		toDoApp.scan.close();
		System.out.println("\nGoodbye!");
		System.exit(0);	*/
	}

}

