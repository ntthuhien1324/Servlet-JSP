package practice.sv.bai5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import practice.sv.bai1.ReadFile;
import practice.sv.bai1.Student;


@MultipartConfig
public class UploadController extends HttpServlet{
	
	public UploadController() {}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        PrintWriter out = resp.getWriter();

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    if (!fileName.equals("")) {
//                        String dirUrl = req.getServletContext().getRealPath("") + "files";
//                        File dir = new File(dirUrl);
//
//                        if (!dir.exists()) {
//                            dir.mkdir();
//                        }

//                        String fileUrl = dirUrl + File.separator + fileName;
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
                            for (Student student : students) {
                                out.println("<br>");
                                out.println(student.toString());
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
	
	}

}
