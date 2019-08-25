package Main;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class GetFileFromPageUtil {

    public static StringBuilder getFileFromPageUtil (HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        StringBuilder sb = new StringBuilder();
        String s = "";
        try {
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                BufferedReader br = new BufferedReader(new InputStreamReader(item.getInputStream()));

                if (!item.isFormField()) {

                    while ((s = br.readLine()) != null) {
                        sb.append(s);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("херня");
        }
        return sb;
    }

}
