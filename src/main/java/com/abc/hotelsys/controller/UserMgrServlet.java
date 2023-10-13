package com.abc.hotelsys.controller;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.domain.User;
import com.abc.hotelsys.exception.ApplicationException;
import com.abc.hotelsys.service.*;
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
@WebServlet("/userMgr")
public class UserMgrServlet extends ViewBaseServlet {

    private static final Logger logger = Logger.getLogger(UserMgrServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMgrServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");

        if("toInput".equals(task)) {
            this.processTemplate("/user/input_user",request,response);
        }
        else if("create".equals(task)) {

            UserService userService=new UserServiceImpl();
            String userNo=request.getParameter("userno");
            String userName=request.getParameter("username");
            String userPwd = request.getParameter("userpwd");
            String userpwdconfirm = request.getParameter("userpwdconfirm");

            if(StringUtils.isEmpty(userPwd)||StringUtils.isEmpty(userpwdconfirm)){
                 request.setAttribute("errMsg","操作员密码或者确认密码未填写!");
                 this.processTemplate("/user/input_user",request,response);
                 return;
            }
            else if(!userPwd.equals(userpwdconfirm)){
                 request.setAttribute("errMsg","操作员密码和确认密码不一致!");
                 this.processTemplate("/user/input_user",request,response);
                 return;
            }
            //执行创建操作
            User user=new User();
            user.setUserName(userName);
            user.setUserNo(userNo);
            user.setUserPwd(userPwd);

            try {
                userService.addUser(user);
            }catch(ApplicationException e){
                request.setAttribute("errMsg",e.getMessage());
                this.processTemplate("/user/input_user",request,response);

                return;
            }
            response.sendRedirect("/userMgr?task=loadUsers");

        }
        else if("loadUsers".equals(task)){

            UserService userService = new UserServiceImpl();
            request.setAttribute("userList",userService.loadUsers());

            System.out.println(userService.loadUsers());

            this.processTemplate("/user/list_users",request,response);

        }else if("delete".equals(task))
        {
           String userNo=request.getParameter("userNo");
           UserService userService=new UserServiceImpl();
           userService.deleteUser(userNo);
           response.sendRedirect("/userMgr?task=loadUsers");
        }else if("preUpdate".equals(task))
        {
            String userNo = request.getParameter("userNo");;
            UserService userService=new UserServiceImpl();
            User user=userService.getUserByNo(userNo);
            request.setAttribute("user",user);
            this.processTemplate("/user/update_user",request,response);
        }else if("update".equals(task))
        {
            User user=new User();
            user.setUserNo(request.getParameter("userno"));
            user.setUserName(request.getParameter("username"));
            user.setUserPwd(request.getParameter("userpwd"));

            UserService userService=new UserServiceImpl();
            userService.updateUser(user);
            response.sendRedirect("/userMgr?task=loadUsers");

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
