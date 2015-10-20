package catalyst.applicationRunner.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import catalyst.applicationRunner.daos.ToDoData;
import catalyst.applicationRunner.daos.impl.ToDoArrayList;
import catalyst.applicationRunner.entities.ToDoItem;
import catalyst.applicationRunner.services.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	// Dependency to ToDoData
	@Autowired
	private ToDoData toDoData;
	ToDoArrayList toDoArrayList = new ToDoArrayList();
	
	public void setToDoData(ToDoData toDoData) 
	{
		this.toDoData = toDoData;
	}
	
	
	public void add(String task, boolean inProgress, String assignedUser, Date dueDate, String description){
		ToDoItem item = new ToDoItem(task, false, inProgress, assignedUser, dueDate, description);
		toDoData.addToToDoList(item);
	}
	
	/**
	 * checks whether user input is a valid date
	 * @param input
	 * @return true if user input is valid date, false otherwise
	 */
	public boolean isDate(String input)
	{
		Date newDate = new Date ();
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		 try 
		 {
		      newDate = format.parse(input);
		      return true;
		 } 
		 catch (ParseException e) 
		 {
		      return false;
		 }
	}
	
	public ArrayList<ToDoItem> getAll()
	{
		return toDoData.getToDoList();
	}

	@Override
	public void remove(int index) {
		index -= 1;
		toDoData.removeFromToDoList(index);	
	}

	@Override
	public void markComplete(int index) {
		index -= 1;
		toDoData.markCompleteAt(index);
		
	}

	@Override
	public void markIncomplete(int index) {
		index -= 1;
		toDoData.markIncompleteAt(index);
		
	}

	@Override
	public ArrayList<ToDoItem> getComplete() {
		return toDoData.getCompleteList();
	}

	@Override
	public ArrayList<ToDoItem> getIncomplete() {
		return toDoData.getIncompleteList();
	}
	
	public ArrayList<ToDoItem> getPastDue(){
		return toDoData.getPastDue();
	}
	
	public ArrayList<ToDoItem> getUserTask(String userName)
	{
		return toDoData.getUserTask(userName);
	}

	public ArrayList<ToDoItem> getInProgress(){
		return toDoData.getInProgress();
	}
	
	public boolean inList(String task)
	{
		return toDoData.inList(task);
	}

	@Override
	public void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description) {
		choice--;
		ToDoItem item = new ToDoItem(task, complete, inProgress, assignedUser, dueDate, description);
		toDoData.updateToDoList(choice, item);
		
	}
	
	public int getLineNumber(String task)
	{
		return toDoData.getLineNumber(task);
	}
}
