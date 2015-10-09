package catalyst.data;

import java.util.ArrayList;

import catalyst.application.ToDoItem;

public class ToDoArrayList implements ToDoData{
	
	ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	@Override
	/**
	 * returns the entire to-do list
	 * @return ArrayList<ToDoItem>
	 */
	public ArrayList<ToDoItem> getToDoList() {
		
		return toDoList;
	}

	@Override
	/**
	 * adds the specified item to the to-do list
	 * @param ToDoItem object
	 * @return void
	 */
	public void addToToDoList(ToDoItem item) {
		toDoList.add(item);	
	}

	@Override
	/**
	 * removes the specified item from the to-do list
	 * @param ToDoItem object
	 * @return void
	 */
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
