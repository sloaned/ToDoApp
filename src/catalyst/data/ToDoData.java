package catalyst.data;

import java.util.ArrayList;

import catalyst.application.ToDoItem;

public interface ToDoData {
	
	ArrayList<ToDoItem> getToDoList();
	
	void addToToDoList(ToDoItem item);
	void removeFromToDoList(ToDoItem item);
}
