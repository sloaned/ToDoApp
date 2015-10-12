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
	
	ArrayList<ToDoItem> getPastDue();
	
	ArrayList<ToDoItem> getInProgress();
}
