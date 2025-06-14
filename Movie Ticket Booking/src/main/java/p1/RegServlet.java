package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
		PrintWriter out=response.getWriter();
		String name=request.getParameter("user");
		String pwd=request.getParameter("password");	
		String number=request.getParameter("number");
		String mail=request.getParameter("mail");
	

		Class.forName("com.mysql.cj.jdbc.Driver");

		out.println("Driver Loaded");

		

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieBooking","root","tiger");

		out.println("Connection Established");

		

		Statement st=con.createStatement();

		out.println("statement created");

		

		int i=st.executeUpdate("insert into users values('"+name+"','"+pwd+"',"+number+",'"+mail+"')");

		if (i>0)

			response.sendRedirect("Admin1.html");

		else

			out.println("User not Registered");

		

		con.close();

		

	}

	

	catch(Exception e) {

		PrintWriter out=response.getWriter();

		out.println("e");

		

	}
	}

}
