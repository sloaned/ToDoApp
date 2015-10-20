package catalyst.applicationRunner.daos.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import catalyst.applicationRunner.daos.ToDoData;
import catalyst.applicationRunner.entities.ToDoItem;


@Component
public class ToDoArrayList implements ToDoData{
	
	private int counter = 0;
	ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	@Override
	public ArrayList<ToDoItem> getToDoList() {
		
		return toDoList;
	}

	@Override
	public void addToToDoList(ToDoItem item) {
		counter++;
		item.setTaskNum(counter);
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
			if(i.getComplete() == true)
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
			if(i.getComplete() == false)
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
		for(ToDoItem i : toDoList)
		{
			if(i.getComplete() == false && i.getDueDate().before(date))
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
			if(i.getInProgress())
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
