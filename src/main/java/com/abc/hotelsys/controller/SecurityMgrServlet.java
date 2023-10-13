package com.abc.hotelsys.controller;

import com.abc.hotelsys.domain.User;
import com.abc.hotelsys.exception.ApplicationException;
import com.abc.hotelsys.service.UserService;
import com.abc.hotelsys.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HotelMgrServlet
 */
@WebServlet("/securityMgr")
public class SecurityMgrServlet extends ViewBaseServlet {

    private static final Logger logger = Logger.getLogger(SecurityMgrServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecurityMgrServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String task = request.getParameter("task");

        if("toLogin".equals(task)) {
            this.processTemplate("/login",request,response);
        }
        else if("login".equals(task)){

            String userNo = request.getParameter("userNo");
            String userPwd = request.getParameter("userPwd");

            UserService userService = new UserServiceImpl();

            try{
                User user = userService.checkUser(userNo,userPwd);
                request.getSession().setAttribute("loginedUser",user);
                response.sendRedirect("securityMgr?task=main");
            }catch (ApplicationException e){
                e.printStackTrace();
                request.setAttribute("errMsg",e.getMessage());
                this.processTemplate("/login",request,response);
            }
        }
        else if("main".equals(task)){
            this.processTemplate("/main",request,response);
        }
        else if("logout".equals(task)){
            request.getSession().removeAttribute("loginedUser");
            request.getSession().invalidate();
            response.sendRedirect("securityMgr?task=toLogin");
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
