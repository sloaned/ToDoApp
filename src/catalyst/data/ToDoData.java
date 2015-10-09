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
	
	void markIncompleteAt(int index);
	void markCompleteAt(int index);
	void updateToDoList(int index, ToDoItem item);
	ArrayList<ToDoItem> getCompleteList();
	ArrayList<ToDoItem> getIncompleteList();
}
