package servlet;

import Bean.FeedBack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/9 - 11:46
 * 登出
 */
public class QuitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        ss.invalidate();

        //设置反馈信息
        ArrayList<FeedBack> arrayListFb = new ArrayList<>();
        FeedBack fb = new FeedBack();
        fb.setMess("退出成功");
        arrayListFb.add(fb);
        req.setAttribute("listFb",arrayListFb);

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
