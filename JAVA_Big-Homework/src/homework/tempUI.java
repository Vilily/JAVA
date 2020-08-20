package homework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class tempUI implements MouseListener{
	//����
	private JFrame frame;
	//��������
	private JScrollPane scrollPane;
	//����
	private PersonalAssistant assistant;
	//����0-���ң�1-�ƶ���2-����
	private int func;
	//��һ������
	private PersonalAssistantUI assistantUI;
	//��һ�����嵥����
	private TaskListUI taskListUI;
	//�б�
	private List<JPanel> panels;
	//�����б�
	private List<Task> tasks;
	//�����嵥�б�
	private List<TaskList> taskLists;
	//
	private JPanel tasksPanel;
	//
	private Task task;
	//
	private TaskList taskList;
	
	
	//����
	public tempUI(PersonalAssistant assistant, PersonalAssistantUI assistantUI, String name)
	{
		this.assistant = assistant;
		this.assistantUI = assistantUI;
		this.frame = new JFrame("���ҽ��");
		this.frame.setSize(new Dimension(500,500));
		this.tasksPanel = new JPanel();
		this.tasksPanel.setLayout(new BoxLayout(this.tasksPanel, BoxLayout.Y_AXIS));
		this.scrollPane = new JScrollPane(
				this.tasksPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		this.panels = new ArrayList<JPanel>();
		this.tasks = new ArrayList<Task>();
		this.frame.add(this.scrollPane);
		this.findInit(name);
		
	}
	
	//�ƶ�����
	public tempUI(PersonalAssistant assistant, TaskList taskList, TaskListUI taskListUI, Task task, int func)
	{
		this.assistant = assistant;
		this.taskList = taskList;
		this.taskListUI = taskListUI;
		this.task = task;
		this.func = func;
		String s;
		if(func == 1)
			s = "�ƶ���";
		else
			s = "���Ƶ�";
		
		this.frame = new JFrame(s);
		this.frame.setSize(new Dimension(500,500));
		this.tasksPanel = new JPanel();
		this.tasksPanel.setLayout(new BoxLayout(this.tasksPanel, BoxLayout.Y_AXIS));
		this.scrollPane = new JScrollPane(
				this.tasksPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		this.panels = new ArrayList<JPanel>();
		this.taskLists = new ArrayList<TaskList>();
		this.frame.add(this.scrollPane);
		this.showAllInit();
	}
	
	private void showAllInit()
	{
		List<TaskList> temp = this.assistant.getTaskLists();
		for(int i = 0; i < temp.size(); i++)
		{
			this.taskLists.add(temp.get(i));
			
			JPanel temPanel = new JPanel();
			temPanel.setLayout(new BorderLayout());
			temPanel.add(new JLabel(temp.get(i).getName()), BorderLayout.WEST);
			temPanel.add(new JLabel(temp.get(i).getKind()), BorderLayout.CENTER);
			temPanel.addMouseListener(this);
			
			this.panels.add(temPanel);
			this.tasksPanel.add(temPanel);
		}
		this.frame.setVisible(true);
	}
	
	private void findInit(String name)
	{
		List<TaskList> temp = this.assistant.getTaskLists();
		for(int i = 0; i < temp.size(); i++)
		{
			List<Task> taskTemp = temp.get(i).getTasks();
			for(int j = 0; j < taskTemp.size(); j++)
			{
				if(name.equals(taskTemp.get(j).getName()))
				{
					
					this.tasks.add(taskTemp.get(j));
					JPanel temPanel = new JPanel();
					temPanel.setLayout(new BorderLayout());
					temPanel.add(new JLabel(taskTemp.get(j).getName()), BorderLayout.WEST);
					temPanel.add(new JLabel(taskTemp.get(j).getKind()), BorderLayout.CENTER);
					temPanel.add(new JLabel(temp.get(i).getName()), BorderLayout.EAST);
					temPanel.addMouseListener(this);
					
					this.panels.add(temPanel);
					this.tasksPanel.add(temPanel);
					this.scrollPane.setVisible(true);
					
				}
			}
		}
		if(this.tasks.isEmpty())
			this.frame.add(new JLabel("û�и�����"));
		this.frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		for(int i = 0; i < this.panels.size(); i++)
		{
			if(source.equals(this.panels.get(i)))
			{
				if(this.func == 0)
				{
					new TaskUI(assistantUI, this.tasks.get(i));
					this.frame.dispose();
				}
				//�ƶ�
				else if(this.func == 1)
				{
					this.taskLists.get(i).addTask(this.task);
					this.taskList.deleteTask(this.task);
					this.taskListUI.setVision();
					this.frame.dispose();
				}
				//����
				else if(this.func == 2)
				{
					this.taskLists.get(i).addTask(this.task);
					this.frame.dispose();
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
