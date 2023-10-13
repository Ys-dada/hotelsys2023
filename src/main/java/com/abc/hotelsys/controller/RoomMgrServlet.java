package com.abc.hotelsys.controller;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.service.*;
import com.abc.hotelsys.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HotelMgrServlet
 */
@WebServlet("/roomMgr")
public class RoomMgrServlet extends ViewBaseServlet {

    private static final Logger logger = Logger.getLogger(RoomMgrServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomMgrServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String task = request.getParameter("task");

        if("toInput".equals(task)){

            HotelService hotelService = new HotelServiceImpl();
            List<Hotel> hotelList = hotelService.loadHotels();
            request.setAttribute("hotelList",hotelList);

            logger.info("开始回显新增房间主界面，加载了"+hotelList.size()+"个分店信息完成。");

            this.processTemplate("/room/input_room",request,response);
        }
        else if("create".equals(task)){

            Room room = new Room();
            room.setRoomNo(request.getParameter("roomNo"));

            String[] roomEquips = request.getParameterValues("roomEquip");
            room.setRoomEquip(roomEquips);
            room.setRoomStatus(request.getParameter("roomStatus"));
            room.setRoomType(request.getParameter("roomType"));
            room.setRoomMemo(request.getParameter("roomMemo"));

            Hotel hotel = new Hotel();
            hotel.setHotelId(Integer.parseInt(request.getParameter("hotelid")));
            room.setHotel(hotel);

//            System.out.println(room);
            logger.debug("新增房间:"+ room);

            RoomService roomService = new RoomServiceImpl();
            roomService.createRoom(room);

            logger.info("房间信息增加成功!");

            response.sendRedirect("roomMgr?task=loadRooms");

        }
        else if("loadRooms".equals(task)){

            RoomQueryHelper helper = new RoomQueryHelper();

            if(StringUtils.isNotEmpty(request.getParameter("qryHotelId")))
                helper.setQryHotelNo(Integer.parseInt(request.getParameter("qryHotelId")));

            if(StringUtils.isNotEmpty(request.getParameter("qryRoomType")))
                helper.setQryRoomType(request.getParameter("qryRoomType"));

            if(StringUtils.isNotEmpty(request.getParameter("qryRoomStatus")))
                helper.setQryRoomStatus(request.getParameter("qryRoomStatus"));

            Page page = new Page();

            if(StringUtils.isNotEmpty(request.getParameter("pageno"))){
                page.setPageNo(Integer.parseInt(request.getParameter("pageno")));
            }

            RoomService roomService = new RoomServiceImpl();
            page = roomService.loadPagedRooms(helper,page);
//            System.out.println(roomList);

            request.setAttribute("page",page);
            HotelService hotelService = new HotelServiceImpl();
            List<Hotel> hotelList = hotelService.loadHotels();
            request.setAttribute("hotelList",hotelList);

            request.setAttribute("helper",helper);
            this.processTemplate("/room/list_rooms",request,response);

        }else if("delete".equals(task))
        {

            Integer roomId=Integer.valueOf(request.getParameter("roomId"));
            RoomService roomService = new RoomServiceImpl();
            //根据id找房间，根据房间找所属酒店


            HotelService hotelService=new HotelServiceImpl();
            //删除房间
            roomService.delRoom(roomId);



            response.sendRedirect("roomMgr?task=loadRooms");
        }else if("preUpdate".equals(task))
        {
            //获取房间信息
            Integer roomId=Integer.parseInt(request.getParameter("roomid"));
            RoomService roomService=new RoomServiceImpl();
            Room room=roomService.getRoomById(roomId);
            request.setAttribute("room",room);
            //获取所有酒店集合
            HotelService hotelService = new HotelServiceImpl();
            List<Hotel> hotelList = hotelService.loadHotels();
            request.setAttribute("hotelList",hotelList);

            this.processTemplate("/room/update_room",request,response);
        }else if("update".equals(task))
        {

            Room room = new Room();
            room.setRoomNo(request.getParameter("roomNo"));

            String[] roomEquips = request.getParameterValues("roomEquip");
            room.setRoomEquip(roomEquips);
            room.setRoomStatus(request.getParameter("roomStatus"));
            room.setRoomType(request.getParameter("roomType"));
            room.setRoomMemo(request.getParameter("roomMemo"));
            room.setRoomId(Integer.valueOf(request.getParameter("roomId")));
            Hotel hotel = new Hotel();
            hotel.setHotelId(Integer.valueOf(request.getParameter("hotelid")));
            room.setHotel(hotel);


            RoomService roomService = new RoomServiceImpl();
            roomService.updateRoom(room);

            response.sendRedirect("roomMgr?task=loadRooms");
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
