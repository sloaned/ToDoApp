package catalyst.data;

import java.time.LocalDateTime;
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
	
	public ArrayList<ToDoItem> getPastDue(){
		ArrayList<ToDoItem> pastDue = new ArrayList<ToDoItem>();
		for(ToDoItem i : toDoList)
		{
			Date date = new Date();
			if(i.isComplete() == false && i.getDueDate().before(date))
			{
				pastDue.add(i);
			}
		}
		return pastDue;
	}
	@Override
	public ArrayList<ToDoItem> getUserTask(String userName) {
		ArrayList<ToDoItem> user = new ArrayList<ToDoItem>();
		for(ToDoItem i : toDoList)
		{
			if(i.getAssignedUser() == userName)
			{
				user.add(i);
			}
		}
		return user;
	}
	
}
