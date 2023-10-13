package com.abc.hotelsys.controller;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.service.HotelService;
import com.abc.hotelsys.service.HotelServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HotelMgrServlet
 */
@WebServlet("/abcMgr")
public class abcMgrServlet extends ViewBaseServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(abcMgrServlet.class);


    /**
     * @see HttpServlet#HttpServlet()
     */
    public abcMgrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String task = request.getParameter("task");
        if("abc".equals(task)){
            HotelService hotelService=new HotelServiceImpl();
            Integer hotelId=Integer.valueOf(request.getParameter("hotelId"));
            Hotel hotel=hotelService.getHotelById(hotelId);
            System.out.println(hotel);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
