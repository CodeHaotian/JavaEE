package com.java.servlet;

import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 修改用户信息
 */
@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding( "utf-8" );

        //2.获取表单数据
        Map<String, String[]> map = request.getParameterMap();

        //3.封装对象
        User user = new User();
        try {
            BeanUtils.populate( user, map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用service修改
        UserService service = new UserServiceImpl();
        service.updateUser( user );

        //5.跳转到查询所有的servlet
        response.sendRedirect( request.getContextPath() + "/findUserByPageServlet" );

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
