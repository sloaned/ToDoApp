package catalyst.data;

public interface ToDoData {
	
	ArrayList<ToDoList> getToDoList();
	
	void addToToDoList(String ToDo);
	void removeFromToDoList(String ToDo);
}
