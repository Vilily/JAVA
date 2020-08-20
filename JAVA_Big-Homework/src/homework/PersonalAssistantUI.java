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
	//�����嵥�б�
	private List<JPanel> listTasksPanels;
	//�����嵥ɾ����ť
	private List<JButton> listTasksButtons;
	//�����嵥Panel
	private JPanel listMainPanel;
	//��һҳ��ť
	private JButton nextPageButton;
	//��һҳ��ť
	private JButton lastPageButton;
	//�½�����ť
	private JButton newButton;
	//��ťPanel
	private JPanel buttonPanel;
	//��ǰҳ��
	private int nowPage;
	//��ǰ��������
	private int nowPieces;
	//������
	private JFrame assistantFrame;
	//�洢��Ϣ
	private PersonalAssistant assistant;
	//�����嵥����
	private final int LISTS_FOR_PAGE = 10;
	//����������ť
	private JButton nameSortButton;
	//���������ť
	private JButton kindSortButton;
	//��������
	private JTextField findField;
	//���Ұ�ť
	private JButton findButton;
	//������ť
	private JButton exportButton;
	//���밴ť
	private JButton importButton;
	
	
	public PersonalAssistantUI(PersonalAssistant assistant)
	{
		//��������
		this.assistantFrame = new JFrame("Personal Assistant");
		this.assistantFrame.setSize(1000, 800);
		this.assistantFrame.addWindowListener(this);
		//��������
		this.assistant = assistant;
		//��ʼ�����б�JPanel��������ҳ�沼��
		this.listMainPanel = new JPanel();
		this.listMainPanel.setLayout(new BoxLayout(this.listMainPanel, BoxLayout.Y_AXIS));
		this.assistantFrame.add(this.listMainPanel);
		//������ť
		createButton();
		//���ϰ�ťJPanel
		this.assistantFrame.add(this.buttonPanel, BorderLayout.SOUTH);
		//���õ�ǰҳ��
		this.nowPage = 1;
		//��ʼ�������嵥�б����
		this.tasksListPanelInit();
		//���ڿɼ�
		this.assistantFrame.setVisible(true);
		this.assistantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//�����嵥�б��ʼ��
	private void tasksListPanelInit()
	{
		//��ʼ�������嵥�б�
		this.listTasksPanels = new ArrayList<JPanel>();
		//��ʼ����ť
		this.listTasksButtons = new ArrayList<JButton>();
		//����
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(1000,200));
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(new Color(783223));
		JLabel title = new JLabel("��������");
		title.setFont(new Font("����", Font.BOLD, 20));
		titlePanel.add(title, BorderLayout.CENTER);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
		
		JPanel movePanel = new JPanel();
		movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.X_AXIS));
		this.exportButton = new JButton("����");
		this.importButton = new JButton("����");
		this.exportButton.addActionListener(this);
		this.importButton.addActionListener(this);
		movePanel.add(this.exportButton);
		movePanel.add(this.importButton);
		tempPanel.add(movePanel);
		
		JPanel findPanel = new JPanel();
		findPanel.setLayout(new BorderLayout());
		findPanel.add(new JLabel("�����������������"), BorderLayout.WEST);
		this.findField = new JTextField();
		this.findButton = new JButton("����");
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
			//�����嵥����
			JLabel tempLabel = new JLabel();
			tempLabel.setPreferredSize(new Dimension(400, 50));
			tempLabel.setHorizontalAlignment(JLabel.CENTER);
			temPanel.add(tempLabel, BorderLayout.WEST);
			//�����嵥���
			JLabel tempLabel2 = new JLabel();
			tempLabel2.setPreferredSize(new Dimension(400, 50));
			tempLabel2.setHorizontalAlignment(JLabel.CENTER);
			temPanel.add(tempLabel2, BorderLayout.CENTER);
			//����ɾ����ť
			JButton tempButton = new JButton("ɾ��");
			tempButton.setPreferredSize(new Dimension(200, 50));
			tempButton.addActionListener(this);
			this.listTasksButtons.add(tempButton);
			temPanel.add(tempButton, BorderLayout.EAST);
			
			this.listTasksPanels.add(temPanel);
			this.listMainPanel.add(temPanel);
		}
		this.updateListTasksPanel();
		
	}
	//���������嵥�б�
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
		//����buttonPanelΪX����
		this.buttonPanel = new JPanel();
		this.buttonPanel.setPreferredSize(new Dimension(1000, 100));
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.X_AXIS));
		this.nextPageButton = new JButton("��һҳ");
		this.nextPageButton.addActionListener(this);
		this.lastPageButton = new JButton("��һҳ");
		this.lastPageButton.addActionListener(this);
		this.newButton = new JButton("�½������嵥");
		this.newButton.addActionListener(this);
		this.nameSortButton = new JButton("����������");
		this.nameSortButton.addActionListener(this);
		this.kindSortButton = new JButton("���������");
		this.kindSortButton.addActionListener(this);
		
		
		//����nextPageButton
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
	//������һ����
	private void callTaskListUi(int index) {
		
		int i = (this.nowPage-1)*LISTS_FOR_PAGE + index;
		TaskList taskL = this.assistant.getTaskListByIndex(i);
		TaskListUI ui = new TaskListUI(taskL, i, this, this.assistant);
		this.assistantFrame.setVisible(false);
	}
	
	//���������嵥����ʾ����
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
		//�½������嵥
		else if(source == this.newButton)
		{
			new TaskListUI(this.assistant, this);
			this.assistantFrame.setVisible(false);
		}
		//����������
		else if(source == this.nameSortButton)
		{
			this.assistant.sortBy(0);
			this.updateListTasksPanel();
		}
		//����������
		else if(source == this.kindSortButton)
		{
			this.assistant.sortBy(1);
			this.updateListTasksPanel();
		}
		//����
		else if(source == this.findButton)
		{
			new tempUI(this.assistant, this, this.findField.getText());
		}
		//����
		else if(source == this.exportButton)
		{
			this.assistant.export();
		}
		//����
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
