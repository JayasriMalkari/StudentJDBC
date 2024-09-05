package SD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentAdmissionServlet")
public class StudentAdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Database credentials
	String DB_URL = "jdbc:mysql://localhost:3306/student_admission_db?useSSL=false";
	String DB_USER = "root";
	String DB_PASSWORD = "Root"; // Replace with your MySQL password
	String sql = "INSERT INTO students (name, dob, gender, email, phone, address, course, marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve form data Or Read from data
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String course = request.getParameter("course");
		String marks = request.getParameter("marks");

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Connect to the database and insert data
		try {
			// Load the MySQL JDBC driver s1
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection s2
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// SQL INSERT statement
			//String sql = "INSERT INTO students (name, dob, gender, email, phone, address, course, marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";



			// Write response

			out.println("<html><body>");
			out.println("<h2>Student Admission Confirmation</h2>");
			out.println("<p>Thank you, " + name + "! Your application has been submitted successfully.</p>");
			out.println("<h3>Submitted Details:</h3>");
			out.println("<p><strong>Date of Birth:</strong> " + dob + "</p>");
			out.println("<p><strong>Gender:</strong> " + gender + "</p>");
			out.println("<p><strong>Email:</strong> " + email + "</p>");
			out.println("<p><strong>Phone Number:</strong> " + phone + "</p>");
			out.println("<p><strong>Address:</strong> " + address + "</p>");
			out.println("<p><strong>Course Applied For:</strong> " + course + "</p>");
			out.println("<p><strong>Previous Qualification Marks:</strong> " + marks + "</p>");
			out.println("</body></html>");

			// Create a PreparedStatement s3
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, dob);
			stmt.setString(3, gender);
			stmt.setString(4, email);
			stmt.setString(5, phone);
			stmt.setString(6, address);
			stmt.setString(7, course);
			stmt.setString(8, marks);
			// Execute the statement
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				out.println("<h2>Student Admission Confirmation</h2>");
				out.println("<p>Thank you, " + name + "! Your application has been submitted successfully.</p>");
			} else {
				out.println("<h2>Error</h2>");
				out.println("<p>There was an issue submitting your application. Please try again later.</p>");
			}



			// Close the connection
			stmt.close();
			conn.close();

		} catch ( Exception e) {
			e.printStackTrace();
			out.println("<h2>Error</h2>");
			out.println("<p>There was an error processing your request: " + e.getMessage() + "</p>");
		}

		out.close();
	}
}
