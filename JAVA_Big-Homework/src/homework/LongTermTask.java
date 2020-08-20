package homework;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LongTermTask extends Task{
	private Date deadLineDate;
	List<Task> child;
	
	public LongTermTask(String name, String body)
	{
		super(name, body, "LongTerm");
		this.child = new ArrayList<Task>();
		this.deadLineDate = Calendar.getInstance().getTime();
		this.child = new ArrayList<Task>();
	}
	//有数据的初始化
	public LongTermTask(String name, Date deadLineDate, boolean isFinished) 
	{
		super(name, null, "LongTerm", isFinished);
		this.deadLineDate = deadLineDate;
		this.child = new ArrayList<Task>();
	}
	@Override
	public void setDeadLineDate(Date deadLineDate)
	{
		this.deadLineDate = deadLineDate;
	}
	
	@Override
	public void addTask(Task temp)
	{
		this.child.add(temp);
	}
	@Override
	public Date getDeadLineDate()
	{
		return this.deadLineDate;
	}
	
	@Override
	public List<Task> getTasks()
	{
		return this.child;
	}
	@Override
	public Task getTaskByIndex(int index)
	{
		return this.child.get(index);
	}
	
	@Override
	public void deleteTaskByIndex(int index)
	{
		if(index >= 0 && index < this.child.size())
			this.child.remove(index);
	}
	

}
