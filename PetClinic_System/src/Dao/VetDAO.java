package Dao;

import Bean.Vet;
import util.Conn;

import java.nio.channels.NotYetBoundException;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/9 - 14:44
 */
public class VetDAO {

    Conn cn = null;
    String strName[];
    String strSpec[];
    ArrayList<Vet> arrayList = new ArrayList<>();

    public ArrayList<Vet> search(String vetName, String vetSpecial){

        String sql;
        Vet vet;

        if (vetName.isEmpty() && !vetSpecial.isEmpty()){
            //根据专业特长查兽医名
            int id[];
            strName = new String[20];
            id = searchFspec(vetSpecial);
            cn = new Conn();
            try {
                for (int i = 0; id[i]!=0; i++) {
                    sql = "select * from vets where id = "+id[i];
                    cn.pr = cn.cn.prepareStatement(sql);
                    cn.rs = cn.pr.executeQuery();
                    while (cn.rs.next()) {
                        strName[i] = cn.rs.getString("name");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (!vetName.isEmpty() && vetSpecial.isEmpty()){

            //根据兽医名查专业特长
            int id[];
            strSpec = new String[20];
            id = searchF(vetName);
            cn = new Conn();
            try {
                for (int i = 0; i < id.length && id[i]!=0; i++) {
                    sql = "select name from specialties where id = "+id[i];
                    cn.pr = cn.cn.prepareStatement(sql);
                    cn.rs = cn.pr.executeQuery();
                    while (cn.rs.next()) {
                        strSpec[i] = cn.rs.getString("name");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; strName[i]!=null; i++) {
            vet = new Vet();
            vet.setVetName(strName[i]);
            if (strSpec[i]==null){
                vet.setSpecName(strSpec[i-1]);
            }else{
                vet.setSpecName(strSpec[i]);
            }
            arrayList.add(vet);
        }
        cn.close();
        return arrayList;
    }

    //模糊查询，根据兽医部分名称查其id以及全称
    public int[] searchF(String name){
        int i = 0;
        cn = new Conn();
        int Id[] = new int[20];
        strName = new String[20];
        String sql = "select * from vets where name like '"+name+"%'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                Id[i] = cn.rs.getInt("id");
                strName[i] = cn.rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return searchS(Id,i);
    }

    //根据兽医id获取兽医专业特长id
    public int[] searchS(int id[], int n){
        int i = 0;
        cn = new Conn();
        int idS[] = new int[20];
        try {
            for (int j = 0; j < n; j++) {
                String sql = "select * from vet_specialties where vet_id = "+id[j];
                cn.pr = cn.cn.prepareStatement(sql);
                cn.rs = cn.pr.executeQuery();
                while (cn.rs.next()) {
                    idS[i++] = cn.rs.getInt("specialty_id");
                }
                System.out.println(idS[j]+"s");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return idS;
    }


    //模糊查询，根据兽医部分名称查其id以及全称
    public int[] searchFspec(String name){
        int i = 0;
        cn = new Conn();
        int Id[] = new int[20];
        strSpec = new String[20];
        String sql = "select * from specialties where name like '"+name+"%'";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                Id[i] = cn.rs.getInt("id");
                strSpec[i] = cn.rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return searchSspec(Id,i);
    }

    //根据兽医id获取兽医专业特长id
    public int[] searchSspec(int id[], int n){
        int i = 0;
        cn = new Conn();
        int idS[] = new int[20];
        try {
            for (int j = 0; j < n; j++) {
                String sql = "select * from vet_specialties where specialty_id = "+id[j];
                cn.pr = cn.cn.prepareStatement(sql);
                cn.rs = cn.pr.executeQuery();
                while (cn.rs.next()) {
                    idS[i++] = cn.rs.getInt("vet_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return idS;
    }


    //获取所有兽医信息
    public ArrayList<Vet> allVet(){
        Vet vet;
        int k = 0;
        int[] id = getVetId();
        cn = new Conn();
        strSpec = new String[21];
        try {
            for (int j = 0; id[j]!=0; j++) {
                System.out.println(id[j]+"id");
                String sql = "select * from specialties where id = "+id[j];
                cn.pr = cn.cn.prepareStatement(sql);
                cn.rs = cn.pr.executeQuery();
                while (cn.rs.next()) {
                    strSpec[k++] = cn.rs.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; strName[i]!=null; i++) {
            vet = new Vet();
            vet.setVetName(strName[i]);
            vet.setSpecName(strSpec[i]);
            arrayList.add(vet);
        }
        cn.close();
        return arrayList;
    }

    public int[] getVetId(){
        int i = 0;
        int[] id = new int[21];
        strName = new String[21];
        cn = new Conn();
        String sql = "select * from vets";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                id[i] = cn.rs.getInt("id");
                strName[i] = cn.rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return searchS(id,i);
    }


    //删除单个兽医
    public void delVet(String vetName){
        System.out.println(vetName+"vet");
        delConn(vetName);
        cn = new Conn();
        try {
                String sql = "delete from vets where name = '"+vetName+"'";
                cn.pr = cn.cn.prepareStatement(sql);
                cn.pr.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    public void delConn(String vetName){
        cn = new Conn();
        try {
            String sql = "select * from vets where name = '"+vetName+"'";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                delSpec(cn.rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    public void delSpec(int id){
        cn = new Conn();
        try {
            String sql = "delete from vet_specialties where vet_id = "+id;
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    public void addVet(String vetName, String vetSpec){

        cn = new Conn();
        try {
            String sql = "insert into vets (name) values ('"+vetName+"')";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.executeUpdate();
            cn.pr.execute("commit ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        insertVet(getOneVetId(vetName), getSpecId(vetSpec));

    }

    public void insertVet(int oneVetId, int specId) {
        cn = new Conn();
        try {
            String sql = "insert into vet_specialties (vet_id, specialty_id) value ("+oneVetId+","+specId+")";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.execute();
            cn.pr.execute("commit ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
    }

    //获取兽医ID
    public int getOneVetId(String vetName){
        int vetId = 0;
        cn = new Conn();
        try {
            String sql = "select * from vets where name = '"+vetName+"'";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                vetId = cn.rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return vetId;
    }

    //获取特长ID
    public int getSpecId(String vetSpec){
        int specId = 0;
        cn = new Conn();
        try {
            String sql = "select * from specialties where name = '"+vetSpec+"'";
            cn.pr = cn.cn.prepareStatement(sql);
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()) {
                specId = cn.rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return specId;
    }

}
