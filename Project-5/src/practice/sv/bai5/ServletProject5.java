package practice.sv.bai5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletProject5 extends HttpServlet {

	public ServletProject5() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		ServletOutputStream out = resp.getOutputStream();
        
	    out.println("<html>");
	    out.println("<head><title>Project-5</title></head>");
	    
	    out.println("<body>");
	    out.println("<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">");
	    out.println("File: <input type=\"file\" name=\"file\" /> <br />");
	    out.println("<input type=\"submit\" value=\"Submit\" name=\"btnUpload\" />");
	    out.println("</form>");
	    out.println("</body>");
	    out.println("<html>");
	    
	    out.close();
	}


}
