package servlet;

import Bean.FeedBack;
import Bean.Pet;
import Bean.PetOwner;
import Bean.PetVisit;
import Dao.PetDAO;
import Dao.VisitDAO;
import util.FBK;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/11 - 17:05
 */
public class PetVisitServlet extends HttpServlet {

    ArrayList<Pet> arrayListPet, apt;
    ArrayList<PetOwner> arrayListPo;
    FBK fbk = new FBK();
    ArrayList<PetVisit> arrayList = new ArrayList<PetVisit>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String purpose = req.getParameter("purpose");

        if ( purpose.equals("visitList") ){
            //设置所属病例的宠物信息
            req.setCharacterEncoding("UTF-8");

            arrayListPet = new ArrayList<Pet>();
            Pet pet = new Pet();
            pet.setName(req.getParameter("name"));
            pet.setType(req.getParameter("type"));
            pet.setOwnerName(req.getParameter("ownerName"));
            arrayListPet.add(pet);
            req.setAttribute("visitOwner",arrayListPet);

            //设置指定宠物id的问诊信息
            VisitDAO vd = new VisitDAO();
            int pet_id = Integer.parseInt(req.getParameter("pet_id"));
            arrayList = vd.getVisitMess(pet_id);
            req.setAttribute("listVisit",arrayList);

            if (arrayList.size()==0){
                //若无问诊记录，则设置反馈信息，供用户查看
                fbk.setFeedBack("该宠物无任何问诊记录", req);
            }

            req.getRequestDispatcher("petVisit.jsp").forward(req,resp);

        }else if (purpose.equals("editor")){
            req.setCharacterEncoding("UTF-8");
            PetDAO pd = new PetDAO();
            apt = new ArrayList<>();
            apt = pd.petMess(req.getParameter("pet_name"));
            req.setAttribute("someMess",apt);
            req.getRequestDispatcher("newVisit.jsp").forward(req,resp);

        }else if (purpose.equals("addVisit")){
            //增加问诊记录
            req.setCharacterEncoding("UTF-8");

            String petName = req.getParameter("petName");
            String ownerName = req.getParameter("ownerName");
            String type = req.getParameter("type");
            String birth_date = req.getParameter("birth_date");
            String description = req.getParameter("description");

            if (petName.isEmpty() || ownerName.isEmpty() || type.isEmpty() || birth_date.isEmpty() || description.isEmpty()){

                PetDAO pd = new PetDAO();
                apt = new ArrayList<>();
                apt = pd.petMess(petName);
                req.setAttribute("someMess",apt);

                //所插入的病例信息若果为空则反馈用户信息
                fbk.setFeedBack("宠物问诊信息均不能为空",req);
                req.getRequestDispatcher("newVisit.jsp").forward(req,resp);
            }else {

                VisitDAO vd = new VisitDAO();
                PetVisit pv = new PetVisit();
                pv.setName(req.getParameter("petName"));
                pv.setOwnerName(req.getParameter("ownerName"));
                pv.setType(req.getParameter("type"));
                pv.setVisitDate(req.getParameter("birth_date"));
                pv.setDescription(req.getParameter("description"));
                vd.addVisit(pv);

                fbk.setFeedBack("新的病例信息插入成功",req);

                req.getRequestDispatcher("petsearch.jsp").forward(req,resp);

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
