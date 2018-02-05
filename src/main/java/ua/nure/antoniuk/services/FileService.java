package ua.nure.antoniuk.services;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class FileService {


    public String uploadFile(String path, String name, HttpServletRequest request) {
        System.out.println(path + File.separator + name + "1");
        try {
            System.out.println(path + File.separator + name + "2");
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            for (FileItem item : multiparts) {System.out.println(path + File.separator + name + "3");
                if (!item.isFormField()) {
                    item.write(new File(path + File.separator + name));
                    System.out.println(path + File.separator + name + "4");
                }
            }
            //File uploaded successfully
            return "";
        } catch (Exception ex) {
            return "File Upload Failed";
        }
    }



}
