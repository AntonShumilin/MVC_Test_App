package View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Main.GsonBuilderUtil.getGsonBuilder;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class UtilMethods {

    public static <T> void  sendListOfJson  (List<T> list, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");
        Integer offsetInt = 0;
        Integer limitInt = 0;
        List<T> sendList = new LinkedList<T>();

        if (limit == null) limitInt = 10;
        else {
            try {
                limitInt = Integer.parseInt(limit);
            } catch (Exception e) {
                limitInt =10;
            }
        }

        if (offset == null) offsetInt = 1;
        else {
            try {
                offsetInt = Integer.parseInt(offset);
                if (offsetInt > list.size()) throw new Exception();
            } catch (Exception e) {
                offsetInt = 1;
            }
        }

        if ((offsetInt + limitInt) > list.size()) limitInt = list.size() - offsetInt + 1;
        if (offsetInt < 1) offsetInt = 1;
        try {
            for (int i = offsetInt - 1; i < offsetInt - 1 + limitInt; i++) {
                sendList.add(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        for (T t : sendList) {
            response.getWriter().println(getGsonBuilderExpose().toJson(t));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return;


    }

}
