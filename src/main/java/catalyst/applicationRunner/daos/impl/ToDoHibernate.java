package catalyst.applicationRunner.daos.impl;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import catalyst.applicationRunner.daos.ToDoData;
import catalyst.applicationRunner.entities.ToDoItem;


@Component
@Transactional
public class ToDoHibernate implements ToDoData{
	
	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	
	//private int counter = 0;
	//ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	@Override
	public ArrayList<ToDoItem> getToDoList() {
		
		return (ArrayList<ToDoItem>) em.
				createQuery("SELECT e FROM ToDoItem e ORDER BY e.taskNum", ToDoItem.class).
				getResultList();
	}
	
	@Override
	public ArrayList<ToDoItem> getToDoList(String user) {
		
		return (ArrayList<ToDoItem>) em
				.createQuery("SELECT e FROM ToDoItem e WHERE e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
				.setParameter("name",  user)
				.getResultList();
	}
	
	@Override
	public ToDoItem getByTaskId(int taskId) {
		return em
				.createQuery("SELECT e FROM ToDoItem e WHERE e.taskNum = :id", ToDoItem.class)
				.setParameter("id",  taskId)
				.getSingleResult();
	}

	@Override
	public void addToToDoList(ToDoItem item) {
		em.persist(item);
	}

	@Override
	public void removeFromToDoList(int index) {
		ToDoItem item = getByTaskId(index);
		em.remove(item);
	}
	
	/*public void markCompleteAt(int index){
		toDoList.get(index).setComplete(true);
		toDoList.get(index).setInProgress(false);
	}
	
	public void markIncompleteAt(int index){
		toDoList.get(index).setComplete(false);
	}*/

	@Override
	public ArrayList<ToDoItem> getCompleteList(String user) {
		if((user.trim()).length() == 0)
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.complete IS true ORDER BY e.taskNum", ToDoItem.class)
					.getResultList();
		}
		else
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.complete IS true AND e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
					.setParameter("name",  user)
					.getResultList();
		}
	}

	@Override
	public ArrayList<ToDoItem> getIncompleteList(String user) {
		if((user.trim()).length() == 0)
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.complete IS false ORDER BY e.taskNum", ToDoItem.class)
					.getResultList();
		}
		else
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.complete IS false AND e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
					.setParameter("name",  user)
					.getResultList();
		}
	}

	@Override
	public void updateToDoList(int index, ToDoItem item) {
		item.setTaskNum(index);
		em.merge(item);
		
	}
	
	@Override
	public ArrayList<ToDoItem> getPastDue(String user){
		if((user.trim()).length() == 0)
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.dueDate < CURRENT_DATE() AND e.complete IS false ORDER BY e.taskNum", ToDoItem.class)
					.getResultList();
		}
		else
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.dueDate < CURRENT_DATE() AND e.complete IS false AND e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
					.setParameter("name",  user)
					.getResultList();
		}
	}
	/*@Override
	public ArrayList<ToDoItem> getUserTask(String userName) {
		ArrayList<ToDoItem> user = new ArrayList<ToDoItem>();
		for(ToDoItem i : toDoList)
		{
			if(i.getAssignedUser().equals(userName))
			{
				user.add(i);
			}
		}
		return user;
	}*/
	
	@Override
	public ArrayList<ToDoItem> getInProgress(String user){
		System.out.println("made it to hibernate!!!");
		if((user.trim()).length() == 0)
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.inProgress IS true ORDER BY e.taskNum", ToDoItem.class)
					.getResultList();
		}
		else
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.inProgress IS true AND e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
					.setParameter("name",  user)
					.getResultList();
		}
	}
	
	@Override
	public ArrayList<ToDoItem> getNotInProgress(String user){
		if((user.trim()).length() == 0)
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.inProgress IS false ORDER BY e.taskNum", ToDoItem.class)
					.getResultList();
		}
		else
		{
			return (ArrayList<ToDoItem>) em
					.createQuery("SELECT e FROM ToDoItem e WHERE e.inProgress = false AND e.assignedUser = :name ORDER BY e.taskNum", ToDoItem.class)
					.setParameter("name",  user)
					.getResultList();
		}
		
	}
	
	public boolean inList(String taskName)
	{
		
		ArrayList<ToDoItem> toDoList = getToDoList();
		for(ToDoItem i: toDoList)
		{
			if((i.getTask()).equals(taskName))
			{
				return true;
			}
		}
		return false;
	}
	
	public int getLineNumber(String taskName)
	{
		int lineNumber = 0;
		boolean found = false;
		ArrayList<ToDoItem> toDoList = getToDoList();
		for(int i = 0; i < toDoList.size() && !found; i++)
		{
			if((toDoList.get(i).getTask().equals(taskName)))
			{
				lineNumber = i+1;
				found = true;
			}
		}
		return lineNumber;
	}
	
}
