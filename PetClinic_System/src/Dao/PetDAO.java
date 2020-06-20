package Dao;

import Bean.Pet;
import Bean.Vet;
import util.Conn;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/9 - 20:40
 */
public class PetDAO {

    Pet pt;
    String[] strName2;
    String[] strName;
    String[] strPet;
    ArrayList<Pet> arrayList = new ArrayList<Pet>();

    public ArrayList<Pet> searchOOP(String petName, String ownerName){
        if ( petName.isEmpty() && !ownerName.isEmpty() ){
            int j=0;
            int[] id;
            String sql;
            strPet = new String[21];
            strName2 = new String[21];
            id = searchName(ownerName);
            Conn cn = new Conn();
            //根据所有人名查询其宠物
            for (int i = 0; id[i]!=0 ; i++) {
                try {
                    sql = "select * from pets where owner_id = "+id[i];
                    System.out.println(id[i]);
                    cn.pr = cn.cn.prepareStatement(sql);
                    cn.rs = cn.pr.executeQuery();
                    while (cn.rs.next()){
                        strPet[j] = cn.rs.getString("name");
                        strName2[j] = strName[i];
                        j++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            cn.close();
            for (int i = 0; strName2[i]!=null ; i++) {
                pt = new Pet();
                pt.setName(strPet[i]);
                pt.setOwnerName(strName2[i]);
                arrayList.add(pt);
            }

        }else if (!petName.isEmpty() && ownerName.isEmpty()){
            int[] id;
            String sql;
            strName = new String[21];
            id = searchPetName(petName);
            Conn cn = new Conn();
            //根据宠物名查询其所有人
            try {
                for (int i = 0; id[i]!=0 ; i++) {
                    sql = "select * from owners where id = "+id[i];
                    cn.pr = cn.cn.prepareStatement(sql);
                    cn.rs = cn.pr.executeQuery();
                    while (cn.rs.next()){
                        strName[i] = cn.rs.getString("name");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            cn.close();
            for (int i = 0; strName[i]!=null ; i++) {
                pt = new Pet();
                pt.setName(strPet[i]);
                pt.setOwnerName(strName[i]);
                arrayList.add(pt);
            }
        }

        return arrayList;
    }

    //searchOOP方法的子功能方法
    public int[] searchName(String name){

        int i = 0;
        int[] id = new int[21];
        strName = new String[21];
        Conn cn = new Conn();
        String sql  = "select * from owners where name like '"+name+"%'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                id[i] = cn.rs.getInt("id");
                strName[i] = cn.rs.getString("name");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return id;
    }

    //searchOOP方法的子功能方法
    public int[] searchPetName(String petName){
        int i = 0;
        int[] id = new int[21];
        strPet = new String[21];
        Conn cn = new Conn();
        String sql  = "select * from pets where name like '"+petName+"%'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                id[i] = cn.rs.getInt("owner_id");
                strPet[i] = cn.rs.getString("name");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return id;
    }


    //获取宠物信息
    public ArrayList<Pet> petMess(String petname){
        Conn cn = new Conn();
        String typeid,ownerid;
        String sql = "select * from pets where name = '"+petname+"'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            cn.rs.first();
            pt = new Pet();
            typeid = cn.rs.getString("type_id");
            ownerid = cn.rs.getString("owner_id");
            pt.setId(cn.rs.getInt("id"));
            pt.setName(cn.rs.getString("name"));
            pt.setBirthDate(cn.rs.getString("birth_date"));
            pt.setType(getType(typeid));
            pt.setOwnerName(getOwnerName(ownerid));
            arrayList.add(pt);
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return arrayList;
    }

    //配合petMess方法，根据指定宠物的owner_id获取其所有人的姓名，并返回
    public String getOwnerName(String ownerId){
        Conn cn = new Conn();
        String name="";
        String sql = "select name from owners where id = '"+ownerId+"'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                name = cn.rs.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return name;
    }

    //配合petMess方法，根据指定宠物的type_id获取其种类的名称，并返回
    public String getType(String typeId){
        Conn cn = new Conn();
        String type="";
        String sql = "select name from types where id = '"+typeId+"'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                type = cn.rs.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return type;
    }

    //修改宠物信息
    public void petUpdate(Pet pet){

        Conn cn = new Conn();
        String sql = "update pets set name=?,birth_date=?," +
                "type_id=(select id from types where name = '"+pet.getType()+"')," +
                "owner_id=(select id from owners where name = '"+pet.getOwnerName()+
                "') where id = "+pet.getId();
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.setObject(1,pet.getName());
            cn.pr.setObject(2,pet.getBirthDate());
            cn.pr.executeUpdate();
            cn.pr.execute("commit ");
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
    }

    //获取所有宠物种类
    public ArrayList<Pet> getTypeSelect(){
        Conn cn = new Conn();
        ArrayList<Pet> ptTy = new ArrayList<Pet>();
        String sql = "select name from types";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                pt = new Pet();
                pt.setType(cn.rs.getString("name"));
                ptTy.add(pt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return ptTy;
    }

    //增加新宠物信息
    public void addPet(Pet pet){
        Conn cn = new Conn();
        String sql = "insert into pets (name,birth_date,type_id,owner_id) value " +
                "(?,?,(select id from types where name = '"+pet.getType()+"')," +
                "(select id from owners where name = '"+pet.getOwnerName()+"'))";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.setObject(1,pet.getName());
            cn.pr.setObject(2,pet.getBirthDate());
            cn.pr.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
    }

    //获取所有宠物名称
    public ArrayList<Pet> getPNSelect(){
        Conn cn = new Conn();
        ArrayList<Pet> apt = new ArrayList<Pet>();
        String sql = "select name from pets";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                pt = new Pet();
                pt.setName(cn.rs.getString("name"));
                apt.add(pt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return apt;
    }


    public ArrayList<Pet> allPet(){
        int[] id = getPetOwnerId();
        Conn cn = new Conn();
        strName = new String[21];
        try {
            for (int j = 0; id[j]!=0; j++) {
                String sql = "select * from owners where id = "+id[j];
                cn.pr = cn.cn.prepareStatement(sql);
                cn.rs = cn.pr.executeQuery();
                while (cn.rs.next()) {
                    strName[j] = cn.rs.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; strName[i]!=null; i++) {
            pt = new Pet();
            pt.setOwnerName(strName[i]);
            pt.setName(strPet[i]);
            arrayList.add(pt);
            System.out.println(strName[i]+strPet[i]);
        }
        cn.close();
        return arrayList;

    }

    public int[] getPetOwnerId(){
        int i = 0;
        int[] id = new int[21];
        strPet = new String[21];
        Conn cn = new Conn();
        String sql = "select * from pets";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                id[i] = cn.rs.getInt("owner_id");
                strPet[i] = cn.rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return id;
    }

    //删除单个宠物信息
    public void delPet(String petName, String ownerName){
        System.out.println(petName+"pet");
        delConn(petName);
        Conn cn = new Conn();
        try {
            String sql = "delete from owners where name = '"+ownerName+"'";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.execute();
            cn.pr.execute("commit ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    public void delConn(String petName){
        int petId = 0;
        Conn cn = new Conn();
        try {
            String sql = "select * from pets where name = '"+petName+"'";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                petId = cn.rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        delVisit(petId);
        delPet(petId);
    }

    public void delPet(int id){
        Conn cn = new Conn();
        try {
            String sql = "delete from pets where id = "+id;
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.execute();
            cn.pr.execute("commit ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    //通过宠物id获取visit的id
    public void getPetId(int petId){
        int i = 0;
        int[] id = new int[21];
        Conn cn = new Conn();
        try {
            String sql = "select * from visits where pet_id = "+petId;
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                id[i++] = cn.rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    //删除宠物的访问记录
    public void delVisit(int petId){
        Conn cn = new Conn();
        String sql = "delete from visits where pet_id = "+petId;
        try {
                cn.pr = cn.cn.prepareStatement(sql);
                cn.pr.execute();
                cn.pr.execute("commit ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

}
