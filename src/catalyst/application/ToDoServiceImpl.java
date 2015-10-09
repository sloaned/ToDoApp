package catalyst.application;

import java.util.ArrayList;
import java.util.Date;

import catalyst.data.ToDoData;

public class ToDoServiceImpl implements ToDoService {

	// Dependency to ToDoData
	private ToDoData toDoData;
	
	public void add(String task, boolean inProgress, String assignedUser, Date dueDate){
		
		ToDoItem item = new ToDoItem(task, false, inProgress, assignedUser, dueDate);
		toDoData.addToToDoList(item);
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
}
