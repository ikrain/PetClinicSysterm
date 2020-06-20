package servlet;

import Bean.Pet;
import Bean.PetOwner;
import Dao.OwnerDAO;
import Dao.PetDAO;
import Dao.VetDAO;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
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
 * @data 2020/6/9 - 20:29
 */
public class PetServlet extends HttpServlet {

    Pet pet;
    FBK fbk = new FBK();
    ArrayList<Pet> arrayList = new ArrayList<Pet>();
    ArrayList<PetOwner> arrayListPO = new ArrayList<PetOwner>();

    Method method = null;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public void petow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单信息
        String petname = req.getParameter("petname");
        String ownername = req.getParameter("ownername");
        System.out.println("反射机制");
        if (petname.isEmpty() && ownername.isEmpty()){
            //设置反馈信息，供用户查看
            fbk.setFeedBack("请输入正确的索引", req);
            req.getRequestDispatcher("petsearch.jsp").forward(req,resp);

        }else {
            //显示宠物和所有人信息
            PetDAO pd = new PetDAO();
            arrayList = pd.searchOOP(petname, ownername);
            req.setAttribute("petList", arrayList);

            //设置反馈信息，供用户查看
            fbk.setFeedBack("宠物信息查询成功",req);

            //跳转到指定jsp页面
            req.getRequestDispatcher("petsearch_name.jsp").forward(req, resp);
        }
    }

    public void see(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("see返");
        //显示宠物信息
        PetDAO pd = new PetDAO();
        arrayList = pd.petMess(req.getParameter("petname"));
        req.setAttribute("petMess",arrayList);

        //跳转到指定jsp页面
        req.getRequestDispatcher("petview_name.jsp").forward(req,resp);
    }

    public void send(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //转到修改编辑页面(由于request没有变，因此在view中的ArrayList的数据可以继续使用)
        req.setAttribute("petViewList",arrayList);

        //获取宠物所有类型
        PetDAO pt = new PetDAO();
        arrayList = pt.getTypeSelect();
        req.setAttribute("typeList",arrayList);

        //跳转到指定jsp页面
        req.getRequestDispatcher("petupdate.jsp").forward(req,resp);

    }

    public void upda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        //修改宠物信息
        PetDAO pd = new PetDAO();
        Pet pt = new Pet();
        pt.setId(Integer.parseInt(req.getParameter("petid")));
        pt.setName(req.getParameter("name"));
        pt.setBirthDate(req.getParameter("birthdate"));
        pt.setType(req.getParameter("updateType"));
        pt.setOwnerName(req.getParameter("ownername"));
        pd.petUpdate(pt);
        arrayList = pd.petMess(req.getParameter("name"));
        req.setAttribute("newPetMess", arrayList);

        fbk.setFeedBack("宠物信息修改成功",req);

        //跳转到指定jsp页面
        req.getRequestDispatcher("petview_name.jsp").forward(req,resp);

    }

    public void addpet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取所有用户名
        OwnerDAO od = new OwnerDAO();
        arrayListPO = od.getOwnerSelect();
        req.setAttribute("ownerSelect",arrayListPO);

        //获取所有宠物类型
        PetDAO pt = new PetDAO();
        arrayList = pt.getTypeSelect();
        req.setAttribute("typeSelect",arrayList);

        //跳转到指定jsp页面
        req.getRequestDispatcher("newpet.jsp").forward(req,resp);

    }

    public void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String petname = req.getParameter("petname");
        String typeSt = req.getParameter("typeSt");
        String birthdate = req.getParameter("birthdate");
        String ownerSt = req.getParameter("ownerSt");

        if (petname.isEmpty() || typeSt.isEmpty() || birthdate.isEmpty() || ownerSt.isEmpty()){
            //设置反馈信息，供用户查看
            fbk.setFeedBack("所插入的信息均不能为空", req);

            //跳转到指定jsp页面
            req.getRequestDispatcher("newpet.jsp").forward(req, resp);

        }else {

            //插入用户信息，先封装信息，再传值
            pet = new Pet();
            pet.setName(petname);
            pet.setType(typeSt);
            pet.setBirthDate(birthdate);
            pet.setOwnerName(ownerSt);

            PetDAO pd = new PetDAO();
            pd.addPet(pet);

            //设置反馈信息，供用户查看
            fbk.setFeedBack("宠物信息插入成功", req);

            //跳转到指定jsp页面
            req.getRequestDispatcher("petsearch.jsp").forward(req, resp);
        }

    }

    public void allPet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PetDAO pd = new PetDAO();
        arrayList = pd.allPet();
        req.setAttribute("petList", arrayList);

        //设置用户反馈信息
        fbk.setFeedBack("所有兽医信息显示成功",req);

        req.getRequestDispatcher("petsearch_name.jsp").forward(req, resp);

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String petName = req.getParameter("petName");
        String ownerName = req.getParameter("ownerName");

        PetDAO pd = new PetDAO();

        pd.delPet(petName, ownerName);

        //设置用户反馈信息
        fbk.setFeedBack("客户信息删除成功",req);
        req.getRequestDispatcher("petsearch.jsp").forward(req, resp);

    }


}
