package catalyst.application;

import java.util.ArrayList;
import java.util.Date;

	public interface ToDoService 
	{
		/**
		 * creates a ToDoItem object with the given data and adds it to the to-do list
		 * @param task
		 * @param inProgress
		 * @param assignedUser
		 * @param dueDate
		 */
		void add(String task, boolean inProgress, String assignedUser, Date dueDate);
		
		/**
		 * removes the item from the to-do list at the given index
		 * @param index
		 */
		void remove(int index);

		ArrayList<ToDoItem> getAll(); 
		
		void markComplete(int index);
		void markIncomplete(int index);
		ArrayList<ToDoItem> getComplete();
		ArrayList<ToDoItem> getIncomplete();

		void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate);
	}


