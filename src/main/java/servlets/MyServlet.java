package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MyServlet extends HttpServlet {
    private final HashMap<String, String> addresses = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();
        String ipAddress = req.getRemoteAddr();
        String users = req.getHeader("User-Agent");
        addresses.put(ipAddress, users);

        resp.setContentType("text/html");

        responseBody.println("<ul>");
        addresses.forEach((ip, ua) -> {
            String tag = ip.equals(ipAddress) ? "b" : "span";
            responseBody.println("<li><" + tag + ">" + ip + "&nbsp;::&nbsp;" + ua + "</" + tag + "></li>");
        });
        responseBody.println("</ul>");
    }
}