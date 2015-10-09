package catalyst.application;

import java.util.ArrayList;
import java.util.Date;

	public interface ToDoService 
	{
		
		void add(String task, boolean inProgress, String assignedUser, Date dueDate);
		void remove(int index);
		static ArrayList<ToDoItem> getAll()
		{
			// TODO Auto-generated method stub
			return null;
		}
		void markComplete(int index);
		void markIncomplete(int index);
		ArrayList<ToDoItem> getComplete();
		ArrayList<ToDoItem> getIncomplete();
	
}

