/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.formaccioninvestig;

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
@Path("insertaccioninvest")
public class InsertFormaccioninvestig {

    @GET
    @Path("/get")
    public Response getbyquery(@QueryParam("idform") String idform,  
            @QueryParam("typetime") String typetime,  
            @QueryParam("planaccionres") String planaccionres, 
            @QueryParam("planaccionrecursos") String planaccionrecursos, 
            @QueryParam("planaccionrescumplir") String planaccionrescumplir,
            @QueryParam("usuario_creador") String usuario_creador,  
            @QueryParam("usuario_modificador") String usuario_modificador,
            @QueryParam("grupo_informacion") int grupo_informacion
    ) throws ClassNotFoundException, SQLException, IOException, Exception {

        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.FORMACCIONINVESTIGACION "
                + "(ID, IDFORM, TYPETIME, PLANACCIONPREV, PLANACCIONRES, PLANACCIONDATEEXE, PLANACCIONRECURSOS, PLANACCIONRESCUMPLIR, PLANACCIONDATECLOSE, USUARIO_CREADOR, FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION,GRUPO_INFORMACION)"
                + " VALUES "
                + "(3,?,?,CURRENT_TIMESTAMP,?, CURRENT_TIMESTAMP, ?, ?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)");
        p.setString(1, idform);       //el primer interrogante
        p.setString(2, typetime);
        p.setString(3, planaccionres);
        p.setString(4, planaccionrecursos);
        p.setString(5, planaccionrescumplir);
        p.setString(6, usuario_creador);
        p.setString(7, usuario_modificador);
        p.setInt(8, grupo_informacion);
        p.executeUpdate();      // Se ejecuta la inserción.

        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
