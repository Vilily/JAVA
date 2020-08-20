package homework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;













public class TaskUI implements WindowListener,ActionListener, MouseListener{
	//上一界面
	private TaskListUI lastUI;
	//上一界面
	private PersonalAssistantUI assistantUI;
	//是否是主界面
	private boolean isAssistantUI;
	private TaskList taskList;
	//数据
	private Task task;
	//子任务相关
	private TaskUI lastTaskUI;
	private Task lasTask;
	private boolean isSubTask;
	//确认按钮
	private JButton okButton;
	//是否是新建的
	private Boolean isNew;
	//主界面
	private JFrame taskFrame;
	//主Panel
	private JPanel mainPanel;
	//name Label
	private JTextField nameText;
	//内容
	private JTextArea bodyText;
	//kind Label
	private JLabel kindLabel;
	//截止日期
	private JTextField deadLineText;
	//repeat 开始日期
	private JTextField beginDateText;
	//repeat 重复次数
	private JTextField repeatTimesText;
	//repeat 重复周期
	private JTextField repeatRangeText;
	//repeat tasks
	private JScrollPane subTaskScrollPane;
	//long
	private JPanel subTasksPanel;
	//long
	private List<JPanel> subTasksPanels;
	//long
	private List<JButton> subTasksButtons;
	//新建任务的选择按钮
	private JButton tempButton;
	private JButton repeatButton;
	private JButton longTermButton;
	//
	private JButton newButton;
	//完成状态
	private JRadioButton isFinishedButton;
	
	
	//查找查看
	public TaskUI(PersonalAssistantUI assistantUI, Task task)
	{
		this(task);
		this.assistantUI = assistantUI;
		this.isAssistantUI = true;
	}
	//查看任务界面
	public TaskUI(TaskListUI lastUI, Task task)
	{
		this(task);
		this.isAssistantUI = false;
		this.lastUI = lastUI;
	}
	public TaskUI(Task task) 
	{
		this.task = task;
		this.isNew = false;
		this.isSubTask = false;
		this.taskFrame = new JFrame(this.task.getName());
		this.taskFrame.addWindowListener(this);
		this.taskFrame.setSize(500,500);
		
		this.nameText = new JTextField(this.task.getName());
		this.bodyText = new JTextArea(this.task.getBody());
		this.kindLabel = new JLabel(this.task.getKind());
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		if(this.task.getKind().equals("Temp"))
		{
			this.tempTaskInit();
		}
		else if(this.task.getKind().equals("Repeat"))
		{
			this.repeatTaskInit();
		}
		else
		{
			this.longTermTaskInit();
		}
		
	}
	
	//长期任务的子任务界面
	public TaskUI(TaskUI lastUI, Task task, int useLess)
	{
		this.isAssistantUI = false;
		this.isSubTask = true;
		this.isNew = false;
		this.task = task;
		this.lastTaskUI = lastUI;
		this.taskFrame = new JFrame();
		this.taskFrame.setSize(500,500);
		this.taskFrame.addWindowListener(this);
		this.taskFrame.setVisible(true);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		this.taskFrame.add(this.mainPanel);
		this.mainPanel.removeAll();
		this.nameText = new JTextField(this.task.getName());
		this.bodyText = new JTextArea(this.task.getBody());
		this.kindLabel = new JLabel(this.task.getKind());
		
		this.longTermTaskInit();
	}
	
	//新建普通任务
	public TaskUI(TaskListUI lastUI, TaskList taskList)
	{
		this.isAssistantUI = false;
		this.isSubTask = false;
		this.isNew = true;
		this.lastUI = lastUI;
		this.taskList = taskList;
		this.taskFrame = new JFrame();
		
		this.taskFrame.setSize(500,500);
		this.taskFrame.addWindowListener(this);
		this.taskFrame.setVisible(true);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		this.mainPanel.add(new JLabel("请选择新建的任务类型"));
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
		this.tempButton = new JButton("临时任务");
		this.tempButton.addActionListener(this);
		this.repeatButton = new JButton("重复任务");
		this.repeatButton.addActionListener(this);
		this.longTermButton = new JButton("长期任务");
		this.longTermButton.addActionListener(this);
		temp.add(this.tempButton);
		temp.add(this.longTermButton);
		temp.add(this.repeatButton);
		this.mainPanel.add(temp);
		this.taskFrame.add(this.mainPanel);
	}
	
	//新建子任务
	public TaskUI(TaskUI lastUI, Task lasTask)
	{
		this.isAssistantUI = false;
		this.isSubTask = true;
		this.isNew = true;
		this.lasTask = lasTask;
		this.lastTaskUI = lastUI;
		this.taskFrame = new JFrame();
		this.taskFrame.setSize(500,500);
		this.taskFrame.addWindowListener(this);
		this.taskFrame.setVisible(true);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		this.taskFrame.add(this.mainPanel);
		this.task = new LongTermTask("Please Input name", "Please Input things");
		this.nameText = new JTextField(this.task.getName());
		this.bodyText = new JTextArea(this.task.getBody());
		this.kindLabel = new JLabel(this.task.getKind());
		
		this.longTermTaskInit();
	}
	
