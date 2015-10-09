package catalyst.presentation;

import java.util.Scanner;

public class ToDoApp 
{
	Scanner scan = new Scanner(System.in);
	public void displayMenu()
	{
		System.out.println("Welcome to the To-Do List App!");
		System.out.println("");
		System.out.println("Please select from the following options");
		System.out.println("");
		System.out.println("1) Time to add some new tasks");
		System.out.println("2) What's on my list today?");
		System.out.println("3) I need to fix a task!");
		System.out.println("4) I gotta get rid of something.");
		System.out.println("5) I finished something!");
		System.out.println("6) Just kidding, I didn't finish something...");
		System.out.println("7) Show me what I have left to do!");
		System.out.println("8) Show me what I've done, I need a confidence boost...");
		System.out.println("");
		System.out.println("9) Ok, I'm finished here");
	}
	
	public int getInput()
	{
		
		
		displayMenu();
		
		boolean notValid = true;
		String rawEntry = null;
		int entryChoice = 0;
		
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
		scan.close();
		return entryChoice;
		
		
	}
}
