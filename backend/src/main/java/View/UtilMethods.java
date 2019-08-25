package View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static Main.GsonBuilderUtil.getGsonBuilder;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class UtilMethods {

    public static <T> void  sendListOfJson  (List<T> list, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");
        Integer offsetInt = 0;
        Integer limitInt = 0;
        List<T> sendList = new LinkedList<T>();

        if (offset == null && limit == null) {
            offsetInt = 1;
            limitInt = list.size();
        }

        if (offset != null && limit == null) {
            try {
                offsetInt = Integer.parseInt(offset);
                if (offsetInt > list.size()) throw new Exception();
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            limitInt = list.size();
        }

        if (offset == null && limit != null) {
            offsetInt = 1;
            try {
                limitInt = Integer.parseInt(limit);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }


        if (offset != null && limit != null) {
            try {
                offsetInt = Integer.parseInt(offset);
                if (offsetInt > list.size()) throw new Exception();
                limitInt = Integer.parseInt(limit);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        if ((offsetInt + limitInt) > list.size()) limitInt = list.size() - offsetInt + 1;

        try {
            for (int i = offsetInt - 1; i < offsetInt - 1 + limitInt; i++) {
                sendList.add(list.get(i));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        response.setContentType("application/json");
        for (T t : sendList) {
            response.getWriter().println(getGsonBuilderExpose().toJson(t));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return;


    }

}
