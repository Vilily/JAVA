package homework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;









public class TaskListUI implements WindowListener, ActionListener,MouseListener{
	//信息数据
	private TaskList taskList;
	//修改按钮
	private JButton changeInfoButton;
	//确认按钮
	private JButton okButton;
	//新建按钮
	private JButton newButton;
	//信息的序号，index为-1表示为新建
	private int index;
	//主框架
	private JFrame taskListFrame;
	//taskList信息显示Panel
	private JPanel taskListInfoPanel;
	//taskList修改Panel
	private JPanel taskListInfoChangePanel;
	//总Panel
	private JPanel mainPanel;
	//滚动Panel,显示tasks
	private JScrollPane scrollPane;
	//新建任务清单时需要向里面添加
	private PersonalAssistant assistant;
	//放置tasks的
	private JPanel tasksPanel;
	//放置上一级UI
	private PersonalAssistantUI lastUI;
	//tasks的Panels
	private List<JPanel> listTaskPanels;
	//显示任务清单名称的Label
	private JLabel nameLabel;
	//显示任务清单类别的Label
	private JLabel kindLabel;
	//修改任务清单名称的Text
	private JTextField nameTextField;
	//修改任务清单的类别Text
	private JTextField kindTextField;
	//按日期排序按钮
	private JButton sortByNameButton;
	//按种类排序
	private JButton sortByKindButton;
	//按完成状况排序
	private JButton sortByIsFinishedButton;
	
	
	
	//查看任务清单的界面
	public TaskListUI(TaskList taskList, int index, PersonalAssistantUI lastUI, PersonalAssistant assistant)
	{
		this.taskList = taskList;
		this.index = index;
		this.lastUI = lastUI;
		this.assistant = assistant;
		this.listTaskPanels = new ArrayList<JPanel>();
		this.FrameInit();
	}
	
	//新建的任务清单界面
	public TaskListUI(PersonalAssistant assistant, PersonalAssistantUI lastUI)
	{
		this.taskList = new TaskList("Please input name", "Please input kind");
		this.index = -1;
		this.assistant = assistant;
		this.lastUI = lastUI;
		this.listTaskPanels = new ArrayList<JPanel>();
		this.FrameInit();
		//初始化修改界面
		this.changeInfoButtonPerforming();
		
	}
	
	private void FrameInit()
	{
		//设置主窗口
		this.taskListFrame = new JFrame(taskList.getName());
		this.taskListFrame.addWindowListener(this);
		this.taskListFrame.setSize(1000,800);
		//整个界面的Panel
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        //新建任务清单信息Panel，包括内容
        this.initTaskListInfoPanel();
        //主Panel添加信息Panel
        this.mainPanel.add(this.taskListInfoPanel);
        
        //task信息Panel
        this.tasksPanel = new JPanel();
        this.tasksPanel.setLayout(new BoxLayout(this.tasksPanel, BoxLayout.Y_AXIS));
        this.upDateTasks();
        
        //设置 滚动内容
		this.scrollPane = new JScrollPane(
				this.tasksPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		this.scrollPane.setPreferredSize(new Dimension(1000, 600));
		this.scrollPane.setVisible(true);
		//主Panel添加滚动内容
		this.mainPanel.add(this.scrollPane, BorderLayout.SOUTH);
		
		this.taskListFrame.add(this.mainPanel);
		this.taskListFrame.setVisible(true);
		
	}
	
	//初始化任务清单的清单信息Panel
	private void initTaskListInfoPanel() 
	{
		//taskList信息Panel
		this.taskListInfoPanel = new JPanel();
        this.taskListInfoPanel.setLayout(new BoxLayout(this.taskListInfoPanel, BoxLayout.Y_AXIS));
        this.taskListInfoPanel.setPreferredSize(new Dimension(1000, 200));
        //名称
        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(1000, 70));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JLabel("名称:	"));
        this.nameLabel = new JLabel(this.taskList.getName());
        namePanel.add(this.nameLabel);
        this.taskListInfoPanel.add(namePanel);
        //类别
        JPanel kindPanel = new JPanel();
        kindPanel.setPreferredSize(new Dimension(1000, 70));
        kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
        kindPanel.add(new JLabel("类别:	"));
        this.kindLabel = new JLabel(this.taskList.getKind());
        kindPanel.add(this.kindLabel);
        this.taskListInfoPanel.add(kindPanel);
        
