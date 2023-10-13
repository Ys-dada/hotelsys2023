package com.abc.hotelsys.controller;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.exception.ApplicationException;
import com.abc.hotelsys.service.HotelService;
import com.abc.hotelsys.service.HotelServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HotelMgrServlet
 */
@WebServlet("/hotelMgr")
public class HotelMgrServlet extends ViewBaseServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HotelMgrServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelMgrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //获得当前WEB服务器的临时文件夹的位置
        File tempFile=new File(System.getProperty("java.io.tmpdir"));

        //在fileupload工具库中，任何一个输入域都是一个磁盘文件项
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //最多能够支持一次性提供4096个磁盘文件项
        factory.setSizeThreshold(4096);
        //提供临时文件夹位置，以方便操作
        factory.setRepository(tempFile);

        //创建sfu,绑定factory
        ServletFileUpload sfu=new ServletFileUpload(factory);
        //设置单文件上传的最大体积
        sfu.setSizeMax(5000000);


        List<FileItem> fileItems=null;
        String task=null;

        if(sfu.isMultipartContent(request)) //判断request是否是多段提交
        {
            try {
                fileItems=sfu.parseRequest(request); //对请求进行过滤，获得所有的fileitem.
                for(FileItem item:fileItems)
                {
                    if(item.isFormField() && item.getFieldName().equals("task"))
                    {
                        task=item.getString("utf-8");
                        break;
                    }
                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
            task = request.getParameter("task");


        if("toInput".equals(task)){
//            ThymeleafUtil.printUtil("WEB-INF/views/hotel/input_hotel.html",null,response);
//              request.setAttribute("username1","mary");
              this.processTemplate("/hotel/input_hotel",request,response);
        }
        else if("create".equals(task)){

            Hotel hotel = new Hotel();

            for(FileItem item:fileItems)
            {
                if(item.isFormField() && item.getFieldName().equals("hotelname"))
                    hotel.setHotelName(item.getString("utf-8"));
                else if(item.isFormField() && item.getFieldName().equals("hoteladdr"))
                    hotel.setHotelAddr(item.getString("utf-8"));
                else if(item.isFormField() && item.getFieldName().equals("hotelphone"))
                    hotel.setHotelPhone(item.getString("utf-8"));
                else if(!item.isFormField() && item.getFieldName().equals("hotelpic")){
                    byte[] hotelPic=new byte[(int)item.getSize()];
                    item.getInputStream().read(hotelPic, 0, (int)item.getSize());
                    hotel.setHotelPic(hotelPic);
                }
            }

//            System.out.println(hotel);

            HotelService hotelService = new HotelServiceImpl();
            hotelService.createHotel(hotel);

            response.sendRedirect("hotelMgr?task=loadHotels");
        }
        else if("loadHotels".equals(task)){
            HotelService hotelService = new HotelServiceImpl();
            List<Hotel> hotelList = hotelService.loadHotels();

//            System.out.println(hotelList);
            request.setAttribute("hotelList",hotelList);
            this.processTemplate("/hotel/list_hotels",request,response);

        }
        else if("preUpdate".equals(task)){ //hotelMgr?task=preUpdate&hotelid=1

            int hotelId = Integer.parseInt(request.getParameter("hotelid"));

            HotelService hotelService = new HotelServiceImpl();
            Hotel hotel = hotelService.getHotelById(hotelId);

            request.setAttribute("hotel",hotel);
            this.processTemplate("/hotel/update_hotel",request,response);

        }
        else if("update".equals(task)){

            Hotel hotel = new Hotel();

            for(FileItem item:fileItems)
            {
                if(item.isFormField() && item.getFieldName().equals("hotelid")){
                    int hid = Integer.parseInt(item.getString("utf-8"));
                    hotel.setHotelId(hid);
                }
                else if(item.isFormField() && item.getFieldName().equals("hotelname"))
                    hotel.setHotelName(item.getString("utf-8"));
                else if(item.isFormField() && item.getFieldName().equals("hoteladdr"))
                    hotel.setHotelAddr(item.getString("utf-8"));
                else if(item.isFormField() && item.getFieldName().equals("hotelphone"))
                    hotel.setHotelPhone(item.getString("utf-8"));
                else if(item.isFormField() && item.getFieldName().equals("hotelroomcount")){
                    int cnt = Integer.parseInt(item.getString("utf-8"));
                    hotel.setHotelRoomCount(cnt);
                }
                else if(!item.isFormField() && item.getFieldName().equals("hotelpic")){

                    byte[] hotelPic=null;

                    if(item.getSize()>0){
                        hotelPic=new byte[(int)item.getSize()];
                        item.getInputStream().read(hotelPic, 0, (int)item.getSize());
                    }
                    else{
                        HotelService hotelService = new HotelServiceImpl();
                        hotelPic=hotelService.getHotelPicById(hotel.getHotelId());
                    }

                    hotel.setHotelPic(hotelPic);
                }
            }

//            System.out.println(hotel);

            logger.info("修改分店信息如下:"+hotel);

            HotelService hotelService = new HotelServiceImpl();
            hotelService.updateHotel(hotel);

            response.sendRedirect("hotelMgr?task=loadHotels");

        }
        else if("delete".equals(task)){ //hotelMgr?task=delete&hotelid=1
            int hotelId = Integer.parseInt(request.getParameter("hotelid"));
            HotelService hotelService = new HotelServiceImpl();
            try {
                hotelService.removeHotel(hotelId);
            }catch(ApplicationException e){
                request.setAttribute("errMsg",e.getMessage());
                List<Hotel> hotelList = hotelService.loadHotels();

//            System.out.println(hotelList);
                request.setAttribute("hotelList",hotelList);
                this.processTemplate("/hotel/list_hotels",request,response);
                return;
            }

            response.sendRedirect("hotelMgr?task=loadHotels");

        }
        else if("getpic".equals(task)){

            int hotelId=Integer.parseInt(request.getParameter("hotelid"));

            HotelService hotelService=new HotelServiceImpl();
            byte[] hotelPic=hotelService.getHotelPicById(hotelId);

            //没有从数据库中读取到分店的图片，使用默认图片
            if(hotelPic==null || hotelPic.length==0){
                String defaultPicPath = request.getRealPath("/")+ "WEB-INF/pics/no-pic.jpg"; //真实磁盘路径和网页路径之间的差异。
                FileInputStream fis =new FileInputStream(defaultPicPath);
                hotelPic=new byte[fis.available()];
                fis.read(hotelPic);
            }

            response.setContentType("image/jpeg");
            ServletOutputStream sos=response.getOutputStream();
            sos.write(hotelPic);
            sos.flush();
            sos.close();
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
