package homework;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
	private String kind;
	private String name;
	private String body;
	boolean isFinished;
	
	//无参数的初始化
	public Task(String name, String body, String kind)
	{
		this.name = name;
		this.body = body;
		this.kind = kind;
		this.isFinished = false;
	}
	public Task(String name, String body, String kind, boolean isFinished)
	{
		this.name = name;
		this.body = body;
		this.kind = kind;
		this.isFinished = isFinished;
	}
	public boolean getIsFinished()
	{
		return this.isFinished;
	}
	
	public Task(String name, String kind)
	{
		this.name = name;
		this.kind = kind;
	}
	public boolean isFinished()
	{
		return this.isFinished;
	}
	public void setIsFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	//repeat
	public void setBeginDate(Date beginDate)
	{
	}
	public void setRepeatTimes(int times)
	{
		
	}
	public Date getBeginDate()
	{
		Date c = new Date();
		return c;
	}
	public int getRepeatTimes()
	{
		return 0;
	}
	
	public int getRangeYear()
	{
		return 0;
	}
	public int getRangeMonth()
	{
		return 0;
	}
	public int getRangeDay()
	{
		return 0;
	}
	public void setRangeYear(int year)
	{
		
	}
	
	public void setRangeMonth(int month)
	{
		
	}
	
	public void setRangeDay(int day)
	{
		
	}
	
	
	//temp
	public void setDeadLineDate(Date deadLineDate)
	{
	}
	public Date getDeadLineDate()
	{
		return new Date();
	}
	//long
	public void addTask(Task temp)
	{
	}
	public List<Task> getTasks()
	{
		return (new ArrayList<Task>());
	}
	
	public Task getTaskByIndex(int index)
	{
		return null;
	}
	public void deleteTaskByIndex(int index)
	{
		
	}
	//
	
	public String getName()
	{
		return this.name;
	}
	public String getKind()
	{
		return this.kind;
	}
	public String getBody()
	{
		return this.body;
	}
	
}
