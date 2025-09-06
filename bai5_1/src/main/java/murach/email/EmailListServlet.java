package murach.email;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "join";

        if (!"add".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }

        String first = trim(req.getParameter("firstName"));
        String last  = trim(req.getParameter("lastName"));
        String email = trim(req.getParameter("email"));

        if (isBlank(first) || isBlank(last) || isBlank(email)) {
            addCookie(resp, "firstName", first);
            addCookie(resp, "lastName",  last);
            addCookie(resp, "email",     email);
            addCookie(resp, "message",   "Values are required.");
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            req.setAttribute("firstName", first);
            req.setAttribute("lastName",  last);
            req.setAttribute("email",     email);
            getServletContext().getRequestDispatcher("/thanks.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    private static boolean isBlank(String s){ return s == null || s.trim().isEmpty(); }
    private static String trim(String s){ return s == null ? null : s.trim(); }

    private static void addCookie(HttpServletResponse resp, String name, String value) {
        String enc = value == null ? "" : URLEncoder.encode(value, StandardCharsets.UTF_8);
        Cookie c = new Cookie(name, enc);
        c.setPath("/");
        c.setMaxAge(60);
        resp.addCookie(c);
    }
}