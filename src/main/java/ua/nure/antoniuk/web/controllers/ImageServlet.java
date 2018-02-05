package ua.nure.antoniuk.web.controllers;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.services.FileService;
import ua.nure.antoniuk.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ImageServlet", urlPatterns = Mapping.SERVLET_IMAGE_URL)
public class ImageServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class);
    private FileService fileService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String redirect = request.getContextPath() + "/" + Mapping.SERVLET_CABINET;
        if (ServletFileUpload.isMultipartContent(request)) {
            if (Util.isMatch(request.getContextPath() + "/resources/image/car/\\d+", request.getRequestURI())) {
                List<Car> cars = (List<Car>) request.getSession().getAttribute(Attributes.SESSION_CARS);
                for (Car car : cars) {
                    if (car.getId() == Integer.parseInt(request.getRequestURI().replace(request.getContextPath() + "/resources/image/car/", ""))) {
                        LOGGER.trace(request.getRequestURI());
                        LOGGER.trace(request.getRequestURL());
                        error = fileService.uploadFile(request.getServletContext().getInitParameter("imgPath")
                                + "/car", request.getRequestURI().replace(request.getContextPath() + "/resources/image/car/", "") + ".jpg", request);
                        break;
                    }
                    error = "You can't change a stranger image car";
                }
                redirect = request.getContextPath() + Mapping.SERVLET_GARAGE_URL;
            } else if (Util.isMatch(request.getContextPath() + "/resources/image/user/\\d+", request.getRequestURI())) {
                if (((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId() == Integer.parseInt(request.getRequestURI().replace(request.getContextPath() + "/resources/image/user/", ""))) {
                    error = fileService.uploadFile(request.getServletContext().getInitParameter("imgPath")
                            + "/user", request.getRequestURI().replace(request.getContextPath() + "/resources/image/user/", "") + ".jpg", request);
                } else {
                    error = "You can't change a stranger image";
                }
            } else {
                error = Constants.INVALID_FORMAT;
            }

        } else {
            error = "Sorry this Servlet only handles file upload request";
        }
        LOGGER.error(error);
        request.getSession().setAttribute(Attributes.SESSION_ERROR_UPLOAD, error);
        response.sendRedirect(redirect);
        //request.getRequestDispatcher("/result.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgPath = request.getRequestURI().replaceFirst(request.getContextPath() + "/resources/image", "");
        imgPath = request.getServletContext().getInitParameter("imgPath") + imgPath;
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            FileInputStream fin = new FileInputStream(imgPath);
            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        fileService = (FileService) config.getServletContext().getAttribute(Constants.SERVICE_FILE);
        LOGGER.trace("Image Servlet init");
    }
}
