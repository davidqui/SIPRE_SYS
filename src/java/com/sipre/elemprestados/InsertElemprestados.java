/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.elemprestados;

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
@Path("insertelemptd")
public class InsertElemprestados {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("userid") int userid, @QueryParam("elem") int elem,  @QueryParam("datein") Date datein,  
            @QueryParam("obs") String obs, @QueryParam("usuario_creador") String usuario_creador, @QueryParam("fecha_creacion") Date fecha_creacion,
            @QueryParam("usuario_modificador") String usuario_modificador, @QueryParam("fecha_modificacion") Date fecha_modificacion,
            @QueryParam("grupo_informacion") int grupo_informacion, @QueryParam("id") int id) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.LISTITEM "
                + "(USERID, ELEM, DATEIN, OBS, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION, GRUPO_INFORMACION, ID)"
                + " VALUES "
                + "(?,?,CURRENT_TIMESTAMP,?,?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP,?,?)");
        p.setInt(1, userid);       //el primer interrogante
        p.setInt(2, elem);
//        p.setTimestamp(3, (Timestamp) datein);       
        p.setString(4, obs);
        p.setString(5, usuario_creador);
//        p.setTimestamp(6, (Timestamp) fecha_creacion); 
        p.setString(7, usuario_modificador);
//        p.setTimestamp(8, (Timestamp) fecha_modificacion);  
        p.setInt(9, grupo_informacion);
        p.setInt(10, id);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
