/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.formaccioninvestig;

import static com.sipre.util.Conexion.openCon;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author oscardiaz
 */
@Path("formaccioninv")
public class FormaccioninvestigJSON {

    @GET
    @Path("/get")
    public Response JSONData() throws ClassNotFoundException, SQLException, IOException, Exception {

        JsonObjectBuilder dataIdentifier = Json.createObjectBuilder();
        JsonObjectBuilder dataIdContent = Json.createObjectBuilder();
        JsonObjectBuilder dataNameContent = Json.createObjectBuilder();
        JsonArrayBuilder dataId = Json.createArrayBuilder();
        JsonArrayBuilder dataName = Json.createArrayBuilder();
        JsonArrayBuilder JSONcontent = Json.createArrayBuilder();
        
        String jsonString = "";
        Statement statement = openCon().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FORMACCIONINVESTIGACION");
        while (resultSet.next()) {
            
            Integer num = resultSet.getInt("ID");
            PreparedStatement preparedStatement =openCon().prepareStatement("SELECT * FROM FORMACCIONINVESTIGACION WHERE ID = ?");
            preparedStatement.setInt(1, num);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            
            while (resultSet1.next()) {
                if(resultSet1.getInt("ID")>0){
                dataId.add(resultSet1.getInt("ID"));
                } else {
                dataId.add(0);
                }
                if(resultSet1.getString("IDFORM") != null){
                dataName.add(resultSet1.getString("IDFORM"));
                } else {
                dataId.add("No definido");
                }
            }
            
            
            JSONcontent.add(dataId);
            JSONcontent.add(dataName);
            
            dataIdentifier.add(resultSet.getString("IDFORM"),JSONcontent);
            dataIdentifier.add(resultSet.getString("TYPETIME"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONPREV"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONRES"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONDATEEXE"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONRECURSOS"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONRESCUMPLIR"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PLANACCIONDATECLOSE"),JSONcontent);
            dataIdentifier.add(resultSet.getString("USUARIO_CREADOR"),JSONcontent);
            dataIdentifier.add(resultSet.getString("FECHA_CREACION"),JSONcontent);
            dataIdentifier.add(resultSet.getString("USUARIO_MODIFICADOR"),JSONcontent);
            dataIdentifier.add(resultSet.getString("FECHA_MODIFICACION"),JSONcontent);
            dataIdentifier.add(resultSet.getString("GRUPO_INFORMACION"),JSONcontent);
        }
        
        JsonObject jsonObject = dataIdentifier.build();
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        }
        return Response.ok(jsonString).header("Access-Control-Allow-Origin", "*").build();
    }

}
