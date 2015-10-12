package catalyst.data;

import java.util.ArrayList;

import catalyst.application.ToDoItem;

public interface ToDoData {
	/**
	 * returns the entire to-do list
	 * @return ArrayList<ToDoItem>
	 */
	ArrayList<ToDoItem> getToDoList();
	
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
	void markIncompleteAt(int index);
	
	/**
	 * marks an item complete at the given index
	 * @param index
	 */
	void markCompleteAt(int index);
	
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
	ArrayList<ToDoItem> getCompleteList();
	
	/**
	 * gets the list of all incomplete to-do list items
	 * @return ArrayList of ToDoItem
	 */
	ArrayList<ToDoItem> getIncompleteList();
	
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
	boolean inList(String taskName);
	
	int getLineNumber(String taskName);
}
