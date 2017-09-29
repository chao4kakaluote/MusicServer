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
            Class.forName(name);//ָ����������  
            conn = (Connection) DriverManager.getConnection(url, user, password);//��ȡ����  
          //  pst = conn.prepareStatement(sql);//׼��ִ�����  
            st=(Statement) conn.createStatement();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public String register(String name,String pwd) 
    {
    	String sql1="select count(*) from user where userName= "+"\'"+name+"\'";
    	//���ж��Ƿ�ע����ˡ�
    	try {
			ResultSet rst=st.executeQuery(sql1);
			if(rst.next())
			{
				if(rst.getInt(1)>0)
					return "���˺���ע��"+rst.getInt(1)+"��";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	String sql="insert into user(userName,userPwd) values("+"\'"+name+"\'"+","+"\'"+pwd+"\'"+")";
    	try 
    	{
			st.execute(sql);
			return "ע��ɹ�";
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	return "ע��ʧ��";
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
					return "��½�ɹ�";
				else
					return "���벻��ȷ";
			}
			return "���˺Ų�����";
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return "��½ʧ��";
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
