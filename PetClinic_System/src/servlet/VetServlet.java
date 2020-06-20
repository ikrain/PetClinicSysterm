package servlet;

import Bean.Vet;
import Dao.VetDAO;
import util.FBK;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/9 - 14:43
 */
public class VetServlet extends HttpServlet {

    VetDAO vd;
    Method method = null;
    FBK fbk = new FBK();
    ArrayList<Vet> arrayList = new ArrayList<Vet>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String purpose = req.getParameter("purpose");

        Class c = this.getClass();          //获取当前类的CLass对象

        try {
            method = c.getMethod(purpose, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);       //反射调用方法
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void selectVet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vetName = req.getParameter("vetname");
        String vetSpecial = req.getParameter("vetspecialist");

        //当兽医名和专业特长均为空时
        if ( vetName.isEmpty() && vetSpecial.isEmpty() ){

            //设置用户反馈信息
            fbk.setFeedBack("请输入正确的索引",req);

            req.getRequestDispatcher("vetsearch.jsp").forward(req,resp);

        }else {
            vd = new VetDAO();
            arrayList = vd.search(vetName, vetSpecial);
            req.setAttribute("list", arrayList);

            //设置用户反馈信息
            fbk.setFeedBack("兽医信息查询成功",req);

            req.getRequestDispatcher("vetsearch_name.jsp").forward(req, resp);

        }
    }

    public void allVet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vd = new VetDAO();
        arrayList = vd.allVet();
        req.setAttribute("list", arrayList);

        //设置用户反馈信息
        fbk.setFeedBack("所有兽医信息显示成功",req);

        req.getRequestDispatcher("vetsearch_name.jsp").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vetName = req.getParameter("vet");

        vd = new VetDAO();

        vd.delVet(vetName);

        //设置用户反馈信息
        fbk.setFeedBack("兽医信息插入成功",req);
        req.getRequestDispatcher("vetsearch.jsp").forward(req, resp);

    }

    public void addVet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vetName = req.getParameter("vetName");
        String vetSpec = req.getParameter("vetSpec");

        VetDAO vd = new VetDAO();

        vd.addVet(vetName,vetSpec);

        //设置用户反馈信息
        fbk.setFeedBack("兽医信息删除成功",req);
        req.getRequestDispatcher("vetsearch.jsp").forward(req, resp);

    }

}
