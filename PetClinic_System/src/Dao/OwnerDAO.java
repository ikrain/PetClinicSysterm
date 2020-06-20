package Dao;

import Bean.PetOwner;
import util.Conn;

import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/10 - 14:33
 */
public class OwnerDAO {

    Conn cn;
    ArrayList<PetOwner> arrayList;

    //获取宠物所有人信息
    public ArrayList<PetOwner> viewOwner(String ownerName){
        PetOwner petOwner;
        cn = new Conn();
        arrayList = new ArrayList<PetOwner>();
        String SQL = "select * from owners where name = '"+ownerName+"'";
        try {
            cn.pr = cn.cn.prepareStatement(SQL);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                petOwner = new PetOwner();
                petOwner.setId(cn.rs.getInt("id"));
                petOwner.setName(ownerName);
                petOwner.setAddress(cn.rs.getString("address"));
                petOwner.setCity(cn.rs.getString("city"));
                petOwner.setTelephone(cn.rs.getString("telephone"));
                arrayList.add(petOwner);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return arrayList;
    }

    //修改宠物所有人信息
    public void updateOwner(PetOwner petOwner){
        cn = new Conn();
        String sql = "update owners set name=?,address=?,city=?,telephone=? where id = '"+petOwner.getId()+"'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.setObject(1,petOwner.getName());
            cn.pr.setObject(2,petOwner.getAddress());
            cn.pr.setObject(3,petOwner.getCity());
            cn.pr.setObject(4,petOwner.getTelephone());
            cn.pr.executeUpdate();
            cn.pr.execute("commit ");
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
    }

    //增加新客户信息
    public void addPerson(PetOwner po){
        cn = new Conn();
        String sql = "insert into owners (name,address,city,telephone) value (?,?,?,?)";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.setObject(1,po.getName());
            cn.pr.setObject(2,po.getAddress());
            cn.pr.setObject(3,po.getCity());
            cn.pr.setObject(4,po.getTelephone());
            cn.pr.execute();
            cn.pr.execute("commit ");
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
    }

    //获取owners表中所有客户的姓名，供添加宠物信息时使用
    public ArrayList<PetOwner> getOwnerSelect(){
        cn = new Conn();
        PetOwner po;
        arrayList = new ArrayList<PetOwner>();
        String sql = "select name from owners";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                po = new PetOwner();
                po.setName(cn.rs.getString("name"));
                arrayList.add(po);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return arrayList;
    }

}
