package catalyst.application;

import java.util.Date;

public class ToDoItem {
	private String task;
	private boolean complete;
	private boolean inProgress;
	private String assignedUser;
	private Date dueDate;
	private String description;
	
	
	public ToDoItem(String task, boolean complete, boolean inProgress, String assignedUser, Date dueDate, String description){
		this.task = task;
		this.complete = complete;
		this.inProgress = inProgress;
		this.assignedUser = assignedUser;
		this.dueDate = dueDate;
		this.description = description;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public boolean isInProgress() {
		return inProgress;
	}
	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	public String getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
