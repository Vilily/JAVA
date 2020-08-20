package homework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;








public class PersonalAssistantUI implements ActionListener, MouseListener, WindowListener{
	//任务清单列表
	private List<JPanel> listTasksPanels;
	//任务清单删除按钮
	private List<JButton> listTasksButtons;
	//任务清单Panel
	private JPanel listMainPanel;
	//下一页按钮
	private JButton nextPageButton;
	//上一页按钮
	private JButton lastPageButton;
	//新建任务按钮
	private JButton newButton;
	//按钮Panel
	private JPanel buttonPanel;
	//当前页数
	private int nowPage;
	//当前数据条数
	private int nowPieces;
	//主界面
	private JFrame assistantFrame;
	//存储信息
	private PersonalAssistant assistant;
	//任务清单条数
	private final int LISTS_FOR_PAGE = 10;
	//按名称排序按钮
	private JButton nameSortButton;
	//按类别排序按钮
	private JButton kindSortButton;
	//查找输入
	private JTextField findField;
	//查找按钮
	private JButton findButton;
	//导出按钮
	private JButton exportButton;
	//导入按钮
	private JButton importButton;
	
	
	public PersonalAssistantUI(PersonalAssistant assistant)
	{
		//创建界面
		this.assistantFrame = new JFrame("Personal Assistant");
		this.assistantFrame.setSize(1000, 800);
		this.assistantFrame.addWindowListener(this);
		//连接数据
		this.assistant = assistant;
		//初始化主列表JPanel，设置主页面布局
		this.listMainPanel = new JPanel();
		this.listMainPanel.setLayout(new BoxLayout(this.listMainPanel, BoxLayout.Y_AXIS));
		this.assistantFrame.add(this.listMainPanel);
		//创建按钮
		createButton();
		//加上按钮JPanel
		this.assistantFrame.add(this.buttonPanel, BorderLayout.SOUTH);
		//设置当前页码
		this.nowPage = 1;
		//初始化任务清单列表界面
		this.tasksListPanelInit();
		//窗口可见
		this.assistantFrame.setVisible(true);
		this.assistantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//任务清单列表初始化
	private void tasksListPanelInit()
	{
		//初始化任务清单列表
		this.listTasksPanels = new ArrayList<JPanel>();
		//初始化按钮
		this.listTasksButtons = new ArrayList<JButton>();
		//标题
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(1000,200));
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(new Color(783223));
		JLabel title = new JLabel("个人助理");
		title.setFont(new Font("宋体", Font.BOLD, 20));
		titlePanel.add(title, BorderLayout.CENTER);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
		
		JPanel movePanel = new JPanel();
		movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.X_AXIS));
		this.exportButton = new JButton("导出");
		this.importButton = new JButton("导入");
		this.exportButton.addActionListener(this);
		this.importButton.addActionListener(this);
		movePanel.add(this.exportButton);
		movePanel.add(this.importButton);
		tempPanel.add(movePanel);
		
		JPanel findPanel = new JPanel();
		findPanel.setLayout(new BorderLayout());
		findPanel.add(new JLabel("请输入查找任务名称"), BorderLayout.WEST);
		this.findField = new JTextField();
		this.findButton = new JButton("查找");
		this.findButton.addActionListener(this);
		findPanel.add(this.findField, BorderLayout.CENTER);
		findPanel.add(this.findButton, BorderLayout.EAST);
		tempPanel.add(findPanel);
		
		titlePanel.add(tempPanel, BorderLayout.SOUTH);
		
		
		this.listMainPanel.add(titlePanel);
		
		for(int i = 0; i < 10; i++)
		{
			JPanel temPanel = new JPanel();
			temPanel.setBorder(BorderFactory.createEtchedBorder());
			temPanel.addMouseListener(this);
			temPanel.setLayout(new BorderLayout());
			temPanel.setPreferredSize(new Dimension(1000, 50));
			//加入清单名字
			JLabel tempLabel = new JLabel();
			tempLabel.setPreferredSize(new Dimension(400, 50));
			tempLabel.setHorizontalAlignment(JLabel.CENTER);
			temPanel.add(tempLabel, BorderLayout.WEST);
			//加入清单类别
			JLabel tempLabel2 = new JLabel();
			tempLabel2.setPreferredSize(new Dimension(400, 50));
			tempLabel2.setHorizontalAlignment(JLabel.CENTER);
			temPanel.add(tempLabel2, BorderLayout.CENTER);
			//加入删除按钮
			JButton tempButton = new JButton("删除");
			tempButton.setPreferredSize(new Dimension(200, 50));
			tempButton.addActionListener(this);
			this.listTasksButtons.add(tempButton);
			temPanel.add(tempButton, BorderLayout.EAST);
			
			this.listTasksPanels.add(temPanel);
			this.listMainPanel.add(temPanel);
		}
		this.updateListTasksPanel();
		
	}
	//更新任务清单列表
	public void updateListTasksPanel()
	{
		List<TaskList> temp = this.assistant.getTaskList(this.nowPage);
		this.nowPieces = temp.size();
		for(int i = 0; i < 10; i++)
		{
			if(i < temp.size())
			{
				((JLabel)this.listTasksPanels.get(i).getComponent(0)).setText(temp.get(i).getName());
				((JLabel)this.listTasksPanels.get(i).getComponent(1)).setText(temp.get(i).getKind());
				this.listTasksPanels.get(i).getComponent(0).setVisible(true);
				this.listTasksPanels.get(i).getComponent(1).setVisible(true);
				this.listTasksPanels.get(i).getComponent(2).setVisible(true);
			}
			else 
			{
				this.listTasksPanels.get(i).getComponent(0).setVisible(false);
				this.listTasksPanels.get(i).getComponent(1).setVisible(false);
				this.listTasksPanels.get(i).getComponent(2).setVisible(false);
			}
		}
		this.assistantFrame.setVisible(true);
		
	}
	
	private void createButton()
	{
		//设置buttonPanel为X排列
		this.buttonPanel = new JPanel();
		this.buttonPanel.setPreferredSize(new Dimension(1000, 100));
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.X_AXIS));
		this.nextPageButton = new JButton("下一页");
		this.nextPageButton.addActionListener(this);
		this.lastPageButton = new JButton("上一页");
		this.lastPageButton.addActionListener(this);
		this.newButton = new JButton("新建任务清单");
		this.newButton.addActionListener(this);
		this.nameSortButton = new JButton("按名称排序");
		this.nameSortButton.addActionListener(this);
		this.kindSortButton = new JButton("按类别排序");
		this.kindSortButton.addActionListener(this);
		
		
		//加入nextPageButton
		this.buttonPanel.add(this.nextPageButton);
		this.buttonPanel.add(this.lastPageButton);
		this.buttonPanel.add(this.newButton);
		this.buttonPanel.add(this.nameSortButton);
		this.buttonPanel.add(this.kindSortButton);
	}
	
	private void nextPageButtonPressed()
	{
		this.nowPage++;
		this.updateListTasksPanel();
	}
	
	private void lastPageButtonPressed()
	{
		if(this.nowPage > 1)
		{
			this.nowPage--;
			updateListTasksPanel();
		}
	}
	//进入下一界面
	private void callTaskListUi(int index) {
		
		int i = (this.nowPage-1)*LISTS_FOR_PAGE + index;
		TaskList taskL = this.assistant.getTaskListByIndex(i);
		TaskListUI ui = new TaskListUI(taskL, i, this, this.assistant);
		this.assistantFrame.setVisible(false);
	}
	
	//更新任务清单并显示窗口
	public void setVision() {
		this.updateListTasksPanel();
		this.assistantFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.nextPageButton)
			this.nextPageButtonPressed();
		else if(source == this.lastPageButton)
			this.lastPageButtonPressed();
		//新建任务清单
		else if(source == this.newButton)
		{
			new TaskListUI(this.assistant, this);
			this.assistantFrame.setVisible(false);
		}
		//按名称排序
		else if(source == this.nameSortButton)
		{
			this.assistant.sortBy(0);
			this.updateListTasksPanel();
		}
		//按种类排序
		else if(source == this.kindSortButton)
		{
			this.assistant.sortBy(1);
			this.updateListTasksPanel();
		}
		//查找
		else if(source == this.findButton)
		{
			new tempUI(this.assistant, this, this.findField.getText());
		}
		//导出
		else if(source == this.exportButton)
		{
			this.assistant.export();
		}
		//导入
		else if(source == this.importButton)
		{
			this.assistant.inport();
			this.updateListTasksPanel();
		}
		else 
		{
			for(int i = 0; i < this.listTasksButtons.size(); i++)
			{
				if(source.equals(this.listTasksButtons.get(i)))
				{
					this.assistant.deleteTaskListByIndex(i + LISTS_FOR_PAGE*(this.nowPage - 1));
					this.updateListTasksPanel();
					this.assistantFrame.setVisible(true);
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		for(int i = 0; i < this.listTasksPanels.size(); i++)
		{
			if(this.listTasksPanels.get(i).equals(source) && this.nowPieces > i)
			{
				this.callTaskListUi(i);
				break;
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

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.assistant.storeInfo();
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

}
