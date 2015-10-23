package catalyst.applicationRunner.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import catalyst.applicationRunner.entities.ToDoItem;


	public interface ToDoService 
	{
		/**
		 * creates a ToDoItem object with the given data and adds it to the to-do list
		 * @param task
		 * @param inProgress
		 * @param assignedUser
		 * @param dueDate
		 */
		void add(String task, boolean inProgress, String assignedUser, Date dueDate, String description);
		
		
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
		 * returns the entire to-do list filtered by name
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getAll(String name); 

		/**
		 * returns an ArrayList of all to-do items that have been completed
		 * @return ArrayList of ToDoItem 
		 */
		ArrayList<ToDoItem> getComplete(String name);
		
		/**
		 * returns an ArrayList of all to-do items that have not been completed
		 * @return ArrayList of ToDoItem 
		 */
		ArrayList<ToDoItem> getIncomplete(String name);

		/**
		 * replaces the task at the specified index in the ArrayList with an updated version
		 * @param choice
		 * @param task
		 * @param complete
		 * @param inProgress
		 * @param assignedUser
		 * @param dueDate
		 */
		void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description);

		/**
		 * returns an ArrayList of all to-do items that are past their due date and incomplete
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getPastDue(String name);

		/**
		 * returns an ArrayList of all to-do items that are in progress
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getInProgress(String name);
		
		/**
		 * returns an ArrayList of all to-do items that are not in progress
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getNotInProgress(String name);
		
		/**
		 * checks whether a given task name is in the to-do list already
		 * (used to enforce uniqueness)
		 * @return true if task name is already in list, false otherwise
		 */
		boolean inList(String task);
		
		/**
		 * gets index of given task, subtracted by one
		 * (used to get line number of user display)
		 * @param task
		 * @return int line number
		 */
		int getLineNumber(String task);

	}


