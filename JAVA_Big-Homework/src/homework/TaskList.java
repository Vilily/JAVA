package homework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TaskList {
	private String name;
	private String kind;
	private List<Task> taskList;
	
	
	public TaskList(String name, String kind)
	{
		this.name = name;
		this.kind = kind;
		taskList = new ArrayList<Task>();
	}
	
	public void addTask(Task x)
	{
		this.taskList.add(x);
	}
	public String getName()
	{
		return this.name;
	}
	public String getKind()
	{
		return this.kind;
	}
	public void deleteByIndex(int index)
	{
		this.taskList.remove(index);
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setKind(String kind)
	{
		this.kind = kind;
	}
	public List<Task> getTasks()
	{
		return this.taskList;
	}
	
	public Task getTaskByIndex(int index)
	{
		return this.taskList.get(index);
	}
	
	public void sortByName()
	{
		this.taskList.sort(new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {
				return (o1.getName().compareTo(o2.getName()));
			}
			
		});
	}
	//按类别排序
	public void sortByKind()
	{
		this.taskList.sort(new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {
				return (o1.getKind().compareTo(o2.getKind()));
			}
			
		});
	}
	//按完成情况排序
	public void sortByIsFinished()
	{
		this.taskList.sort(new Comparator<Task>() {
			
			@Override
			public int compare(Task o1, Task o2)
			{
				if(o1.getIsFinished() == false)
					if(o2.getIsFinished() == false)
						return 0;
					else
						return -1;
				return 1;
			}
		});
	}
	
	//删除
	public void deleteTask(Task task)
	{
		this.taskList.remove(task);
	}
	
	//
	public Task getByIndex(int index)
	{
		return this.taskList.get(index);
	}
	

}
