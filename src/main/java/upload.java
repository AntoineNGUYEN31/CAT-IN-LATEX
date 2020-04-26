import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

@WebServlet("/upload")
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Upload to /tmp
        /*
         * try { Part part =request.getPart("zipfile"); String fileName = new
         * File(part.getName()).getName(); part.write("/tmp" + File.separator +
         * fileName); request.setAttribute("status", "File Uploaded Successfully");
         * }catch (Exception ex) { request.setAttribute("status",
         * "File Upload Failed due to " + ex); } //generate the pdf
         * 
         * //return message request.getRequestDispatcher("result.jsp").forward(request,
         * response);
         */

        /**/

        String root = "";
        // sub folder is unique to avoid conflicts due to multiple access to service.
        // Epoch time is used for naming.
        String subFolder = String.valueOf(System.currentTimeMillis()) + "/";
        String log = "";
        String name="";

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

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        // item.write( new File("/tmp" + File.separator + name));
                        item.write(new File(workspace + name));
                        //request.setAttribute("fout", name);
                    }
                }
                // File uploaded successfully
                // String rootPath = System.getProperty("catalina.home");
                // ServletContext ctx = ServletContextEvent.getServletContext();
                // String relativePath = ctx.getInitParameter("tempfile.dir");
                request.setAttribute("status", "File Uploaded Successfully");
                // request.setAttribute("status", rootPath+"/"+relativePath);

            } catch (Exception ex) {
                request.setAttribute("status", "File Upload Failed due to " + ex);
            }
        } else {

            request.setAttribute("status", "No File found");
        }

        // unzip content to root
        String command;
        Process process;
        command = "cd " + workspace + "; unzip " + name;
        String[] unzip_cmd = { "/bin/sh", "-c", command };
        process = Runtime.getRuntime().exec(unzip_cmd);

        // search for tex file
        File f = new File(workspace);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                // We want to find only .c files
                return name.endsWith(".tex");
            }
        };
        File[] files = f.listFiles(filter);
        //the only .tex file is expected
        String fname = files[0].getName();
        //String fname = "main.tex";

        // build
        command = "cd " + workspace + "; pdflatex -interaction=nonstopmode " + fname + ">build.log 2>&1";
        String[] cmd = { "/bin/sh", "-c", command };
        process = Runtime.getRuntime().exec(cmd);

        // wait for the lateX compilation to finish, and get log
        try {
            int exitValue = process.waitFor();
            request.setAttribute("status", "Pdf created successfully");
            // get log data
            fileToString f2s = new fileToString();
            log = f2s.convert(workspace + "build.log");

        } catch (Exception e) {
            ;
        }

        request.setAttribute("fout", subFolder + fname.replace(".tex", ".pdf"));
        request.setAttribute("log", log);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

}