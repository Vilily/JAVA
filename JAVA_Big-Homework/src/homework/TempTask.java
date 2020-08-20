package homework;

import java.util.Date;

public class TempTask extends Task{
	private Date deadLineDate;
	public TempTask(String name, String body, Date deadLineDate)
	{
		super(name, body, "Temp");
		this.deadLineDate = deadLineDate;
	}
	
	public TempTask(String name, String body, Date deadLineDate, boolean isFinished) 
	{
		super(name, body, "Temp", isFinished);
		this.deadLineDate = deadLineDate;
	}
	
	@Override
	public void setDeadLineDate(Date deadLineDate)
	{
		this.deadLineDate.setTime(deadLineDate.getTime());
	}
	
	@Override
	public Date getDeadLineDate()
	{
		return this.deadLineDate;
	}
	
}
