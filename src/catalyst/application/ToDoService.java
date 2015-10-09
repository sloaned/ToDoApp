package catalyst.application;

import java.util.ArrayList;
import java.util.Date;

public interface ToDoService {
		
	void add(String task, boolean inProgress, String assignedUser, Date dueDate);
	void remove(int index);
	ArrayList<ToDoItem> getAll();
	void markComplete(int index);
	void markIncomplete(int index);
	ArrayList<ToDoItem> getComplete();
	ArrayList<ToDoItem> getIncomplete();
	
}
