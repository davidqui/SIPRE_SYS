/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.process;

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
@Path("insertprocess")
public class InsertProcess {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("nameprocess") String nameprocess, @QueryParam("iduser") int iduser,  @QueryParam("formatjson") String formatjson,  
            @QueryParam("loadprocess") int loadprocess, @QueryParam("percentprocess") int percentprocess, @QueryParam("ipaddress") String ipaddress,
            @QueryParam("usuario_creador") String usuario_creador, @QueryParam("fecha_creacion") Date fecha_creacion, @QueryParam("usuario_modificador") String usuario_modificador, 
            @QueryParam("fecha_modificacion") Date fecha_modificacion) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.PROCESS "
                + "(ID, NAMEPROCESS, IDUSER, FORMATJSON, LOADPROCESS, PERCENTPROCESS, IPADDRESS, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION)"
                + " VALUES "
                + "(2,?,?,?,?,?,?,?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)");
        p.setString(1, nameprocess);       //el primer interrogante
        p.setInt(2, iduser);
        p.setString(3, formatjson);
        p.setInt(4, loadprocess);
        p.setInt(5, percentprocess);
        p.setString(6, ipaddress);
        p.setString(7, usuario_creador);
        p.setString(8, usuario_modificador);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
