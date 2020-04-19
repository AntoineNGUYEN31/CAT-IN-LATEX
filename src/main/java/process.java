//package demotest;
 
import java.io.IOException;
//import java.util.Base64;

import org.apache.commons.codec.binary.Base64;
//import java.util.Arrays;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class guru_register
 */
@WebServlet("/process")
public class process extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String input1 = String.valueOf(request.getParameter("input1"));
      if (request.getParameter("decode") != null) {
        byte[] message = Base64.decodeBase64(input1.getBytes());
        String res1=new String(message);
        request.setAttribute("res1", res1);
        request.setAttribute("preinput1", input1);
      }else{
        byte[] message = input1.getBytes();
        String res1=Base64.encodeBase64String(message);
        request.setAttribute("res1", res1); 
        request.setAttribute("preinput1", input1);
      }
      // Redisplay JSP.
      request.getRequestDispatcher("index.jsp").forward(request, response); 
    }
}
