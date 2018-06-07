/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.formsbase;

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
@Path("insertfbase")
public class InsertFormsBase {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("base") int base, @QueryParam("jsondata") String jsondata,  @QueryParam("status") int status,  
            @QueryParam("usuario_creador") String usuario_creador, @QueryParam("fecha_creacion") Date fecha_creacion, @QueryParam("usuario_modificador") String usuario_modificador,
            @QueryParam("fecha_modificacion") Date fecha_modificacion) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.FORMS_BASE "
                + "(ID, BASE, JSONDATA, STATUS, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION)"
                + " VALUES "
                + "(2,?,?,?,?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)");
        p.setInt(1, base);       //el primer interrogante
        p.setString(2, jsondata);
        p.setInt(3, status);
        p.setString(4, usuario_creador);
        p.setString(5, usuario_modificador);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
