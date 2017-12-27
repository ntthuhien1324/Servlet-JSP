package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.sv.bai1.Student;
import sv.practice.mysql.JDBCStatement;


public class PrintFile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ArrayList<Student> students=  new ArrayList<>();
        students = (ArrayList<Student>) JDBCStatement.readData();
        for (Student student : students) {
            out.println(student.toString());
            out.println("<br>");
        }
	}
}
