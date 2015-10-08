package catalyst.data;

import java.util.ArrayList;

import catalyst.application.ToDoItem;

public class ToDoArrayList implements ToDoData{
	
	ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	@Override
	public ArrayList<ToDoItem> getToDoList() {
		
		return toDoList;
	}

	@Override
	public void addToToDoList(String ToDo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromToDoList(String ToDo) {
		// TODO Auto-generated method stub
		
	}
	
}
