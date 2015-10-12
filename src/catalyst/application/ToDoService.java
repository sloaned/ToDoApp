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
		void add(String task, boolean inProgress, String assignedUser, Date dueDate, String newDescription);
		
		/**
		 * removes the item from the to-do list at the given index
		 * @param index
		 */
		void remove(int index);

		/**
		 * returns the entire to-do list
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getAll(); 
		
		/**
		 * marks the task complete at the specified index and sets inProgress to false
		 * @param index
		 */
		void markComplete(int index);
		
		/**
		 * marks the task incomplete at the specified index
		 * @param index
		 */
		void markIncomplete(int index);
		
		/**
		 * returns an ArrayList of all to-do items that have been completed
		 * @return ArrayList of ToDoItem 
		 */
		ArrayList<ToDoItem> getComplete();
		
		/**
		 * returns an ArrayList of all to-do items that have not been completed
		 * @return ArrayList of ToDoItem 
		 */
		ArrayList<ToDoItem> getIncomplete();

		/**
		 * replaces the task at the specified index in the ArrayList with an updated version
		 * @param choice
		 * @param task
		 * @param complete
		 * @param inProgress
		 * @param assignedUser
		 * @param dueDate
		 */
		void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String newDescription);

		ArrayList<ToDoItem> getPastDue();
		
		ArrayList<ToDoItem> getUserTask(String userName);

		ArrayList<ToDoItem> getInProgress();
		
		boolean inList(String task);

	}


