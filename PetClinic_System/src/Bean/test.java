package Bean;

import Dao.PetDAO;
import Dao.VetDAO;
import util.Conn;

/**
 * @author cc
 * @data 2020/6/8 - 21:15
 */
public class test {



    public static void main(String[] args) {
//        Conn cn = new Conn();
//        String sql = "select * from employee where name=? and password=?";
//        int flag = 0;
//        try {
//            cn.pr = cn.cn.prepareStatement(sql);
//            cn.pr.setObject(1,"huahua");
//            cn.pr.setObject(2,"123456");
//            cn.rs = cn.pr.executeQuery();
//            while (cn.rs.next()){
//                flag = 1;
//            }
//            System.out.println(flag);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        /*PetDAO pd = new PetDAO();
        Pet pet = new Pet();
        pet.setId(9);
        pet.setName("小板凳");
        pet.setType("柯基");
        pet.setBirthDate("2020-12-12");
        pet.setOwnerName("李哥");
        pd.petUpdate(pet);*/

        /*VetDAO vd = new VetDAO();
        int id[];

        id = vd.searchF("马");
        for (int i = 0; i < id.length && id[i]!=0 ; i++) {
            System.out.println(id[i]);
        }*/

        String[] str = new String[10];
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
}
