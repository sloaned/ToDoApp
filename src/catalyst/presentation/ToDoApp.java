package catalyst.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import catalyst.application.ToDoItem;
import catalyst.application.ToDoService;

public class ToDoApp 
{
	// dependency to ToDoService interface
	private ToDoService toDoService;
	
	public void setToDoService(ToDoService toDoService) 
	{
		this.toDoService = toDoService;
	}
	
	Scanner scan = new Scanner(System.in);

	public void displayMenu()
	{
		System.out.println("");
		System.out.println("Please select from the following options");
		System.out.println("");
		System.out.println("1) What's on my list today?");
		System.out.println("2) Time to add some new tasks");
		System.out.println("3) I need to fix a task!");
		System.out.println("4) I gotta get rid of something.");
		System.out.println("5) I finished something!");
		System.out.println("6) Just kidding, I didn't finish something...");
		System.out.println("7) Show me what I have left to do!");
		System.out.println("8) Show me what I've done, I need a confidence boost...");
		System.out.println("");
		System.out.println("9) Ok, I'm finished here");
		System.out.println("");
	}
			
	public int getInput()
	{
		boolean notValid = true;
		String rawEntry = null;
		int entryChoice = 0;
		
		System.out.println("Please enter your choice: ");
	
		do
		{
			rawEntry = scan.nextLine();
			try 
            {
				entryChoice = Integer.parseInt(rawEntry);
                notValid = false;
            } 
            catch (Exception e) 
            {
                System.out.println("Must be a number.  Try again.");
            }
			
		}while (notValid);

		return entryChoice;		
	}
	
	public void showLeftToDo()
	{
		toDoService.getComplete();
		
	}
	
	public void showCompleted()
	{
		toDoService.getIncomplete();
	}
	
	public void getNewTask()
	{
		String newTask = null;
		String getDate = null;
		boolean inProgress = false;
		String newUser = null;
		String rawInput = null;
		
		
		System.out.println("Enter a new task: ");
		newTask = scan.nextLine();
		
		
		System.out.println("Enter the Due Date: ");
		getDate = scan.nextLine();
		Date newDate = new Date ();
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    try 
	    {
	      newDate = format.parse(getDate);
	    } 
	    catch (ParseException e) 
	    {
	      e.printStackTrace();
	    }
		
	    System.out.println("Is this task in progress? (yes or no): ");
		rawInput = scan.nextLine();
		if (rawInput.equals("yes") || rawInput.equals("y"))
		{
			inProgress = true;
		}
		else if (rawInput.equals("no") || rawInput.equals("n"))
		{
			inProgress = false;
		}
		
		System.out.println("Who has to finish this task?: ");
		newUser = scan.nextLine();
		
		toDoService.add(newTask, inProgress, newUser, newDate);
		
		//scan.close();
	}
	
	public void removeListItem()
	{
		getList();
		
		int rawInput = getInput();
		ArrayList<ToDoItem> toDo = toDoService.getAll();
		
		
		while(rawInput > toDo.size() || rawInput < 0)
		{
			System.out.println("That is not an item in the list. Try again");
			rawInput = getInput();
		}
		
		toDoService.remove(rawInput);
	}
	
	public void updateTask()
	{
		getList();
		int userEntry = getInput();
		
		String updateTask = null;
		String getDate = null;
		boolean inProgress = false;
		String updateUser = null;
		String updateInput = null;
		boolean isComplete = false;
		
		
		System.out.println("Update the task: ");
		updateTask = scan.nextLine();
		
		
		System.out.println("Enter the new Due Date: ");
		getDate = scan.nextLine();
		Date updateDate = new Date ();
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    try 
	    {
	      updateDate = format.parse(getDate);
	    } 
	    catch (ParseException e) 
	    {
	      e.printStackTrace();
	    }
		
	    System.out.println("Is this task in progress? (yes or no): ");
		String rawInput = scan.nextLine();
		
		if (rawInput == "yes"|| rawInput == "y")
		{
			inProgress = true;
			
		}
		else if (rawInput.equals("no") || rawInput.equals("n"))
		{
			System.out.println("Is this task complete?");
			String nextRawInput = scan.nextLine();
			
			if (nextRawInput.equals("yes") || nextRawInput.equals("y"))
			{
				isComplete = true;
			}
			else if (nextRawInput.equals("no") || nextRawInput.equals("n"))
			{
				isComplete = false;
			}
			
			if (isComplete == false)
			{
				System.out.println("Who has to finish this task?: ");
				updateUser = scan.nextLine();
			}
			else if (isComplete == true)
			{
				System.out.println("Who finished this task?: ");
				updateUser = scan.nextLine();
			}
			inProgress = false;
		}
		
		toDoService.update(userEntry, updateTask, inProgress, isComplete, updateUser, updateDate);
	}
	
	public void markTaskComplete()
	{
		getList();
		
		int rawInput = getInput();
		ArrayList<ToDoItem> toDo = toDoService.getAll();
		
		
		while(rawInput > toDo.size() || rawInput < 0)
		{
			System.out.println("That is not an item in the list. Try again");
			rawInput = getInput();
		}
		
		toDoService.markComplete(rawInput);
	}
	
	public void markTaskIncomplete()
	{
		getList();
		
		int rawInput = getInput();
		ArrayList<ToDoItem> toDo = toDoService.getAll();
		
		
		while(rawInput > toDo.size() || rawInput < 0)
		{
			System.out.println("That is not an item in the list. Try again");
			rawInput = getInput();
		}
		
		toDoService.markIncomplete(rawInput);
	}
	
	public void getList()
	{
		int counter = 1;

		ArrayList<ToDoItem> theList = toDoService.getAll();
		for(ToDoItem idx : theList)
		{
			System.out.println(counter + ")  " + idx.getTask() + " " + idx.getDueDate() 
				+ " " + idx.getAssignedUser() + " " + idx.isComplete() + " " 
				+ idx.isInProgress() + ".");
			counter++;
		}
	}
	
	public void userChoice(int entryChoice)
	{
		switch(entryChoice)
		{
			case 1:
				getList();
				break;
			
			case 2:
				getNewTask();
				break;
			
			case 3:
				updateTask();
				break;
			
			case 4:
				removeListItem();
				break;
			
			case 5:
				markTaskComplete();
				break;
			
			case 6:
				markTaskIncomplete();
				break;
			
			case 7:
				showLeftToDo();
				break;
			
			case 8:
				showCompleted();
				break;
			
			default: 
				break;
		}
	}
}
