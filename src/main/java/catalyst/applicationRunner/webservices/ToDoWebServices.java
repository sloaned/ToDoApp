package catalyst.applicationRunner.webservices;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import catalyst.applicationRunner.entities.ToDoItem;
import catalyst.applicationRunner.services.ToDoService;

@RestController
public class ToDoWebServices {

	@Autowired
	private ToDoService toDoService;
	
	public void setToDoService(ToDoService toDoService) 
	{
		this.toDoService = toDoService;
	}

	@RequestMapping(value="/todo", method=RequestMethod.POST)
	public void addItem(@RequestBody ToDoItem item){
		toDoService.add(item.getTask(), item.getInProgress(), item.getAssignedUser(), item.getDueDate(), item.getDescription());
	} 
		
		
	@RequestMapping(value="/todo", method = RequestMethod.GET)
	public ArrayList<ToDoItem> getToDoList(){
		return toDoService.getAll();
	}	
	
	@RequestMapping(value="/todo", method = RequestMethod.GET, params = {"filter", "name"})
	public ArrayList<ToDoItem> getFilteredList(@RequestParam String filter, @RequestParam String name){
		if(filter.equals("All statuses") && name.equals(""))
		{
			return toDoService.getAll();
		}
		switch(filter)
		{
			case "All statuses":
				return toDoService.getAll(name);
			case "Incomplete":
				return toDoService.getIncomplete(name);	
			case "Complete":
				return toDoService.getComplete(name);	
			case "In Progress":
				return toDoService.getInProgress(name);
			case "Not In Progress":
				return toDoService.getNotInProgress(name);
			case "Past Due":
				return toDoService.getPastDue(name);
			default:
				return null;		
		}
	}
	

	
	/*@RequestMapping(value="/todo/{id}", method=RequestMethod.GET)
	public ToDoService getTaskByID(@PathVariable Integer id) throws InvalidInputException{ 
		return toDoService.getByItemId(id);
	}*/
	
	@RequestMapping(value="/todo/{id}", method = RequestMethod.PUT)
	public void updateToDoList(@PathVariable Integer id, @RequestBody ToDoItem item){
		toDoService.update(id, item.getTask(), item.getComplete(), item.getInProgress(), item.getAssignedUser(), item.getDueDate(), item.getDescription());
	}
	
	@RequestMapping(value="/todo/{id}", method = RequestMethod.DELETE)
	public void removeTask(@PathVariable Integer id){	
		toDoService.remove(id);	 
	}
}
