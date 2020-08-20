package homework;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mysql.cj.protocol.ExportControlled;






public class PersonalAssistantFile {
	//driver
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	//address
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC&characterEncoding=utf-8";
    //密码和用户名
    static final String USER = "root";
    static final String PASS = "6778";
    //数据库名字
    private String dataBaseName = "assistant";
    private Connection connection;
    private Statement assistantFile;
    //任务清单表名
    private final String taskListTableName = "tasklist";
    //
    private final String tempTaskTableName = "temptask";
    //
    private final String repeatTaskTableName = "repeattask";
    //
    private final String longTermTaskTableName = "longtermtask";
    //
    private PersonalAssistant assistant;
    
    public PersonalAssistantFile(PersonalAssistant assistant)
    {
    	this.assistant = assistant;
    	this.connect();
    	//查看并新建数据库
        this.initDateBase();
        //获取数据
        this.getTaskLists();
        //清空
        this.clear();
        this.close();
    }
    
    private void connect()
    {
    	this.connection = null;
    	this.assistantFile = null;
    	try {
    		// 注册 JDBC 驱动
    		Class.forName(JDBC_DRIVER);
    		// 打开链接
            this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
            //实例化对象
            this.assistantFile = this.connection.createStatement();
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
		}
    }
    
    private void initDateBase()
    {
    	try{
    		ResultSet dataBaseNames = this.assistantFile.executeQuery("SHOW DATABASES");
	        // 执行查询
	        boolean isDataBaseExist = false;
	        while(dataBaseNames.next())
	        {
	        	if(dataBaseNames.getString("DATABASE").equals(this.dataBaseName))
	        	{
	        		isDataBaseExist = true;
	        	}
	        }
	        dataBaseNames.close();
	        
	        if(!isDataBaseExist)
	        {
	        	//新建数据库
	        	this.assistantFile.executeUpdate("CREATE DATABASE " + this.dataBaseName);
	        	//使用当前数据库
	        	this.assistantFile.executeQuery("USE " + this.dataBaseName);
	        	//建表
	        	this.assistantFile.executeUpdate("CREATE TABLE " + this.taskListTableName + "(id INT(1) not null AUTO_INCREMENT, name CHAR(20), kind CHAR(20), tasksindex CHAR(100), PRIMARY KEY(id))");
	        	this.assistantFile.executeUpdate("CREATE TABLE " + this.tempTaskTableName + "(id INT(1) not null AUTO_INCREMENT, name CHAR(20), kind CHAR(20), body CHAR(100), deadlinedate DATETIME(1), isfinished TINYINT(1), PRIMARY KEY(id))");
	        	this.assistantFile.executeUpdate("CREATE TABLE " + this.repeatTaskTableName + "(id INT(1) not null AUTO_INCREMENT, name CHAR(20), kind CHAR(20), body CHAR(100), begindate DATETIME(1), repeatimes INT(1), isfinished TINYINT(1), year INT(1), month INT(1), day INT(1), PRIMARY KEY(id))");
	        	this.assistantFile.executeUpdate("CREATE TABLE " + this.longTermTaskTableName + "(id INT(1) not null AUTO_INCREMENT, name CHAR(20), kind CHAR(20), body CHAR(100), deadlinedate DATETIME(1), isfinished TINYINT(1), childindex CHAR(10), PRIMARY KEY(id))");
	        }
	        else 
	        {
				this.assistantFile.executeQuery("USE " + this.dataBaseName);
			}
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
		}
    }
    
