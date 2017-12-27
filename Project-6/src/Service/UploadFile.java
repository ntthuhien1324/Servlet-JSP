package Service;

import java.io.File;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;



public class UploadFile {
	public static String uploadFile(List<FileItem> fileItems, String dirUrl) {
		for (FileItem fileItem : fileItems) {
            if (!fileItem.isFormField()) {
                String fileName = fileItem.getName();
                if (!fileName.equals("")) {
                    File dir = new File(dirUrl);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    //String fileImg = dirUrl + File.separator + fileName;
                    File file = new File(fileName);

                    try {
                        fileItem.write(file);
                        System.out.println("Directory: \n" + fileName);
                        return fileName;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
		return null;
	}
}
