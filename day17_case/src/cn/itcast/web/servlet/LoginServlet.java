package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.ipml.UserServiceIplm;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


//@WebServlet(value="/loginServlet")
@WebServlet(urlPatterns={"/loginServlet"})

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        //验证码校验
        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确，给出提示信息，跳转到页面
            request.setAttribute("log_msg","验证码错误!(Verification code error!)");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        //封装对象
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //判断登录
        //调用service完成数据库查询
        UserService service=new UserServiceIplm();
        User loginUser = service.login(user);
        if(loginUser!=null){
            //登陆成功，将用户存入session中，跳转页面
            session.setAttribute("users",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else {
            //登录失败
            request.setAttribute("log_msg","用户名密码错误!(username and password error!)");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
