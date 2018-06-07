/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.forminvestigacion;


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
@Path("formInvestigacion")
public class FormInvestigacionJSON {

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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM   FORMINVESTIGACION");
        while (resultSet.next()) {
            
            Integer num = resultSet.getInt("ID");
            PreparedStatement preparedStatement =openCon().prepareStatement("SELECT * FROM FORMINVESTIGACION WHERE ID = ?");
            preparedStatement.setInt(1, num);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            
            while (resultSet1.next()) {
                if(resultSet1.getInt("ID")>0){
                dataId.add(resultSet1.getInt("ID"));
                } else {
                dataId.add(0);
                }
                if(resultSet1.getString("PREINFORME") != null){
                dataName.add(resultSet1.getString("PREINFORME"));
                } else {
                dataId.add("No definido");
                }
            }
            
            
            JSONcontent.add(dataId);
            JSONcontent.add(dataName);
            
//            dataIdentifier.add(rs.getString("IDENTIFIER"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PREINFORME"),JSONcontent);
            dataIdentifier.add(resultSet.getString("PERCENT"),JSONcontent);
            dataIdentifier.add(resultSet.getString("NITINS"),JSONcontent);
            dataIdentifier.add(resultSet.getString("NITCON"),JSONcontent);
            dataIdentifier.add(resultSet.getString("JEFAESTMAYOR"),JSONcontent);
        }
        
        JsonObject jsonObject = dataIdentifier.build();
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        }
        return Response.ok(jsonString).header("Access-Control-Allow-Origin", "*").build();
    }

}
