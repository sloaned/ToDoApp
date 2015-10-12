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
		Date date = new Date();
		System.out.println("date = " + date);
		for(ToDoItem i : toDoList)
		{
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
			if(i.getAssignedUser().equals(userName))
			{
				user.add(i);
			}
		}
		return user;
	}
	
	public ArrayList<ToDoItem> getInProgress(){
		ArrayList<ToDoItem> inProgressList = new ArrayList<ToDoItem>();
		for(ToDoItem i: toDoList)
		{
			if(i.isInProgress())
			{
				inProgressList.add(i);
			}
		}
		return inProgressList;
	}
	
	public boolean inList(String taskName)
	{
		for(ToDoItem i: toDoList)
		{
			if((i.getTask()).equals(taskName))
			{
				return true;
			}
		}
		return false;
	}
	
	public int getLineNumber(String taskName)
	{
		int lineNumber = 0;
		boolean found = false;
		for(int i = 0; i < toDoList.size() && !found; i++)
		{
			if((toDoList.get(i).getTask().equals(taskName)))
			{
				lineNumber = i+1;
				found = true;
			}
		}
		return lineNumber;
	}
	
}