	//短期任务初始化
	private void tempTaskInit() {
		this.bodyText = new JTextArea(this.task.getBody());
		//格式化输出
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.deadLineText = new JTextField(df.format(this.task.getDeadLineDate()));
		//种类
		this.mainPanel.add(this.kindLabel);
		//名称
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 100));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("名称"));
		namePanel.add(this.nameText);
		//描述
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 200));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("描述"));
		bodyPanel.add(this.bodyText);
		//截止日期
		JPanel datePanel = new JPanel();
		datePanel.setPreferredSize(new Dimension(500, 100));
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		datePanel.add(new JLabel("截止日期"));
		datePanel.add(this.deadLineText);
		//按钮Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("已经完成");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("确认修改");
		this.okButton.addActionListener(this);
		buttonPanel.add(this.isFinishedButton);
		buttonPanel.add(this.okButton);
		
		this.mainPanel.add(namePanel);
		this.mainPanel.add(bodyPanel);
		this.mainPanel.add(datePanel);
		this.mainPanel.add(buttonPanel);
		this.taskFrame.add(this.mainPanel);
		
		this.taskFrame.setVisible(true);
	}
	
	//长期任务初始化
	private void longTermTaskInit() {
		//格式化输出
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.deadLineText = new JTextField(df.format(this.task.getDeadLineDate()));
		//名称
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 70));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("名称"));
		namePanel.add(this.nameText);
		//描述
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 70));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("描述"));
		bodyPanel.add(this.bodyText);
		//截止日期
		JPanel deadDatePanel = new JPanel();
		deadDatePanel.setPreferredSize(new Dimension(500, 70));
		deadDatePanel.setLayout(new BoxLayout(deadDatePanel, BoxLayout.X_AXIS));
		deadDatePanel.add(new JLabel("开始日期："));
		deadDatePanel.add(this.deadLineText);
		//按钮Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("已经完成");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("确认修改");
		this.okButton.addActionListener(this);
		this.newButton = new JButton("新建子任务");
		this.newButton.addActionListener(this);
		buttonPanel.add(this.isFinishedButton);
		buttonPanel.add(this.okButton);
		buttonPanel.add(this.newButton);
		//
		this.mainPanel.add(this.kindLabel);
		this.mainPanel.add(namePanel);
		this.mainPanel.add(bodyPanel);
		this.mainPanel.add(deadDatePanel);
		this.mainPanel.add(buttonPanel);
		
		this.subTasksPanel = new JPanel();
		this.subTasksPanel.setLayout(new BoxLayout(this.subTasksPanel, BoxLayout.Y_AXIS));
		this.subTasksPanels = new ArrayList<JPanel>();
		this.subTasksButtons = new ArrayList<JButton>();
		
		this.subTaskScrollPane = new JScrollPane(
				this.subTasksPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		this.subTaskScrollPane.setPreferredSize(new Dimension(500, 200));
		this.subTaskScrollPane.setVisible(true);
		
		this.mainPanel.add(this.subTaskScrollPane);
		this.upDateSubTasks();
		
		this.taskFrame.add(this.mainPanel);
		this.taskFrame.setVisible(true);
	}
	
	//周期任务初始化
	private void repeatTaskInit() {
		this.bodyText = new JTextArea(this.task.getBody());
		//格式化输出
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		this.beginDateText = new JTextField(df.format(this.task.getBeginDate()));
		this.repeatRangeText = new JTextField(this.task.getRangeYear() + "-" + this.task.getRangeMonth() + "-" + this.task.getRangeDay());
		this.repeatTimesText = new JTextField(Integer.toString(this.task.getRepeatTimes()));
		
		//名称
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 70));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("名称"));
		namePanel.add(this.nameText);
		//描述
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 150));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("描述"));
		bodyPanel.add(this.bodyText);
		//开始日期
		JPanel beginDatePanel = new JPanel();
		beginDatePanel.setPreferredSize(new Dimension(500, 70));
		beginDatePanel.setLayout(new BoxLayout(beginDatePanel, BoxLayout.X_AXIS));
		beginDatePanel.add(new JLabel("开始日期："));
		beginDatePanel.add(this.beginDateText);
		//周期
		JPanel rangeDatePanel = new JPanel();
		rangeDatePanel.setPreferredSize(new Dimension(500, 70));
		rangeDatePanel.setLayout(new BoxLayout(rangeDatePanel, BoxLayout.X_AXIS));
		rangeDatePanel.add(new JLabel("重复周期："));
		rangeDatePanel.add(this.repeatRangeText);
		//重复次数
		JPanel repeatTimesPanel = new JPanel();
		repeatTimesPanel.setPreferredSize(new Dimension(500, 70));
		repeatTimesPanel.setLayout(new BoxLayout(repeatTimesPanel, BoxLayout.X_AXIS));
		repeatTimesPanel.add(new JLabel("重复次数："));
		repeatTimesPanel.add(this.repeatTimesText);
		//按钮Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 70));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("已经完成");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("确认修改");
		this.okButton.addActionListener(this);
		buttonPanel.add(this.isFinishedButton);
		buttonPanel.add(this.okButton);
		
		//
		this.mainPanel.add(this.kindLabel);
		this.mainPanel.add(namePanel);
		this.mainPanel.add(bodyPanel);
		this.mainPanel.add(beginDatePanel);
		this.mainPanel.add(rangeDatePanel);
		this.mainPanel.add(repeatTimesPanel);
		this.mainPanel.add(buttonPanel);
		this.taskFrame.add(this.mainPanel);
		this.taskFrame.setVisible(true);
	}
	
	//子任务界面的更新
	private void upDateSubTasks() 
	{
		this.subTasksPanel.removeAll();
		this.subTasksPanels.clear();
		this.subTasksButtons.clear();
		List<Task> temp = this.task.getTasks();
		for(int i = 0; i < temp.size(); i++)
		{
			JPanel tmp = new JPanel();
			tmp.addMouseListener(this);
			tmp.setLayout(new BoxLayout(tmp, BoxLayout.X_AXIS));
			tmp.add(new JLabel(temp.get(i).getName()));
			tmp.add(new JLabel(temp.get(i).getKind()));
			JButton tempButton = new JButton("删除");
			tempButton.addActionListener(this);
			
			this.subTasksButtons.add(tempButton);
			tmp.add(tempButton);
			this.subTasksPanels.add(tmp);
			this.subTasksPanel.add(tmp);
		}
		this.subTasksPanel.repaint();
		this.subTaskScrollPane.setVisible(true);
	}
	//下一界面返回这一界面
	public void setVision() 
	{
		this.upDateSubTasks();
		this.taskFrame.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(this.isAssistantUI)
			this.assistantUI.setVision();
		else if(this.isSubTask)
			this.lastTaskUI.setVision();
		else
			this.lastUI.setVision();
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
		//确认修改
		if(source == this.okButton)
		{
			this.task.setName(this.nameText.getText());
			this.task.setBody(this.bodyText.getText());
			this.task.setIsFinished(this.isFinishedButton.isSelected());
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			if(this.task.getKind().equals("Temp"))
			{
				Date d;
				try {
					d = sdf.parse(this.deadLineText.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
					d = new Date();
				}
				this.task.setDeadLineDate(d);
			}
			else if(this.task.getKind().equals("Repeat"))
			{
				Date begin;
				try {
					begin = sdf.parse(this.beginDateText.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
					begin = new Date();
				}
				this.task.setBeginDate(begin);
				
				String s = this.repeatRangeText.getText();
				String []ss = s.split("-");
				this.task.setRangeYear(Integer.parseInt(ss[0]));
				this.task.setRangeMonth(Integer.parseInt(ss[1]));
				this.task.setRangeDay(Integer.parseInt(ss[2]));
				
				this.task.setRepeatTimes(Integer.parseInt(this.repeatTimesText.getText()));
			}
			else if(this.task.getKind().equals("LongTerm"))
			{
				Date d;
				try {
					d = sdf.parse(this.deadLineText.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
					d = new Date();
				}
				this.task.setDeadLineDate(d);
				
			}
			//添加新建的任务
			if(this.isSubTask)
			{
				if(this.isNew)
				{
					System.out.println(this.task.getName());
					this.lasTask.addTask(this.task);
				}
				//关闭窗口
				this.taskFrame.dispose();
				this.lastTaskUI.setVision();
			}
			else
			{
				if(this.isNew)
				{
					this.taskList.addTask(this.task);
				}
				//关闭窗口
				this.taskFrame.dispose();
				if(!this.isAssistantUI)
					this.lastUI.setVision();
			}
			
		}
		//新建临时任务
		else if(source == this.tempButton)
		{
			this.mainPanel.removeAll();
			this.task = new TempTask("Please Input name", "Please input things", Calendar.getInstance().getTime());
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.tempTaskInit();
		}
		//新建周期任务
		else if(source == this.repeatButton)
		{
			this.mainPanel.removeAll();
			this.task = new RepeatTask("Please Input name", "Please input things");
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.repeatTaskInit();
		}
		//新建long
		else if(source == this.longTermButton)
		{
			this.mainPanel.removeAll();
			this.task = new LongTermTask("Please Input name", "Please input things");
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.longTermTaskInit();
		}
		//新建子任务
		else if(source == this.newButton)
		{
			new TaskUI(this, this.task);
			this.taskFrame.setVisible(false);
		}
		//删除按钮响应
		else
		{
			for(int i = 0; i < this.subTasksButtons.size(); i++)
			{
				if(source == this.subTasksButtons.get(i))
				{
					this.task.deleteTaskByIndex(i);
					this.upDateSubTasks();
					this.taskFrame.setVisible(true);
					break;
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
		//修改子任务
		Object source = e.getSource();
		for(int i = 0; i < this.subTasksPanels.size(); i++)
		{
			if(source.equals(this.subTasksPanels.get(i)))
			{
				new TaskUI(this, this.task.getTaskByIndex(i), 0);
				this.taskFrame.setVisible(false);
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
