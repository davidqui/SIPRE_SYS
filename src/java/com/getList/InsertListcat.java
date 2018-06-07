/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getList;

import static com.sipre.util.Conexion.openCon;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author dquijanor
 */
@Path("insert")
public class InsertListcat {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("cat") int cat, @QueryParam("name") String name, @QueryParam("value") int value, @QueryParam("userid") int userid) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.LISTITEM "
                + "( ID_LISTCAT, NAMEITEM, VALUE, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION)"
                + " VALUES "
                + "(?,?,?,?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)");
        p.setInt(1, cat);       //el primer interrogante
        p.setString(2, name);
        p.setInt(3, value);
        p.setInt(4, userid);
        p.setInt(5, userid);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}