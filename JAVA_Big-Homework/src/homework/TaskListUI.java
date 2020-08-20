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
	//��Ϣ����
	private TaskList taskList;
	//�޸İ�ť
	private JButton changeInfoButton;
	//ȷ�ϰ�ť
	private JButton okButton;
	//�½���ť
	private JButton newButton;
	//��Ϣ����ţ�indexΪ-1��ʾΪ�½�
	private int index;
	//�����
	private JFrame taskListFrame;
	//taskList��Ϣ��ʾPanel
	private JPanel taskListInfoPanel;
	//taskList�޸�Panel
	private JPanel taskListInfoChangePanel;
	//��Panel
	private JPanel mainPanel;
	//����Panel,��ʾtasks
	private JScrollPane scrollPane;
	//�½������嵥ʱ��Ҫ���������
	private PersonalAssistant assistant;
	//����tasks��
	private JPanel tasksPanel;
	//������һ��UI
	private PersonalAssistantUI lastUI;
	//tasks��Panels
	private List<JPanel> listTaskPanels;
	//��ʾ�����嵥���Ƶ�Label
	private JLabel nameLabel;
	//��ʾ�����嵥����Label
	private JLabel kindLabel;
	//�޸������嵥���Ƶ�Text
	private JTextField nameTextField;
	//�޸������嵥�����Text
	private JTextField kindTextField;
	//����������ť
	private JButton sortByNameButton;
	//����������
	private JButton sortByKindButton;
	//�����״������
	private JButton sortByIsFinishedButton;
	
	
	
	//�鿴�����嵥�Ľ���
	public TaskListUI(TaskList taskList, int index, PersonalAssistantUI lastUI, PersonalAssistant assistant)
	{
		this.taskList = taskList;
		this.index = index;
		this.lastUI = lastUI;
		this.assistant = assistant;
		this.listTaskPanels = new ArrayList<JPanel>();
		this.FrameInit();
	}
	
	//�½��������嵥����
	public TaskListUI(PersonalAssistant assistant, PersonalAssistantUI lastUI)
	{
		this.taskList = new TaskList("Please input name", "Please input kind");
		this.index = -1;
		this.assistant = assistant;
		this.lastUI = lastUI;
		this.listTaskPanels = new ArrayList<JPanel>();
		this.FrameInit();
		//��ʼ���޸Ľ���
		this.changeInfoButtonPerforming();
		
	}
	
	private void FrameInit()
	{
		//����������
		this.taskListFrame = new JFrame(taskList.getName());
		this.taskListFrame.addWindowListener(this);
		this.taskListFrame.setSize(1000,800);
		//���������Panel
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        //�½������嵥��ϢPanel����������
        this.initTaskListInfoPanel();
        //��Panel�����ϢPanel
        this.mainPanel.add(this.taskListInfoPanel);
        
        //task��ϢPanel
        this.tasksPanel = new JPanel();
        this.tasksPanel.setLayout(new BoxLayout(this.tasksPanel, BoxLayout.Y_AXIS));
        this.upDateTasks();
        
        //���� ��������
		this.scrollPane = new JScrollPane(
				this.tasksPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		this.scrollPane.setPreferredSize(new Dimension(1000, 600));
		this.scrollPane.setVisible(true);
		//��Panel��ӹ�������
		this.mainPanel.add(this.scrollPane, BorderLayout.SOUTH);
		
		this.taskListFrame.add(this.mainPanel);
		this.taskListFrame.setVisible(true);
		
	}
	
	//��ʼ�������嵥���嵥��ϢPanel
	private void initTaskListInfoPanel() 
	{
		//taskList��ϢPanel
		this.taskListInfoPanel = new JPanel();
        this.taskListInfoPanel.setLayout(new BoxLayout(this.taskListInfoPanel, BoxLayout.Y_AXIS));
        this.taskListInfoPanel.setPreferredSize(new Dimension(1000, 200));
        //����
        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(1000, 70));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JLabel("����:	"));
        this.nameLabel = new JLabel(this.taskList.getName());
        namePanel.add(this.nameLabel);
        this.taskListInfoPanel.add(namePanel);
        //���
        JPanel kindPanel = new JPanel();
        kindPanel.setPreferredSize(new Dimension(1000, 70));
        kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
        kindPanel.add(new JLabel("���:	"));
        this.kindLabel = new JLabel(this.taskList.getKind());
        kindPanel.add(this.kindLabel);
        this.taskListInfoPanel.add(kindPanel);
        
        //�����޸İ�ť���½���ť
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1000, 60));
        this.changeInfoButton = new JButton("�޸���Ϣ");
        this.changeInfoButton.addActionListener(this);
        this.newButton = new JButton("�½�����");
        this.newButton.addActionListener(this);
        this.sortByNameButton = new JButton("����������");
        this.sortByNameButton.addActionListener(this);
        this.sortByKindButton = new JButton("����������");
        this.sortByKindButton.addActionListener(this);
        this.sortByIsFinishedButton = new JButton("�����״������");
        this.sortByIsFinishedButton.addActionListener(this);
        buttonPanel.add(this.changeInfoButton);
        buttonPanel.add(this.newButton);
        buttonPanel.add(this.sortByNameButton);
        buttonPanel.add(this.sortByKindButton);
        buttonPanel.add(this.sortByIsFinishedButton);
        this.taskListInfoPanel.add(buttonPanel);
	}

	
	//���������嵥����Ϣ����
	private void upDateListInfoPanel()
	{
		//������Ϣ
		this.mainPanel.removeAll();
		this.nameLabel.setText(this.taskList.getName());
		this.kindLabel.setText(this.taskList.getKind());
		
		this.mainPanel.add(this.taskListInfoPanel);
		this.mainPanel.add(this.scrollPane, BorderLayout.SOUTH);
		this.taskListFrame.repaint();
		this.taskListFrame.setVisible(true);
	}
	
	//������������б�Ľ���
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
			JButton removeButton = new JButton("�ƶ�");
			removeButton.addActionListener(this);
			buttonPanel.add(removeButton, BorderLayout.WEST);
			
			JButton copyButton = new JButton("����");
			copyButton.addActionListener(this);
			buttonPanel.add(copyButton, BorderLayout.CENTER);
			
			JButton tempButton = new JButton("ɾ��");
			tempButton.addActionListener(this);
			buttonPanel.add(tempButton, BorderLayout.EAST);
			
			tmPanel.add(buttonPanel, BorderLayout.EAST);
			this.listTaskPanels.add(tmPanel);
			this.tasksPanel.add(tmPanel);
		}
		this.tasksPanel.repaint();
		this.taskListFrame.setVisible(true);
	}
	
	//��һ���淵����һ����
	public void setVision() {
		this.upDateTasks();
		this.taskListFrame.setVisible(true);
	}
	
	//����������
	private void sortByName()
	{
		this.taskList.sortByName();
		this.upDateTasks();			
	}
	//���������
	private void sortByKind()
	{
		this.taskList.sortByKind();
		this.upDateTasks();
	}
	//������������
	private void sortByIsFinished()
	{
		this.taskList.sortByIsFinished();
		this.upDateTasks();
	}
	
	//�޸������嵥��Ϣ����
	private void changeInfoButtonPerforming()
	{
		if(this.okButton == null)
		{
			this.okButton = new JButton("ȷ���޸�");
			this.okButton.addActionListener(this);
		}
			
		if(this.taskListInfoChangePanel == null)
		{
			System.out.println("this");
			this.taskListInfoChangePanel = new JPanel();
		    this.taskListInfoChangePanel.setLayout(new BoxLayout(this.taskListInfoChangePanel, BoxLayout.Y_AXIS));
		    this.taskListInfoChangePanel.setPreferredSize(new Dimension(1000, 200));
		    
		    //��������
		    JPanel nameTextPanel = new JPanel();
		    nameTextPanel.setPreferredSize(new Dimension(1000, 60));
		    nameTextPanel.setLayout(new BoxLayout(nameTextPanel, BoxLayout.X_AXIS));
		    this.nameTextField = new JTextField(this.taskList.getName());
		    nameTextPanel.add(new JLabel("�����������嵥���ƣ�"));
		    nameTextPanel.add(this.nameTextField);
		    
		    //�������
		    JPanel kindTextPanel = new JPanel();
		    kindTextPanel.setPreferredSize(new Dimension(1000, 60));
		    kindTextPanel.setLayout(new BoxLayout(kindTextPanel, BoxLayout.X_AXIS));
		    this.kindTextField = new JTextField(this.taskList.getKind());
		    kindTextPanel.add(new JLabel("�����������嵥���"));
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
		//�޸Ľ���
		if(source == this.changeInfoButton)
		{
			this.changeInfoButtonPerforming();
		}
		//ȷ�Ͻ���
		else if(source == this.okButton)
		{
			this.mainPanel.removeAll();
			this.taskList.setName(this.nameTextField.getText());
			this.taskList.setKind(this.kindTextField.getText());
			//���½���
			this.upDateListInfoPanel();
		}
		//�½���ť
		else if(source == this.newButton)
		{
			new TaskUI(this, this.taskList);
			this.taskListFrame.setVisible(false);
		}
		//����������
		else if(source == this.sortByNameButton)
		{
			this.sortByName();
		}
		//����������
		else if(source == this.sortByKindButton)
		{
			this.sortByKind();
		}
		//������������
		else if(source == this.sortByIsFinishedButton)
		{
			this.sortByIsFinished();
		}
		//ɾ��
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
		//listUI����
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
