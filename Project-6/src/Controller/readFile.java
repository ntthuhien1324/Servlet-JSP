package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import Persistence.InsertFile;
import Service.UploadFile;


public class readFile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
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
                //String fileUrl = dirUrl + File.separator + fileName;
                InsertFile.main(fileName);
                out.println("<br>");
                out.println("Insert Done!");
            }
            out.println("<html>");
    	    out.println("<head><title>Project-6</title></head>");           	    
    	    out.println("<body>");
    	    out.println("<form action=\"print\" method=\"post\" enctype=\"multipart/form-data\">");
    	    out.println("<input type=\"submit\" value=\"Print\" name=\"btnPrint\" />");
    	    out.println("</form>");
    	    out.println("</body>");
    	    out.println("<html>");   	    
    	    out.close();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
	}

}
