package catalyst.applicationRunner.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import catalyst.applicationRunner.daos.ToDoData;
import catalyst.applicationRunner.daos.impl.ToDoHibernate;
import catalyst.applicationRunner.entities.ToDoItem;
import catalyst.applicationRunner.services.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	// Dependency to ToDoData
	@Autowired
	private ToDoData toDoData;
	ToDoHibernate toDoHibernate = new ToDoHibernate();
	
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
	
	@Override
	public ArrayList<ToDoItem> getAll()
	{
		return toDoData.getToDoList();
	}
	
	@Override
	public ArrayList<ToDoItem> getAll(String name)
	{
		return toDoData.getToDoList(name);
	}

	@Override
	public void remove(int index) {
		toDoData.removeFromToDoList(index);	
	}

	@Override
	public ArrayList<ToDoItem> getComplete(String name) {
		return toDoData.getCompleteList(name);
	}

	@Override
	public ArrayList<ToDoItem> getIncomplete(String name) {
		return toDoData.getIncompleteList(name);
	}
	
	public ArrayList<ToDoItem> getPastDue(String name){
		return toDoData.getPastDue(name);
	}

	public ArrayList<ToDoItem> getInProgress(String name){
		return toDoData.getInProgress(name);
	}
	
	public ArrayList<ToDoItem> getNotInProgress(String name){
		return toDoData.getNotInProgress(name);
	}
	
	public boolean inList(String task)
	{
		return toDoData.inList(task);
	}

	@Override
	public void update(int choice, String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description) {
		ToDoItem item = new ToDoItem(task, complete, inProgress, assignedUser, dueDate, description);
		toDoData.updateToDoList(choice, item);
		
	}
	
	public int getLineNumber(String task)
	{
		return toDoData.getLineNumber(task);
	}
}