    public void getTaskLists()
    {
    	try {
    		//任务清单
    		int n = 1;
    		while(true)
    		{
	    		ResultSet rs = this.assistantFile.executeQuery("SELECT * FROM " + this.taskListTableName + " WHERE id=" + n);
	    		if(rs.next())
	    		{
	    			String name = rs.getString("name");
	    			String kind = rs.getString("kind");
	    			String index = rs.getString("tasksindex");
	    			
	    			String [] s = index.split(",");
	    			TaskList temp = new TaskList(name, kind);
	    			this.assistant.addTaskList(temp);
	    			for(int i = 0; i < s.length; i++)
	    			{
	    				if(s[i].equals(""))
	    					continue;
	    				else if(s[i].charAt(0) == 't')
	    				{
	    					temp.addTask(this.getTempTask((int)s[i].charAt(1)));
	    				}
	    				else if(s[i].charAt(0) == 'r')
	    				{
	    					temp.addTask(this.getRepeaTask((int)s[i].charAt(1)));
	    				}
	    				else if(s[i].charAt(0) == 'l')
	    				{
	    					temp.addTask(this.getLongTermTask(s[i].charAt(1)));
	    				}
	    			}
	    			n++;
	    			rs.close();
	    		}
	    		else 
	    		{
					break;
				}
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    private void clear()
    {
    	try {
    		this.assistantFile.executeUpdate("TRUNCATE TABLE " + this.taskListTableName);
    		this.assistantFile.executeUpdate("TRUNCATE TABLE " + this.tempTaskTableName);
    		this.assistantFile.executeUpdate("TRUNCATE TABLE " + this.repeatTaskTableName);
    		this.assistantFile.executeUpdate("TRUNCATE TABLE " + this.longTermTaskTableName);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    public void addTaskLists()
    {
    	List<TaskList> temp = this.assistant.getTaskLists();
    	for(int i = 0; i < temp.size(); i++)
    	{
    		List<Task> tempTasks = temp.get(i).getTasks();
    		String indexs = new String();
    		for(int j = 0; j < tempTasks.size(); j++)
    		{
    			if(tempTasks.get(j).getKind().equals("Temp"))
    			{
    				indexs += ("t" + (char)this.addTempTask(tempTasks.get(j)) + ",");
    			}
    			else if(tempTasks.get(j).getKind().equals("Repeat"))
    			{
    				indexs += ("r" + (char)this.addRepeatTask(tempTasks.get(j)) + ",");
    			}
    			else if(tempTasks.get(j).getKind().equals("LongTerm"))
    			{
    				indexs += ("l" + (char)this.addLongTerm(tempTasks.get(j)) + ",");
    			}
    		}
    		try 
    		{
    			this.assistantFile.executeUpdate("INSERT INTO tasklist VALUES(null, '" + 
			    		temp.get(i).getName() + "', '" + 
			    		temp.get(i).getKind() + "', '" + 
			    		indexs + "');");
    		} 
    		catch (Exception e) 
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
    private int addTempTask(Task temp)
    {
    	SimpleDateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd");
    	int index = -1;
    	try {
				this.assistantFile.executeUpdate("INSERT INTO temptask VALUES(null, '" + 
			    	temp.getName() + "', '" + 
			    	temp.getKind() + "', '" + 
					temp.getBody() + "', '" + 
			    	dFormat.format(temp.getDeadLineDate()) + "', " + 
			    	temp.isFinished + ");");
			ResultSet rs = this.assistantFile.executeQuery("SELECT LAST_INSERT_ID();");
			if(rs.next())
				index = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return index;
    }
    
    private int addRepeatTask(Task temp)
    {
    	SimpleDateFormat dFormat  = new SimpleDateFormat("YYYY-MM-dd");
    	int index = -1;
    	try {
			this.assistantFile.executeUpdate("INSERT INTO repeattask VALUES(null, '" + 
				    	temp.getName() + "', '" + 
						temp.getKind() + "', '" + 
				    	temp.getBody() + "', '" + 
						dFormat.format(temp.getBeginDate()) + "', " + 
						temp.getRepeatTimes() + ", " + 
				    	temp.getRangeYear() + ", " +
						temp.getRangeMonth() + ", " +
				    	temp.getRangeDay() + ", " +
				    	temp.isFinished + ");"
				    	);
			ResultSet rs = this.assistantFile.executeQuery("SELECT LAST_INSERT_ID();");
			if(rs.next())
				index = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return index;
    }
    
    private int addLongTerm(Task temp)
    {
    	String indexs = new String();
    	List<Task> tasks = temp.getTasks();
    	SimpleDateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd");
    	int index = -1;
    	try {
	    		for(int i = 0; i < tasks.size(); i++)
	    		{
					indexs += (char)this.addLongTerm(tasks.get(i));
	    		}
	    		this.assistantFile.executeUpdate("INSERT INTO longtermtask VALUES(null, '" + 
			    		temp.getName() + "', '" + 
			    		temp.getKind() + "', '" + 
			    		temp.getBody() + "', '" +
						dFormat.format(temp.getDeadLineDate()) + "', " +
						temp.isFinished + ", '" +
			    		indexs + "');");
	    		
				ResultSet rs = this.assistantFile.executeQuery("SELECT LAST_INSERT_ID();");
				if(rs.next())
					index = rs.getInt(1);
				rs.close();
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return index;
    }
    
    
    private Task getTempTask(int n)
    {
    	Task temp = null;
    	try {
    		ResultSet rs = this.assistantFile.executeQuery("SELECT * FROM " + this.tempTaskTableName + " WHERE id=" + n + ";");
    		if(rs.next())
    		{
	        	String name = rs.getString("name");
	        	String body = rs.getString("body");
	        	Date deadLineDate = rs.getDate("deadlinedate");
	        	boolean isFinished = rs.getBoolean("isfinished");
	         	temp = new TempTask(name, body, deadLineDate, isFinished);
    		}
         	rs.close();
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return temp;
    }
    
    private Task getRepeaTask(int n)
    {
    	Task temp = null;
    	try 
    	{
    		ResultSet rs = this.assistantFile.executeQuery("SELECT * FROM " + this.repeatTaskTableName + " WHERE id=" + n + ";");
        	if(rs.next())
        	{
        		String name = rs.getString("name");
	        	String body = rs.getString("body");
	        	Date beginDate = rs.getDate("begindate");
	        	int repeaTimes = rs.getInt("repeatimes");
	        	int year = rs.getInt("year");
	        	int month = rs.getInt("month");
	        	int day = rs.getInt("day");
	        	boolean isFinished = rs.getBoolean("isfinished");
	         	temp = new RepeatTask(name, body, beginDate, repeaTimes, year, month, day, isFinished);
	         	rs.close();
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return temp;
    }
    
    private Task getLongTermTask(int n)
    {
    	Task temp = null;
    	try 
    	{
    		ResultSet rs = this.assistantFile.executeQuery("SELECT * FROM " + this.longTermTaskTableName + " WHERE id=" + n);
        	
    		if(rs.next())
    		{
    			String name = rs.getString("name");
	        	Date deadLineDate = rs.getDate("deadlinedate");
	        	boolean isFinished = rs.getBoolean("isfinished");
	        	String indexs = rs.getString("childindex");
	         	temp = new LongTermTask(name, deadLineDate, isFinished);
	         	rs.close();
	         	for(int i = 0; i < indexs.length(); i++)
	         	{
	         		temp.addTask(this.getLongTermTask(indexs.charAt(i)));
	         	}
    		}
		} catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return temp;
    }
    
    public void export()
    {
    	this.dataBaseName = "move";
    	this.connect();
    	this.initDateBase();
    	this.addTaskLists();
    	this.dataBaseName = "assistant";
    	this.close();
    }
    
    public void inport()
    {
    	this.dataBaseName = "move";
    	this.connect();
    	this.initDateBase();
    	this.getTaskLists();
    	this.dataBaseName = "assistant";
    	this.close();
    }
    
    public void storeInfo()
    {
    	this.dataBaseName = "assistant";
    	this.connect();
    	this.initDateBase();
    	this.addTaskLists();
    	this.close();
    }
    public void close()
    {
    	try 
		{
			this.assistantFile.close();
    		this.connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}

