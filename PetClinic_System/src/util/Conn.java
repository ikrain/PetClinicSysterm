package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author cc
 * @data 2020/6/8 - 17:27
 */
public class Conn {

    final String DB_URL = "jdbc:mysql://localhost:3306/petclinic";
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String USER = "root";
    final String PASS = "123456";

    public Connection cn = null;
    public PreparedStatement pr = null;
    public ResultSet rs = null;

    public Conn() {

        try {
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void close(){
        try {
            if (cn!=null)
                cn.close();
            if (pr!=null)
                pr.close();
            if (rs!=null)
                rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
