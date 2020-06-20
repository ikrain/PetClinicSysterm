package servlet;

import Bean.FeedBack;
import Bean.PetOwner;
import Dao.OwnerDAO;
import util.FBK;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/10 - 14:35
 */
public class PetOwnerServlet extends HttpServlet {

    ArrayList<PetOwner> arrayList;
    FBK fbk = new FBK();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //此处使用url传参的方式实现多个请求公用一个servlet
        req.setCharacterEncoding("UTF-8");
        String purpose = req.getParameter("purpose");
        if (purpose.equals("see")){
            //显示宠物所有人信息
            OwnerDAO od = new OwnerDAO();
            arrayList =  od.viewOwner(req.getParameter("ownername"));
            req.setAttribute("ownerList",arrayList);
            req.getRequestDispatcher("ownerupdate.jsp").forward(req,resp);

        }else if (purpose.equals("upda")){
            req.setCharacterEncoding("UTF-8");
            String ownerid = req.getParameter("ownerid");
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String city = req.getParameter("city");
            String telephone = req.getParameter("telephone");

            if (ownerid.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() || telephone.isEmpty()){
                OwnerDAO od = new OwnerDAO();
                arrayList =  od.viewOwner(req.getParameter("ownername"));
                req.setAttribute("ownerList",arrayList);
                //设定反馈信息
                fbk.setFeedBack("所有人信息均不能为空", req);

            }else {

                //更新宠物所有人信息
                OwnerDAO od = new OwnerDAO();
                PetOwner po = new PetOwner();
                po.setId(Integer.parseInt(req.getParameter("ownerid")));
                po.setName(req.getParameter("name"));
                po.setAddress(req.getParameter("address"));
                po.setCity(req.getParameter("city"));
                po.setTelephone(req.getParameter("telephone"));
                od.updateOwner(po);
                arrayList = new ArrayList<PetOwner>();
                arrayList.add(po);
                req.setAttribute("newMess", arrayList);

                //设定反馈信息
                fbk.setFeedBack("所有人信息修改成功", req);
            }

            req.getRequestDispatcher("ownerupdate.jsp").forward(req,resp);

        }else if (purpose.equals("addperson")){
            //增加新客户,跳转到编辑信息页面
            req.getRequestDispatcher("newowner.jsp").forward(req,resp);

        }else if (purpose.equals("insert")){
            //获取表单中信息
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String city = req.getParameter("city");
            String telephone = req.getParameter("telephone");

            if (name.isEmpty() || address.isEmpty() || city.isEmpty() || telephone.isEmpty()){
                //设定反馈信息
                fbk.setFeedBack("所插入的信息均不为空", req);

                //跳转到指定jsp页面
                req.getRequestDispatcher("newowner.jsp").forward(req, resp);
            }else {
                //插入用户信息，先封装信息，再传值
                PetOwner po = new PetOwner();
                po.setName(name);
                po.setAddress(address);
                po.setCity(city);
                po.setTelephone(telephone);
                OwnerDAO od = new OwnerDAO();
                od.addPerson(po);

                //设定反馈信息
                fbk.setFeedBack("所有人信息插入成功", req);

                //跳转到指定jsp页面
                req.getRequestDispatcher("petsearch.jsp").forward(req, resp);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
