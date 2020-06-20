package Dao;

import Bean.Employee;
import util.Conn;

/**
 * @author cc
 * @data 2020/6/8 - 17:25
 */
public class EmpDAO {

    //验证用户是否存在，并且验证密码是否正确
    //查询是否存在用户名和密码均匹配的信息。若存在则修改temp的值
    public boolean verify(Conn cn, Employee ep){
        boolean temp = false;
        String sql = "select * from employee where name=? and password=?";
        try {
            cn.pr = cn.cn.prepareStatement(sql);
            cn.pr.setObject(1,ep.getName());
            cn.pr.setObject(2,ep.getPassword());
            cn.rs = cn.pr.executeQuery();
            while (cn.rs.next()){
                temp = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cn.close();
        return temp;
    }

}
