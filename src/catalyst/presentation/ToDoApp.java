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
	
	public Scanner scan = new Scanner(System.in);
	
	/**
	 * displays option menu to the user
	 */
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
		System.out.println("9) Show me what's past due.");
		System.out.println("10) Show me what is currently in progress.");
		System.out.println("11) Show me who has to do what.");
		System.out.println("");
		System.out.println("12) Ok, I'm finished here");
		System.out.println("");
	}
	
	/**
	 * user enters new task to the to-do list
	 */
	public void getNewTask()
	{
		String newTask = null;
		String getDate = null;
		boolean inProgress = false;
		String newUser = null;
		String rawInput = null;
		String getAnswer = null;
		String newDescription = null;
		
		
		System.out.println("Enter a new task: ");
		newTask = scan.nextLine();
		
		while(newTask.trim().length() < 1)
		{
			System.out.println("Enter a new task: ");
			newTask = scan.nextLine();
		}
		while(toDoService.inList(newTask) == true)
		{
			System.out.println("That task already exists, please enter a unique task name.");
			newTask = scan.nextLine();
		}
		
		
		System.out.println("Enter the Due Date: ");
		getDate = scan.nextLine();
		while(!isDate(getDate))
		{
			System.out.println("Not a valid date");
			getDate = scan.nextLine();
		}
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
		else 
		{
			inProgress = false;
		}
		
		System.out.println("Who has to finish this task?: ");
		newUser = scan.nextLine();
		while(newUser.trim().length() < 1)
		{
			System.out.println("Enter a new task: ");
			newUser = scan.nextLine();
		}
		
		System.out.println("Did you want to add a description about this task? (yes or no)");
		getAnswer = scan.nextLine();
		if(getAnswer.equals("no")|| getAnswer.equals("n"))
		{
			newDescription = null;
		}
		else if(getAnswer.equals("yes") || getAnswer.equals("y"))
		{
			System.out.println("Please enter a description: ");
			newDescription = scan.nextLine();
		}
		
		toDoService.add(newTask, inProgress, newUser, newDate, newDescription);
		
	}
	
	/**
	 * allows user to re-enter all info on a given task
	 */
	public void updateTask()
	{
		getList();
		int userEntry = getStringInput();
		
		String updateTask = null;
		String getDate = null;
		boolean inProgress = false;
		String updateUser = null;
		boolean isComplete = false;
		String updateDescription = null;
		String getAnswer = null;
		
		
		System.out.println("Enter the new task name: ");
		updateTask = scan.nextLine();
		while(updateTask.trim().length() < 1)
		{
			System.out.println("Enter a new task name: ");
			updateTask = scan.nextLine();
		}
		
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
		
		if (rawInput.equals("yes")|| rawInput.equals("y"))
		{
			inProgress = true;
			System.out.println("Who has to finish this task?: ");
			updateUser = scan.nextLine();
			while(updateUser.trim().length() < 1)
			{
				System.out.println("Enter a new task: ");
				updateUser = scan.nextLine();
			}
			
		}
		else
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
				while(updateUser.length() < 1 || updateUser.equals(" "))
				{
					System.out.println("Enter a new task: ");
					updateUser = scan.nextLine();
				}
				
			}
			else if (isComplete == true)
			{
				System.out.println("Who finished this task?: ");
				updateUser = scan.nextLine();
				while(updateUser.length() < 1 || updateUser.equals(" "))
				{
					System.out.println("Enter a new task: ");
					updateUser = scan.nextLine();
				}
			}
			inProgress = false;
		}
		System.out.println("Did you want to add a description about this task? (yes or no)");
		getAnswer = scan.nextLine();
		if(getAnswer.equals("no") || getAnswer.equals("n"))
		{
			updateDescription = null;
		}
		else if(getAnswer.equals("yes") || getAnswer.equals("y"))
		{
			System.out.println("Please enter a description: ");
			updateDescription = scan.nextLine();
		}
		
		toDoService.update(userEntry, updateTask, isComplete, inProgress, updateUser, updateDate, updateDescription);
	}
	
	/**
	 * checks whether user input is a valid date
	 * @param input
	 * @return true if user input is valid date, false otherwise
	 */
	public boolean isDate(String input)
	{
		Date newDate = new Date ();
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		 try 
		 {
		      newDate = format.parse(input);
		      return true;
		 } 
		 catch (ParseException e) 
		 {
		      return false;
		 }
	}
	
	/**
	 * validates that user input is a valid number from the to-do list and returns that number
	 * @return
	 */
	public int getIndex()
	{
		getList();
		int rawInput = getStringInput();
		ArrayList<ToDoItem> toDo = toDoService.getAll();
		
		
		while(rawInput > toDo.size() || rawInput < 0)
		{
			System.out.println("That is not an item in the list. Try again");
			rawInput = getInput();
		}
		return rawInput;
	}
	
	/**
	 * user selects a task and it is removed from the to-do list
	 */
	public void removeListItem()
	{
		int itemNumber = getIndex();
		toDoService.remove(itemNumber);
	}
	
	/**
	 * user selects a task and it is marked complete
	 */
	public void markTaskComplete()
	{
		int listNumber = getIndex();
		toDoService.markComplete(listNumber);
	}
	
	/**
	 * user selects a task and it is marked incomplete
	 */
	public void markTaskIncomplete()
	{
		int listNumber = getIndex();
		toDoService.markIncomplete(listNumber);
	}
	
	/**
	 * checks whether user input is an integer
	 * @param userInput
	 * @return true if user input is integer, false otherwise
	 */
	public boolean isNumber(String userInput)
	{
		try
		{
			Integer.parseInt(userInput);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * allows user to enter either line number of task they want to select
	 * or name of task they want to select
	 * @return int (line number of task they selected)
	 */
	public int getStringInput()
	{
		boolean notValid = true;
		int userInput = 0;
		
		System.out.println("Enter the title or number of a task: ");
		
		String rawEntry = scan.nextLine();
		while(notValid)
		{
			if(!isNumber(rawEntry))
			{
				if(toDoService.inList(rawEntry))
				{
					userInput = toDoService.getLineNumber(rawEntry);	
					notValid = false;
				}
				else
				{
					System.out.println("That is not a valid line number or task name. Try again.");
					rawEntry = scan.nextLine();
				}
			}
			else
			{
				userInput = Integer.parseInt(rawEntry);
				ArrayList<ToDoItem> toDo = toDoService.getAll();
				
				if(userInput > toDo.size() || userInput < 0)
				{
					System.out.println("you entered " + userInput);
					System.out.println("That is not an item in the list. Try again");
					rawEntry = scan.nextLine();
				}
				else
				{
					notValid = false;
				}
			}
		}	
		return userInput;
	}
	
	
	/**
	 * gets integer from user to correspond with their menu choice
	 * @return
	 */
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
	
	/**
	 * prints the entire to-do list
	 */
	public void getList()
	{
		ArrayList<ToDoItem> theList = toDoService.getAll();
	
		if(theList.size() <= 0)
		{
			System.out.println("There are no tasks in your list yet!");
		}
		else
		{
			showList(theList);
		}
	}
	
	/**
	 * prints list of incomplete tasks
	 */
	public void showLeftToDo()
	{
		ArrayList<ToDoItem> theList = toDoService.getIncomplete();
		System.out.println("The following tasks are incomplete!:");
		showList(theList);
	
	}
	
	/**
	 * prints list of completed tasks
	 */
	public void showCompleted()
	{	
		ArrayList<ToDoItem> theList = toDoService.getComplete();
		System.out.println("The following tasks are completed:");
		showList(theList);
	}
	
	/**
	 * prints list of overdue tasks
	 */
	public void showPastDue()
	{
		ArrayList<ToDoItem> theList = toDoService.getPastDue();
		System.out.println("The following tasks are past their due date!");
		showList(theList);
	}
	
	/**
	 * prints list of tasks currently in progress
	 */
	public void showInProgress()
	{
		ArrayList<ToDoItem> theList = toDoService.getInProgress();
		System.out.println("The following tasks are in progress:");
		showList(theList);
		
	}
	
	/**
	 * gets username from user and prints all tasks assigned to that username
	 */
	public void selectUserTasks()
	{
		getList();
		System.out.println("Input the name of a user you want to find the tasks for: ");
		String getName = scan.nextLine();
		
		ArrayList<ToDoItem> theList = toDoService.getUserTask(getName);
		System.out.println("The following tasks are assigned to " + getName + ":");
		showList(theList);
	}
	
	/**
	 * prints out any given ArrayList of ToDoItem
	 * @param theList
	 */
	public void showList(ArrayList<ToDoItem> theList)
	{
		int counter = 1;
		String dateString = "";
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    Date date = new Date();
	    for(ToDoItem idx : theList)
		{

			date = idx.getDueDate();
			dateString = sdf.format(date);

			String isInProgress = null;
			boolean inProgress = idx.isInProgress();
			if(inProgress == true)
			{
				isInProgress = "Yes";
				
			}
			else if(inProgress == false)
			{
				isInProgress = "No";
			}
			
			System.out.println(counter + ")  "  + idx.getAssignedUser() + " needs to " + idx.getTask() + " and finish it by: " + dateString + "."); 
			System.out.println( "\tIn progress? " + isInProgress);
			System.out.println( "\tDescription: " + idx.getDescription());
			boolean complete = idx.isComplete();
			
			if(complete == true)
			{
				System.out.println("\tThis task is finished!");
			}
			
			counter++;
		}
	    System.out.println("End of list\n");
	}

	/**
	 * switch statement called when user chooses a menu option
	 * @param entryChoice
	 */
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
				
			case 9:
				showPastDue();
				break;
			
			case 10: 
				showInProgress();
				break;

			case 11:
				selectUserTasks();
				break;
			
			default: 
				break;
		}
	}
}
