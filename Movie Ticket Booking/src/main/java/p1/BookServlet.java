package p1;

import java.io.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private Object ResultSet;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		try
		{	
			String username=request.getParameter("user");
			String m=request.getParameter("movie");
			
			String th=request.getParameter("theatre");
			
			String d=request.getParameter("date");
			
			String t=request.getParameter("time");
		
			int ts=Integer.parseInt(request.getParameter("tickets"));
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			//out.println("Driver Loaded");

			

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieBooking","root","tiger");

//			out.println("Connection Established");

			

			Statement st=con.createStatement();

		//	out.println("statement created");

			

			int i=st.executeUpdate("insert into Tickets values('"+username+"','"+m+"','"+th+"','"+d+"','"+t+"',"+ts+")");

			if (i>0) {

				ResultSet rs=st.executeQuery("select * from Tickets where UserId='"+username+"'");
				
				

			response.setContentType("text/html");
			out.println("<html><body align='center' bgcolor='#f0f0f0'>");
			//out.println("User Id : "+rs.getString(1)+"<br>");
			out.println("<table border='1' align='center'>");
					
			out.println("<tr><th>Movie</th><th>Theatre</th><th>No.of Tickets</th><th>Date</th><th>Time</th></tr>");
					
			

			while(rs.next())
			{	
				
				
				out.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(6)+"</td><td>"+rs.getString(4)+"</td><td>"
						+rs.getString(5)+"</td></tr>");
				
			
//			out.println("<tr><td>"+"MOVIE"+"</td>");
//			out.println("<td>"+rs.getString(2)+"</td>");
//			out.println("<tr>");
//			out.println("<td>"+"THEATRE"+"</td>");
//			out.println("<td>"+rs.getString(3)+"</td>");
//			out.println("<tr>");
//			out.println("<td>"+"DATE"+"</td>");
//			out.println("<td>"+rs.getString(4)+"</td>");
//			out.println("<tr>");
//			out.println("<td>"+"TIME"+"</td>");
//			out.println("<td>"+rs.getString(5)+"</td>");
//			out.println("<tr>");
//			out.println("<td>"+"TICKETS"+"</td>");
//			out.println("<td>"+rs.getInt(6)+"</td></tr>");
		
			}
			out.println("Thanks For Booking");

			}
			else
			{

				out.println("Tickets Not Booked");

			}

			con.close();
			
			
			
			
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}

}
