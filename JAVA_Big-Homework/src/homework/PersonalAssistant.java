package homework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PersonalAssistant {
	private List<TaskList> taskList;
	private PersonalAssistantFile data;
	private PersonalAssistantUI assistantUI;
	private final int LISTS_FOR_PAGE = 10;
	
	
	
	public PersonalAssistant()
	{
		this.taskList = new ArrayList<TaskList>();
		this.data = new PersonalAssistantFile(this);
		
		//初始化界面
		this.assistantUI  = new PersonalAssistantUI(this);
		
	}
	
	public void sortBy(int flag)//0 name; 1 kind
	{
		if(flag == 0)
		{
			this.taskList.sort(new Comparator<TaskList>() {
				
				@Override
				public int compare(TaskList arg0, TaskList arg1)
				{
					return arg0.getName().compareTo(arg1.getName());
				}
			});
		}
		else if(flag == 1)
		{
			this.taskList.sort(new Comparator<TaskList>() {
				
				@Override
				public int compare(TaskList arg0, TaskList arg1)
				{
					return arg0.getKind().compareTo(arg1.getKind());
				}
			});
		}
	}
	
	public void storeInfo()
	{
		this.data.storeInfo();
	}
	
	public void export()
	{
		this.data.export();
	}
	
	public void inport()
	{
		this.data.inport();
	}

	//返回（page-1）到page的内容
	public List<TaskList> getTaskList(int page)
	{
		List<TaskList> temp = new ArrayList<TaskList>();
		for(int i = (page - 1)*LISTS_FOR_PAGE ;i < page*LISTS_FOR_PAGE; i++)
		{
			if(i < this.taskList.size())
			{
				temp.add(this.taskList.get(i));
			}
			else 
			{
				break;
			}
		}
		return temp;
	}
	
	public List<TaskList> getTaskLists()
	{
		return this.taskList;
	}
	
	public TaskList getTaskListByIndex(int index)
	{
		if(index < this.taskList.size())
			return this.taskList.get(index);
		else 
			return null;
	}
	
	
	public void setTaskListByIndex(TaskList item, int index)
	{
		this.taskList.set(index, item);
	}
	//加新的任务清单
	public void addTaskList(TaskList item)
	{
		this.taskList.add(item);
	}
	public void deleteTaskListByIndex(int index)
	{
		this.taskList.remove(index);
	}
}
