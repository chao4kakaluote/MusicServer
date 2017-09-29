package db;
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;  
public class DBHelper 
{
	public static final String url = "jdbc:mysql://127.0.0.1/user";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
    public Statement st=null;
  
    public DBHelper() 
    {  
        try 
        {  
            Class.forName(name);//指定连接类型  
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接  
          //  pst = conn.prepareStatement(sql);//准备执行语句  
            st=(Statement) conn.createStatement();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public String register(String name,String pwd) 
    {
    	String sql1="select count(*) from user where userName= "+"\'"+name+"\'";
    	//先判断是否注册过了。
    	try {
			ResultSet rst=st.executeQuery(sql1);
			if(rst.next())
			{
				if(rst.getInt(1)>0)
					return "该账号已注册"+rst.getInt(1)+"次";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	String sql="insert into user(userName,userPwd) values("+"\'"+name+"\'"+","+"\'"+pwd+"\'"+")";
    	try 
    	{
			st.execute(sql);
			return "注册成功";
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	return "注册失败";
    }
    
    public String Login(String name,String pwd)
    {
    	String sql="select * from user where userName="+"\'"+name+"\'";
    	try {
			ResultSet rst=st.executeQuery(sql);
			
			while(rst.next())
			{
				String password=rst.getString(3);
				if(pwd.equals(password))
					return "登陆成功";
				else
					return "密码不正确";
			}
			return "该账号不存在";
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return "登陆失败";
    }
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
