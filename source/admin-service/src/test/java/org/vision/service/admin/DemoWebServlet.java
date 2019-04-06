package org.vision.service.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="helloUser2", urlPatterns={"/getUser","/userInfo"},loadOnStartup=1)
public class DemoWebServlet extends HttpServlet {
    private static final String DEFAULT_USER="rzm";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user=request.getParameter("user");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer=response.getWriter();
        writer.append("<!DOCTYPE html>")
                .append("<html><head></head><body>")
                .append("用户是"+user)
                .append("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}