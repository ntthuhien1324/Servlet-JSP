import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import practice.sv.bai1.ReadFile;
import practice.sv.bai1.Student;

@WebServlet(name = "bai7")
public class bai7 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    if (!fileName.equals("")) {
                        File file = new File(fileName);

                        try {
                            fileItem.write(file);
                            System.out.println("Upload Success!");
                            System.out.println("Directory: \n" + fileName);
                        } catch (Exception e) {
                            System.out.println("Fail!");
                            e.printStackTrace();
                        }
                        
                        if (fileName.endsWith(".csv")) {
                            List<Student> students = new ArrayList<>();
                            students = ReadFile.listStudent(fileName);
                            //ReadFile.printData(students);
                            
                            RequestDispatcher dispatcher = req.getRequestDispatcher("/print.jsp");
                            req.setAttribute("listStudent", students);
                            dispatcher.forward(req, resp);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
	}
}
