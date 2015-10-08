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
	public void addToToDoList(ToDoItem item) {
		toDoList.add(item);	
	}

	@Override
	public void removeFromToDoList(ToDoItem item) {
		for(int i = 0; i < toDoList.size(); i++)
		{
			ToDoItem current = toDoList.get(i);
			if(current == item)
			{
				toDoList.remove(i);
				i--;
			}
			
		}
		
	}
	
}
