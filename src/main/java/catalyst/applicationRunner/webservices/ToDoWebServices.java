package catalyst.applicationRunner.webservices;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalyst.application.ToDoItem;
import catalyst.application.ToDoService;

@RestController
public class ToDoWebServices {

	@Autowired
	private ToDoService toDoService;
	
	public void setToDoService(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	@RequestMapping(value="/todo", method=RequestMethod.POST)
	public void addItem(@RequestBody String task, boolean inProgress, String assignedUser, Date dueDate, String description){
		toDoService.add(task, inProgress, assignedUser, dueDate, description);
	} 
		
		
	@RequestMapping(value="/todo", method = RequestMethod.GET)
	public ArrayList<ToDoItem> getToDoList(){
		return toDoService.getAll();
	}	
	

	
	/*@RequestMapping(value="/todo/{id}", method=RequestMethod.GET)
	public ToDoService getTaskByID(@PathVariable Integer id) throws InvalidInputException{ 
		return toDoService.getByItemId(id);
	}*/
	
	@RequestMapping(value="/todo/{id}", method = RequestMethod.PUT)
	public void updateToDoList(@PathVariable Integer id, @RequestBody String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description){
		toDoService.update(id, task, complete, inProgress, assignedUser, dueDate, description);
	}
	
	@RequestMapping(value="/todo/{id}", method = RequestMethod.DELETE)
	public void removeTask(@PathVariable Integer id){	
		toDoService.remove(id);	 
	}
}
