/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.usersecure;

import com.sipre.process.*;
import com.sipre.formsbase.*;
import com.sipre.elemprestados.*;
import static com.sipre.util.Conexion.openCon;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author oscardiaz
 */
@Path("insertuser")
public class InsertUsersecure {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("mmbuser") String mmbuser, @QueryParam("pass") String pass,  @QueryParam("salt") String salt,  
            @QueryParam("name") String name, @QueryParam("type_dis") int type_dis, @QueryParam("status") int status,
            @QueryParam("userrole") int userrole, @QueryParam("id_p1") int id_p1, @QueryParam("id_p2") int id_p2, 
            @QueryParam("id_p3") int id_p3, @QueryParam("usuario_creador") String usuario_creador, @QueryParam("fecha_creacion") Date fecha_creacion, 
            @QueryParam("usuario_modificador") String usuario_modificador, @QueryParam("fecha_modificacion") Date fecha_modificacion) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.USERSECURE "
                + "(ID, MMBUSER, PASS, SALT, NAME, TYPE_DIS, STATUS, USERROLE, ID_P1, ID_P2, ID_P3, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION)"
                + " VALUES "
                + "(3,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)");
        p.setString(1, mmbuser);       //el primer interrogante
        p.setString(2, pass);
        p.setString(3, salt);
        p.setString(4, name);
        p.setInt(5, type_dis);
        p.setInt(6, status);
        p.setInt(7, userrole);
        p.setInt(8, id_p1);
        p.setInt(9, id_p2);
        p.setInt(10, id_p3);
        p.setString(11, usuario_creador);
        p.setString(12, usuario_modificador);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
