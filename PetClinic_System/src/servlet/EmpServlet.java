package servlet;

import Bean.FeedBack;
import Dao.EmpDAO;
import Bean.Employee;
import util.Conn;
import util.FBK;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/8 - 17:23
 * 登录
 */
public class EmpServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Conn cn = new Conn();
        FBK fbk = new FBK();
        EmpDAO ed = new EmpDAO();
        Employee ep = new Employee();
        ep.setName(req.getParameter("username"));
        ep.setPassword(req.getParameter("password"));
        if ( ed.verify(cn,ep) ){
            //登录成功
            HttpSession session = req.getSession();
            session.setAttribute("name",req.getParameter("username"));

            fbk.setFeedBack("登录成功",req);

            req.getRequestDispatcher("vetsearch.jsp").forward(req,resp);

        } else{
            //登录失败
            fbk.setFeedBack("登录失败，请输入正确的请户名密码",req);

            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }
        cn.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
