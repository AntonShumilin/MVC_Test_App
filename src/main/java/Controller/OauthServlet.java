package Controller;

import DAO.UserDAO;
import Main.Main;
import Models.Token;
import Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.squareup.okhttp.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Main.GsonBuilderUtil.getGsonBuilder;

public class OauthServlet extends HttpServlet {

    UserDAO userDAO;

    public OauthServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String code = request.getParameter("code");
        if (code == null) {
            String url = "https://accounts.google.com/o/oauth2/v2/auth" +
                    "?client_id=" + Main.config.oauth.clientId +
                    "&redirect_uri=http://localhost:8080/api/v1/admin/oauth" +
                    "&response_type=code" +
                    "&scope=email";
            response.sendRedirect(url);
        } else {

            String jwt_token = getAccessToken(code);
            Token token = getGsonBuilder().fromJson(jwt_token, Token.class);
            DecodedJWT jwt = JWT.decode(token.toString());
            String email = jwt.getClaim("email").asString();

            System.out.println(email);

            if (Main.config.oauth.adminUsers.contains(email)) {
                userDAO.addAdminSession(request.getSession().getId(), email);

            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Вы неавторизованы");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            response.sendRedirect("/api/v1/admin/users");

        }
    }


    public String getAccessToken(String authCode) {
        String json = "";
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("grant_type", "authorization_code")
                .add("client_id", Main.config.oauth.clientId)
                .add("client_secret", Main.config.oauth.secret)
                .add("code", authCode)
                .add("redirect_uri", "http://localhost:8080/api/v1/admin/oauth")
                .build();
        final Request request = new Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            json = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
