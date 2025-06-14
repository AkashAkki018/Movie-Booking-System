package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidUser
 */
@WebServlet("/ValidUser")
public class ValidUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		String name=request.getParameter("user");
		String pwd=request.getParameter("password");
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		out.println("Driver Loaded");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieBooking","root","tiger");
		out.println("connection established");

		Statement st=con.createStatement();

		ResultSet rs=st.executeQuery("select * from users where username='"+name+"' and password='"+pwd+"'");

		if(rs.next())
		{
		response.sendRedirect("Admin1.html");
		}
		else
		{
		out.println("Invalid UserName or Psssword");
		}
		con.close();
		}
		catch(Exception e)
		{
		out.println(e);
		}
	}

}
