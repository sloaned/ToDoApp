package catalyst.applicationRunner.daos;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import catalyst.applicationRunner.entities.ToDoItem;

public interface ToDoData {
	/**
	 * returns the entire to-do list
	 * @return ArrayList<ToDoItem>
	 */
	ArrayList<ToDoItem> getToDoList();
	
	/**
	 * returns the entire to-do list filtered by username
	 * @return ArrayList<ToDoItem>
	 */
	ArrayList<ToDoItem> getToDoList(String name);
	
	/**
	 * adds the specified item to the to-do list
	 * @param ToDoItem object
	 * @return void
	 */
	void addToToDoList(ToDoItem item);
	
	/**
	 * removes the specified item from the to-do list
	 * @param int index of the item to remove
	 * @return void
	 */
	void removeFromToDoList(int index);
	
	/**
	 * marks an item incomplete at the given index
	 * @param index
	 */
//	void markIncompleteAt(int index);
	
	/**
	 * marks an item complete at the given index
	 * @param index
	 */
	//void markCompleteAt(int index);
	
	/**
	 * updates the list by replacing the toDoList item at the given index with updated item
	 * @param index
	 * @param item
	 */
	void updateToDoList(int index, ToDoItem item);
	
	/**
	 * gets the list of all completed to-do list items
	 * @return ArrayList of ToDoItem
	 */
	ArrayList<ToDoItem> getCompleteList(String name);
	
	/**
	 * gets the list of all incomplete to-do list items
	 * @return ArrayList of ToDoItem
	 */
	ArrayList<ToDoItem> getIncompleteList(String name);
	
	/**
	 * returns an ArrayList of all to-do items that are past their due date and incomplete
	 * @return ArrayList of ToDoItem
	 */
	ArrayList<ToDoItem> getPastDue(String name);

	/**
	 * returns an ArrayList of all to-do items that are assigned to the given user
	 * @return ArrayList of ToDoItem
	 */
	//ArrayList<ToDoItem> getUserTask(String userName);
	
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
	boolean inList(String taskName);
	
	/**
	 * gets index of given task, subtracted by one
	 * (used to get line number of user display)
	 * @param task
	 * @return int line number
	 */
	int getLineNumber(String taskName);

	ToDoItem getByTaskId(int taskId);
}
