package ua.nure.antoniuk.web.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ua.nure.antoniuk.util.Parameters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "ServletTest", urlPatterns = "/test")
public class ServletTest extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletTest.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            LOGGER.trace(request.getServletPath());
            System.out.println("dfghj");
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(request.getServletContext().getInitParameter("imgPath")
                                + "/car"+File.separator+request.getParameter(Parameters.UPLOAD_IMG_CAR) + ".jpg"));
                        System.out.println(request.getServletContext().getInitParameter("imgPath")
                                + "/car"+File.separator+request.getParameter(Parameters.UPLOAD_IMG_CAR) + ".jpg");
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            System.out.println("iusefpqgeoiu.h");
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
        System.out.println("iusefpiuwehgpfiuqgeoiu.h");
        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
