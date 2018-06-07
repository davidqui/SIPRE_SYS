package com.sipre.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

private static Connection connection;

private static String url="jdbc:oracle:thin:@localhost:1521:XE";
private static String driver="oracle.jdbc.OracleDriver";
private static String user="sesat";
private static String pass="sesat2018";

    public Conexion() {
    }

public static Connection openCon() throws Exception{
    
    if(connection==null){
        Class.forName(driver);
        connection=DriverManager.getConnection(url,user,pass);
        System.out.println("coneccion ok");
    }
    return connection;
}

public static void closeCon() throws SQLException{
    
    if(connection!=null){
        connection.close();
    }
    connection=null;
}

}
