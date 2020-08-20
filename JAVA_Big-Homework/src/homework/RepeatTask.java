package homework;

import java.util.Calendar;
import java.util.Date;

public class RepeatTask extends Task{
	private Date beginDate;
	private int repeatTimes;
	private int year;
	private int month;
	private int day;
	
	//无数据的初始化
	public RepeatTask(String name, String body)
	{
		super(name, body, "Repeat");
		this.beginDate = Calendar.getInstance().getTime();
		this.repeatTimes = 1;
		this.year = 0;
		this.month = 0;
		this.day = 1;
	}
	//有数据的初始化
	public RepeatTask(String name, String body, Date beginDate, int repeaTimes, int year, int month, int day, boolean isFinished)
	{
		super(name, body, "Repeat", isFinished);
		this.beginDate = beginDate;
		this.repeatTimes = repeaTimes;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}
	
	public void setRepeatTimes(int times)
	{
		this.repeatTimes = times;
	}
	@Override
	public Date getBeginDate()
	{
		return beginDate;
	}
	@Override
	public int getRepeatTimes()
	{
		return this.repeatTimes;
	}
	@Override
	public int getRangeYear()
	{
		return this.year;
	}
	@Override
	public int getRangeMonth()
	{
		return this.month;
	}
	
	@Override
	public int getRangeDay()
	{
		return this.day;
	}
	@Override
	public void setRangeYear(int year)
	{
		this.year = year;
	}
	@Override
	public void setRangeMonth(int month)
	{
		this.month = month;
	}
	@Override
	public void setRangeDay(int day)
	{
		this.day = day;
	}
}
