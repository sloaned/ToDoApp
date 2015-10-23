package catalyst.applicationRunner.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="todo")
public class ToDoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId")
	private int taskNum;
	@Basic(optional=false)
	private String task;
	private boolean complete;
	private boolean inProgress;
	@Basic(optional=false)
	private String assignedUser;
	@JsonFormat(pattern = "MM/dd/yyyy", timezone="PST")
	@Basic(optional=false)
	private Date dueDate;
	private String description;
	
	public ToDoItem()
	{
		
	}
	
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

	public boolean getComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public boolean getInProgress() {
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

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
}
