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
		void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description);

		/**
		 * returns an ArrayList of all to-do items that are past their due date and incomplete
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getPastDue();
		
		/**
		 * returns an ArrayList of all to-do items that are assigned to the given user
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getUserTask(String userName);

		/**
		 * returns an ArrayList of all to-do items that are in progress
		 * @return ArrayList of ToDoItem
		 */
		ArrayList<ToDoItem> getInProgress();
		
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