        //加上修改按钮和新建按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1000, 60));
        this.changeInfoButton = new JButton("修改信息");
        this.changeInfoButton.addActionListener(this);
        this.newButton = new JButton("新建任务");
        this.newButton.addActionListener(this);
        this.sortByNameButton = new JButton("按名称排序");
        this.sortByNameButton.addActionListener(this);
        this.sortByKindButton = new JButton("按种类排序");
        this.sortByKindButton.addActionListener(this);
        this.sortByIsFinishedButton = new JButton("按完成状况排序");
        this.sortByIsFinishedButton.addActionListener(this);
        buttonPanel.add(this.changeInfoButton);
        buttonPanel.add(this.newButton);
        buttonPanel.add(this.sortByNameButton);
        buttonPanel.add(this.sortByKindButton);
        buttonPanel.add(this.sortByIsFinishedButton);
        this.taskListInfoPanel.add(buttonPanel);
	}

	
	//更新任务清单的信息界面
	private void upDateListInfoPanel()
	{
		//更新信息
		this.mainPanel.removeAll();
		this.nameLabel.setText(this.taskList.getName());
		this.kindLabel.setText(this.taskList.getKind());
		
		this.mainPanel.add(this.taskListInfoPanel);
		this.mainPanel.add(this.scrollPane, BorderLayout.SOUTH);
		this.taskListFrame.repaint();
		this.taskListFrame.setVisible(true);
	}
	
	//更新任务滚动列表的界面
	private void upDateTasks()
	{
		this.listTaskPanels.clear();
		this.tasksPanel.removeAll();
		List<Task> temp = this.taskList.getTasks();
		for(int i = 0;i < temp.size(); i++)
		{
			JPanel tmPanel = new JPanel();
			tmPanel.setLayout(new BorderLayout());
			tmPanel.addMouseListener(this);
			JLabel nameLabel = new JLabel(temp.get(i).getName());
			nameLabel.setPreferredSize(new Dimension(400, 50));
			tmPanel.add(nameLabel, BorderLayout.WEST);
			JLabel kindLabel = new JLabel(temp.get(i).getKind());
			kindLabel.setPreferredSize(new Dimension(400, 50));
			tmPanel.add(kindLabel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			JButton removeButton = new JButton("移动");
			removeButton.addActionListener(this);
			buttonPanel.add(removeButton, BorderLayout.WEST);
			
			JButton copyButton = new JButton("复制");
			copyButton.addActionListener(this);
			buttonPanel.add(copyButton, BorderLayout.CENTER);
			
			JButton tempButton = new JButton("删除");
			tempButton.addActionListener(this);
			buttonPanel.add(tempButton, BorderLayout.EAST);
			
			tmPanel.add(buttonPanel, BorderLayout.EAST);
			this.listTaskPanels.add(tmPanel);
			this.tasksPanel.add(tmPanel);
		}
		this.tasksPanel.repaint();
		this.taskListFrame.setVisible(true);
	}
	
	//下一界面返回这一界面
	public void setVision() {
		this.upDateTasks();
		this.taskListFrame.setVisible(true);
	}
	
	//按名称排序
	private void sortByName()
	{
		this.taskList.sortByName();
		this.upDateTasks();			
	}
	//按类别排序
	private void sortByKind()
	{
		this.taskList.sortByKind();
		this.upDateTasks();
	}
	//按完成情况排序
	private void sortByIsFinished()
	{
		this.taskList.sortByIsFinished();
		this.upDateTasks();
	}
	
	//修改任务清单信息界面
	private void changeInfoButtonPerforming()
	{
		if(this.okButton == null)
		{
			this.okButton = new JButton("确认修改");
			this.okButton.addActionListener(this);
		}
			
		if(this.taskListInfoChangePanel == null)
		{
			System.out.println("this");
			this.taskListInfoChangePanel = new JPanel();
		    this.taskListInfoChangePanel.setLayout(new BoxLayout(this.taskListInfoChangePanel, BoxLayout.Y_AXIS));
		    this.taskListInfoChangePanel.setPreferredSize(new Dimension(1000, 200));
		    
		    //输入名称
		    JPanel nameTextPanel = new JPanel();
		    nameTextPanel.setPreferredSize(new Dimension(1000, 60));
		    nameTextPanel.setLayout(new BoxLayout(nameTextPanel, BoxLayout.X_AXIS));
		    this.nameTextField = new JTextField(this.taskList.getName());
		    nameTextPanel.add(new JLabel("请输入任务清单名称："));
		    nameTextPanel.add(this.nameTextField);
		    
		    //输入类别
		    JPanel kindTextPanel = new JPanel();
		    kindTextPanel.setPreferredSize(new Dimension(1000, 60));
		    kindTextPanel.setLayout(new BoxLayout(kindTextPanel, BoxLayout.X_AXIS));
		    this.kindTextField = new JTextField(this.taskList.getKind());
		    kindTextPanel.add(new JLabel("请输入任务清单类别："));
		    kindTextPanel.add(this.kindTextField);
		    
		    this.taskListInfoChangePanel.add(nameTextPanel);
		    this.taskListInfoChangePanel.add(kindTextPanel);
		    JPanel changeInfoButtonPanel = new JPanel();
		    changeInfoButtonPanel.setPreferredSize(new Dimension(1000, 80));
		    changeInfoButtonPanel.add(this.okButton);
		    this.taskListInfoChangePanel.add(changeInfoButtonPanel);
		}
		else 
		{
			this.nameTextField.setText(this.taskList.getName());
			this.kindTextField.setText(this.taskList.getKind());
		}
		this.mainPanel.removeAll();
		this.mainPanel.add(this.taskListInfoChangePanel);
		this.mainPanel.add(this.scrollPane);
		this.taskListFrame.repaint();
		this.taskListFrame.setVisible(true);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		if(this.index == -1)
		{
			this.assistant.addTaskList(this.taskList);
		}
		this.lastUI.setVision();
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//修改界面
		if(source == this.changeInfoButton)
		{
			this.changeInfoButtonPerforming();
		}
		//确认界面
		else if(source == this.okButton)
		{
			this.mainPanel.removeAll();
			this.taskList.setName(this.nameTextField.getText());
			this.taskList.setKind(this.kindTextField.getText());
			//更新界面
			this.upDateListInfoPanel();
		}
		//新建按钮
		else if(source == this.newButton)
		{
			new TaskUI(this, this.taskList);
			this.taskListFrame.setVisible(false);
		}
		//按名称排序
		else if(source == this.sortByNameButton)
		{
			this.sortByName();
		}
		//按种类排序
		else if(source == this.sortByKindButton)
		{
			this.sortByKind();
		}
		//按完成情况排序
		else if(source == this.sortByIsFinishedButton)
		{
			this.sortByIsFinished();
		}
		//删除
		else 
		{
			for(int i = 0; i < this.listTaskPanels.size(); i++)
			{
				if(source.equals(((JPanel)this.listTaskPanels.get(i).getComponent(2)).getComponent(2)))
				{
					this.taskList.deleteByIndex(i);
					this.upDateTasks();
					this.taskListFrame.setVisible(true);
				}
				else if(source.equals(((JPanel)this.listTaskPanels.get(i).getComponent(2)).getComponent(0)))
				{
					new tempUI(this.assistant, this.taskList, this, this.taskList.getByIndex(i), 1);
				}
				else if(source.equals(((JPanel)this.listTaskPanels.get(i).getComponent(2)).getComponent(1)))
				{
					new tempUI(this.assistant, this.taskList, this, this.taskList.getByIndex(i), 2);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//listUI调用
		Object source = e.getSource();
		for(int i  = 0; i < this.listTaskPanels.size(); i++)
		{
			if(source.equals(this.listTaskPanels.get(i)))
			{
				new TaskUI(this, this.taskList.getTaskByIndex(i));
				this.taskListFrame.setVisible(false);
			}
		}
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
