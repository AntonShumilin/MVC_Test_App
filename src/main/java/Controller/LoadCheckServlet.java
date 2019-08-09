package Controller;

import DAO.CheckDAO;
import DAO.ReceiptDAO;
import DAO.UserDAO;
import Models.Check;
import Models.Item;
import Models.Receipt;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.Iterator;
import java.util.List;

public class LoadCheckServlet extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public LoadCheckServlet(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();
        Check check = gson.fromJson(sb.toString(), Check.class);
        Receipt receipt = check.document.receipt;
        User user = userDAO.getUserBySession(request.getSession().getId());
        receipt.setUserID(user.getId());
        for (Item it: receipt.items) {
            it.setReceipt(receipt);
        }


        try {
        receiptDAO.save(receipt);
            String json = gson.toJson(receipt);
            response.setContentType("application/json");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
    }
        catch (HibernateException e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("такой чек уже есть");
            response.setStatus(HttpServletResponse.SC_OK);
        }



    }




//    public void doGet(HttpServletRequest request,
//                      HttpServletResponse response) throws ServletException, IOException {
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.setPrettyPrinting().create();
//
//        Check check;
//
//        StringBuilder sb = new StringBuilder();
//        String s = "";
//        try (BufferedReader br = new BufferedReader(new FileReader("check.json"))) {
//
//            while ((s = br.readLine()) != null) {
//                sb.append(s);
//            }
//
//
//
//        } catch (Exception e){
//            System.out.println("No json file / json file not valid");
//        }
//        check = gson.fromJson(sb.toString(), Check.class);
//
//        userDAO.getUserBySession(request.getSession().getId()).addCheck(check);
//
//
//        String json = gson.toJson(check);
//        response.setContentType("application/json");
//        response.getWriter().println(json);
//        response.setStatus(HttpServletResponse.SC_OK);
//
//
//    }

}
