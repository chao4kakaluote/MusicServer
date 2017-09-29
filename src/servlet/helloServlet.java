package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DBHelper;
public class helloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public helloServlet() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//		String sql = null;  
//	    DBHelper db1 = null;  
//	    ResultSet ret = null;  
//		sql = "select * from user";//SQL语句  
//	    db1 = new DBHelper(sql);//创建DBHelper对象  
//	        try 
//	           {  
//	            ret = db1.pst.executeQuery();//执行语句，得到结果集  
//	            while (ret.next()) 
//	            {  
//	                int uid = ret.getInt(1);  
//	                String name = ret.getString(2);  
//	                String pwd = ret.getString(3);  
//	                PrintWriter out=response.getWriter();
//	                out.println("<HTML><BODY>"+name+pwd+"</HTML></BODY>");
//	            }//显示数据  
//	            ret.close();  
//	            db1.close();//关闭连接  
//	        } 
//	        catch (SQLException e) 
//	        {  
//	            e.printStackTrace();  
//	        }  
	//	PrintWriter out=response.getWriter();
		DBHelper dbhelp=new DBHelper();
		String name="zk";
		String sql1="select * from user where userName= "+"\'"+name+"\'";
		System.out.println(sql1);
		try
		{
			ResultSet rst=dbhelp.st.executeQuery(sql1);
			while(rst.next())
			{
				System.out.println(rst.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		String requestType=request.getParameter("type");
        String name=request.getParameter("userName");
        String pwd=request.getParameter("password");
        
        System.out.println(name);

        PrintWriter out = response.getWriter();
       
        DBHelper db=new DBHelper();
		if(requestType.equals("regist"))
		{
			out.println(db.register(name, pwd));
		}
		else if(requestType.equals("login"))
		{
			out.println(db.Login(name, pwd));
		}
	}
}
