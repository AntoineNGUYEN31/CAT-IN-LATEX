import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.FileWriter;

import java.io.File;

@WebServlet("/process")
public class process extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String root = "";
    // sub folder is unique to avoid conflicts due to multiple access to service.
    // Epoch time is used for naming.
    String subFolder = String.valueOf(System.currentTimeMillis()) + "/";
    String log = "";

    // read workspace, e.g. /usr/local/tomcat/webapps/cat-in-latex/ from resource
    try {
      confReader conf = new confReader("config.json");
      root = conf.get("workspace");
    } catch (Exception e) {
      ;
    }

    String workspace = root + subFolder;
    File file = new File(workspace);
    file.mkdirs();

    // create .tex file in the subFolder
    String fname = String.valueOf(request.getParameter("fname"));
    String fcontent = String.valueOf(request.getParameter("fcontent"));
    try {
      FileWriter myWriter = new FileWriter(workspace + fname);
      myWriter.write(fcontent);
      myWriter.close();

    } catch (IOException e) {
      System.out.println("An error occurred: " + e);
    }

    // build project
    String command = "cd " + workspace + "; pdflatex -interaction=nonstopmode " + fname + ">build.log 2>&1";
    String[] cmd = { "/bin/sh", "-c", command };
    Process process = Runtime.getRuntime().exec(cmd);

    // wait for the lateX compilation to finish, and get log
    try {
      int exitValue = process.waitFor();
      // get log data
      fileToString f2s = new fileToString();
      log = f2s.convert(workspace + "build.log");

    } catch (Exception e) {
      ;
    }

    // redirection with info
    request.setAttribute("fout", subFolder + fname.replace(".tex", ".pdf"));
    request.setAttribute("log", log);
    request.setAttribute("status", "Pdf created successfully");
    request.getRequestDispatcher("result.jsp").forward(request, response);
  }
}
