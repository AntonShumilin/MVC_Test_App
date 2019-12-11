package View;

import Models.DateParams;
import Models.Output;
import Models.SortedObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UtilMethods {

    public static Gson getGsonBuilderExpose () {

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();
        return gson;
    }
    public static Gson getGsonBuilderExposeDateFormat () {

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson;
    }

    public static Gson getGsonBuilder () {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson;
    }

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

        Output output = new Output();
        output.data = sendList;
        output.paginations.total = list.size() ;
        output.paginations.count = sendList.size() ;
        output.filters.limit = limitInt;
        output.filters.offset = offsetInt;
        output.filters.sort = request.getParameter("sort");
        output.filters.afterDate = request.getParameter("afterDate");
        output.filters.beforeDate = request.getParameter("beforeDate");

        response.setContentType("application/json");
        response.getWriter().println(getGsonBuilderExpose().toJson(output));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static DateParams parseDateParam (HttpServletRequest request) {
        DateParams dateParams = new DateParams();
        String afterDate = request.getParameter("afterDate");
        String beforeDate = request.getParameter("beforeDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        if (afterDate != null) {
            try {
                dateParams.afteDate = sdf.parse(afterDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (beforeDate != null) {
            try {
                dateParams.beforeDate = sdf.parse(beforeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateParams;
    }

    public static  <T extends SortedObject> void sortListOfJson (List<T> list, String sortType) {
        if (sortType==null) return;
        Comparator <T> comp;
        if (list == null | list.isEmpty()) return;

        if (sortType.equals("alphabet")) {
            comp = (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
        } else {
            comp = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    if (o1.getDateTime().before(o2.getDateTime())) return -1;
                    else return 1;
                }
            };
        }
            Collections.sort(list, comp);
       }
    }