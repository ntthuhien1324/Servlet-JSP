package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import Service.UploadFile;
import practice.sv.bai1.Student;
import sv.practice.mysql.JDBCStatement;
import Persistence.InsertFile;

@WebServlet(name = "bai8")
public class bai8 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ArrayList<Student> students= (ArrayList<Student>) JDBCStatement.readData();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/print.jsp");
        req.setAttribute("listStudent", students);
        dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        try {
        	List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));
            String dirUrl = req.getServletContext().getRealPath("") + "files";
            String fileName = new String();
            fileName = UploadFile.uploadFile(fileItems, dirUrl);
            if (fileName == null) {
                out.println("Upload Failed!");
            } else {
                out.println("Upload Succeed");
            }
            if (fileName.endsWith(".csv")) {
                InsertFile.main(fileName);
                out.println("<br>");
                out.println("Insert Done!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/insert.jsp");
                dispatcher.forward(req, resp);
                //resp.sendRedirect("/insert.jsp");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
	}
}
