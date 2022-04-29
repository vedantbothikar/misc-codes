package com.logic;


import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class calcservlet
 */
@WebServlet("/calcservlet")
public class calcservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private BeanClassLocal calc;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calcservlet</title>");            
            out.println("</head>");
            out.println("<body>");
             int a = Integer.parseInt(request.getParameter("t1"));
             out.println("<h1> Square is =" + calc.squarenumber(a)+"</h1>");
            out.println("<h1>Square Root is ="+calc.squareRoot(a)+ " </h1>");
            out.println("<h1> Cube is =" + calc.cubenumber(a)+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
