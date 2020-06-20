package Dao;

import Bean.PetVisit;
import com.sun.jndi.cosnaming.CNCtx;
import util.Conn;

import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/11 - 17:11
 */
public class VisitDAO {

    ArrayList<PetVisit> arrayList = new ArrayList<PetVisit>();

    public ArrayList<PetVisit> getVisitMess(int id){
        PetVisit pv;
        Conn cn = new Conn();
        String sql = "select * from visits where pet_id = "+id;
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                pv = new PetVisit();
                pv.setId(cn.rs.getInt("id"));
                pv.setPetId(cn.rs.getInt("pet_id"));
                pv.setVisitDate(cn.rs.getString("visit_date"));
                pv.setDescription(cn.rs.getString("description"));
                arrayList.add(pv);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return arrayList;
    }

    public void addVisit(PetVisit pv){
        Conn cn = new Conn();
        String sql = "insert into visits (pet_id, visit_date, description) values" +
                " ((select id from pets where name = '"+pv.getName()+"'),?,?)";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
//            cn.pr.setObject(1,pv.getName());
            cn.pr.setObject(1,pv.getVisitDate());
            cn.pr.setObject(2,pv.getDescription());
            cn.pr.execute();
            cn.pr.execute("commit ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
