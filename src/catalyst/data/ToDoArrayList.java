package catalyst.data;

import java.util.ArrayList;
import java.util.Date;

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
		Date date = item.getDueDate();
		//System.out.println("number of items in to-do list now = " + toDoList.size());
	}

	@Override
	public void removeFromToDoList(int index) {
		toDoList.remove(index);
	}
	
	public void markCompleteAt(int index){
		toDoList.get(index).setComplete(true);
		toDoList.get(index).setInProgress(false);
	}
	
	public void markIncompleteAt(int index){
		toDoList.get(index).setComplete(false);
	}

	@Override
	public ArrayList<ToDoItem> getCompleteList() {
		ArrayList<ToDoItem> complete = new ArrayList<ToDoItem>();
		for(ToDoItem i : toDoList)
		{
			if(i.isComplete() == true)
			{
				complete.add(i);
			}
		}
		return complete;
	}

	@Override
	public ArrayList<ToDoItem> getIncompleteList() {
		ArrayList<ToDoItem> incomplete = new ArrayList<ToDoItem>();
		for(ToDoItem i : toDoList)
		{
			if(i.isComplete() == false)
			{
				incomplete.add(i);
			}
		}
		return incomplete;
	}

	@Override
	public void updateToDoList(int index, ToDoItem item) {
		toDoList.set(index, item);	
	}
	
}
