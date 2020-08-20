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
	//��һ����
	private TaskListUI lastUI;
	//��һ����
	private PersonalAssistantUI assistantUI;
	//�Ƿ���������
	private boolean isAssistantUI;
	private TaskList taskList;
	//����
	private Task task;
	//���������
	private TaskUI lastTaskUI;
	private Task lasTask;
	private boolean isSubTask;
	//ȷ�ϰ�ť
	private JButton okButton;
	//�Ƿ����½���
	private Boolean isNew;
	//������
	private JFrame taskFrame;
	//��Panel
	private JPanel mainPanel;
	//name Label
	private JTextField nameText;
	//����
	private JTextArea bodyText;
	//kind Label
	private JLabel kindLabel;
	//��ֹ����
	private JTextField deadLineText;
	//repeat ��ʼ����
	private JTextField beginDateText;
	//repeat �ظ�����
	private JTextField repeatTimesText;
	//repeat �ظ�����
	private JTextField repeatRangeText;
	//repeat tasks
	private JScrollPane subTaskScrollPane;
	//long
	private JPanel subTasksPanel;
	//long
	private List<JPanel> subTasksPanels;
	//long
	private List<JButton> subTasksButtons;
	//�½������ѡ��ť
	private JButton tempButton;
	private JButton repeatButton;
	private JButton longTermButton;
	//
	private JButton newButton;
	//���״̬
	private JRadioButton isFinishedButton;
	
	
	//���Ҳ鿴
	public TaskUI(PersonalAssistantUI assistantUI, Task task)
	{
		this(task);
		this.assistantUI = assistantUI;
		this.isAssistantUI = true;
	}
	//�鿴�������
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
	
	//������������������
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
	
	//�½���ͨ����
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
		
		this.mainPanel.add(new JLabel("��ѡ���½�����������"));
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
		this.tempButton = new JButton("��ʱ����");
		this.tempButton.addActionListener(this);
		this.repeatButton = new JButton("�ظ�����");
		this.repeatButton.addActionListener(this);
		this.longTermButton = new JButton("��������");
		this.longTermButton.addActionListener(this);
		temp.add(this.tempButton);
		temp.add(this.longTermButton);
		temp.add(this.repeatButton);
		this.mainPanel.add(temp);
		this.taskFrame.add(this.mainPanel);
	}
	
	//�½�������
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
	
	//���������ʼ��
	private void tempTaskInit() {
		this.bodyText = new JTextArea(this.task.getBody());
		//��ʽ�����
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.deadLineText = new JTextField(df.format(this.task.getDeadLineDate()));
		//����
		this.mainPanel.add(this.kindLabel);
		//����
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 100));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("����"));
		namePanel.add(this.nameText);
		//����
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 200));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("����"));
		bodyPanel.add(this.bodyText);
		//��ֹ����
		JPanel datePanel = new JPanel();
		datePanel.setPreferredSize(new Dimension(500, 100));
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		datePanel.add(new JLabel("��ֹ����"));
		datePanel.add(this.deadLineText);
		//��ťPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("�Ѿ����");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("ȷ���޸�");
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
	
	//���������ʼ��
	private void longTermTaskInit() {
		//��ʽ�����
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.deadLineText = new JTextField(df.format(this.task.getDeadLineDate()));
		//����
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 70));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("����"));
		namePanel.add(this.nameText);
		//����
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 70));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("����"));
		bodyPanel.add(this.bodyText);
		//��ֹ����
		JPanel deadDatePanel = new JPanel();
		deadDatePanel.setPreferredSize(new Dimension(500, 70));
		deadDatePanel.setLayout(new BoxLayout(deadDatePanel, BoxLayout.X_AXIS));
		deadDatePanel.add(new JLabel("��ʼ���ڣ�"));
		deadDatePanel.add(this.deadLineText);
		//��ťPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("�Ѿ����");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("ȷ���޸�");
		this.okButton.addActionListener(this);
		this.newButton = new JButton("�½�������");
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
	
	//���������ʼ��
	private void repeatTaskInit() {
		this.bodyText = new JTextArea(this.task.getBody());
		//��ʽ�����
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		this.beginDateText = new JTextField(df.format(this.task.getBeginDate()));
		this.repeatRangeText = new JTextField(this.task.getRangeYear() + "-" + this.task.getRangeMonth() + "-" + this.task.getRangeDay());
		this.repeatTimesText = new JTextField(Integer.toString(this.task.getRepeatTimes()));
		
		//����
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 70));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(new JLabel("����"));
		namePanel.add(this.nameText);
		//����
		JPanel  bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(500, 150));
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(new JLabel("����"));
		bodyPanel.add(this.bodyText);
		//��ʼ����
		JPanel beginDatePanel = new JPanel();
		beginDatePanel.setPreferredSize(new Dimension(500, 70));
		beginDatePanel.setLayout(new BoxLayout(beginDatePanel, BoxLayout.X_AXIS));
		beginDatePanel.add(new JLabel("��ʼ���ڣ�"));
		beginDatePanel.add(this.beginDateText);
		//����
		JPanel rangeDatePanel = new JPanel();
		rangeDatePanel.setPreferredSize(new Dimension(500, 70));
		rangeDatePanel.setLayout(new BoxLayout(rangeDatePanel, BoxLayout.X_AXIS));
		rangeDatePanel.add(new JLabel("�ظ����ڣ�"));
		rangeDatePanel.add(this.repeatRangeText);
		//�ظ�����
		JPanel repeatTimesPanel = new JPanel();
		repeatTimesPanel.setPreferredSize(new Dimension(500, 70));
		repeatTimesPanel.setLayout(new BoxLayout(repeatTimesPanel, BoxLayout.X_AXIS));
		repeatTimesPanel.add(new JLabel("�ظ�������"));
		repeatTimesPanel.add(this.repeatTimesText);
		//��ťPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 70));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		this.isFinishedButton = new JRadioButton("�Ѿ����");
		this.isFinishedButton.setSelected(this.task.getIsFinished());
		this.okButton = new JButton("ȷ���޸�");
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
	
	//���������ĸ���
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
			JButton tempButton = new JButton("ɾ��");
			tempButton.addActionListener(this);
			
			this.subTasksButtons.add(tempButton);
			tmp.add(tempButton);
			this.subTasksPanels.add(tmp);
			this.subTasksPanel.add(tmp);
		}
		this.subTasksPanel.repaint();
		this.subTaskScrollPane.setVisible(true);
	}
	//��һ���淵����һ����
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
		//ȷ���޸�
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
			//����½�������
			if(this.isSubTask)
			{
				if(this.isNew)
				{
					System.out.println(this.task.getName());
					this.lasTask.addTask(this.task);
				}
				//�رմ���
				this.taskFrame.dispose();
				this.lastTaskUI.setVision();
			}
			else
			{
				if(this.isNew)
				{
					this.taskList.addTask(this.task);
				}
				//�رմ���
				this.taskFrame.dispose();
				if(!this.isAssistantUI)
					this.lastUI.setVision();
			}
			
		}
		//�½���ʱ����
		else if(source == this.tempButton)
		{
			this.mainPanel.removeAll();
			this.task = new TempTask("Please Input name", "Please input things", Calendar.getInstance().getTime());
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.tempTaskInit();
		}
		//�½���������
		else if(source == this.repeatButton)
		{
			this.mainPanel.removeAll();
			this.task = new RepeatTask("Please Input name", "Please input things");
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.repeatTaskInit();
		}
		//�½�long
		else if(source == this.longTermButton)
		{
			this.mainPanel.removeAll();
			this.task = new LongTermTask("Please Input name", "Please input things");
			this.nameText = new JTextField(this.task.getName());
			this.bodyText = new JTextArea(this.task.getBody());
			this.kindLabel = new JLabel(this.task.getKind());
			
			this.longTermTaskInit();
		}
		//�½�������
		else if(source == this.newButton)
		{
			new TaskUI(this, this.task);
			this.taskFrame.setVisible(false);
		}
		//ɾ����ť��Ӧ
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
		//�޸�������
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
