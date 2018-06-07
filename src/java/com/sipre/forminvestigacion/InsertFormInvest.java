/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipre.forminvestigacion;

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
 * @author dquijanor
 */
@Path("insertinvg")
public class InsertFormInvest {

    
    private static final int REGISTROS_BATCH = 100;

    @GET
    @Path("/get")
    public Response getbyquery(
            @QueryParam("preinforme") String preinforme, @QueryParam("percent") int percent, @QueryParam("nitins") String nitins,
            @QueryParam("nitcon") String nitcon, @QueryParam("jefaestmayor") String jefaestmayor, @QueryParam("unitac") String unitac,
            @QueryParam("divcoman") String divcoman, @QueryParam("fuerzatarea") String fuerzatarea, @QueryParam("brigadadireccion") String brigadadireccion,
            @QueryParam("cede") String cede, @QueryParam("datenovedad") String datenovedad, @QueryParam("horanovedad") String horanovedad,
            @QueryParam("minnovedad") String minnovedad, @QueryParam("dateanalisis") String dateanalisis, @QueryParam("zonanovedad") String zonanovedad,
            @QueryParam("coordireccion") String coordireccion, @QueryParam("paisnovedad") String paisnovedad, @QueryParam("depnovedad") String depnovedad,
            @QueryParam("ciudadnovedad") String ciudadnovedad, @QueryParam("caraclugar") String caraclugar, @QueryParam("sitioespecifico") String sitioespecifico,
            @QueryParam("clasenovedad") String clasenovedad, @QueryParam("novedadinternafuerza") String novedadinternafuerza, @QueryParam("novedadinternafuerzasub") String novedadinternafuerzasub,
            @QueryParam("gravedad") String gravedad, @QueryParam("partecuerpo") String partecuerpo, @QueryParam("partecuerpodes") String partecuerpodes,
            @QueryParam("atencioninmediata") String atencioninmediata, @QueryParam("infogrado") String infogrado, @QueryParam("infocargo") String infocargo,
            @QueryParam("infoname") String infoname, @QueryParam("infoextrelacion") String infoextrelacion, @QueryParam("infoextname") String infoextname,
            @QueryParam("fuenteagente") String fuenteagente, @QueryParam("agente") String agente, @QueryParam("otroagente") String otroagente,
            @QueryParam("mecalesion") String mecalesion, @QueryParam("nombrereporta") String nombrereporta, @QueryParam("gradoreporta") String gradoreporta,
            @QueryParam("cargoreporta") String cargoreporta, @QueryParam("pnombre") String pnombre, @QueryParam("snombre") String snombre,
            @QueryParam("papellido") String papellido, @QueryParam("sapellido") String sapellido, @QueryParam("persogrado") String persogrado,
            @QueryParam("persocargo") String persocargo, @QueryParam("armacuerpoesp") String armacuerpoesp, @QueryParam("persodoc") String persodoc,
            @QueryParam("datepersonnac") String datepersonnac, @QueryParam("lugarpersonnac") String lugarpersonnac, @QueryParam("datepersoningre") String datepersoningre,
            @QueryParam("personcontingente") String personcontingente, @QueryParam("timeposervicioanos") String timeposervicioanos, @QueryParam("timeposerviciomeses") String timeposerviciomeses,
            @QueryParam("timeposervicioanosunidad") String timeposervicioanosunidad, @QueryParam("timeposerviciomesesunidad") String timeposerviciomesesunidad, @QueryParam("horaslaborado") String horaslaborado,
            @QueryParam("minlaborado") String minlaborado, @QueryParam("timeposervicioanoscargo") String timeposervicioanoscargo, @QueryParam("timeposerviciomesescargo") String timeposerviciomesescargo,
            @QueryParam("persondireccion") String persondireccion, @QueryParam("personciudad") String personciudad, @QueryParam("persontel") String persontel,
            @QueryParam("personedad") String personedad, @QueryParam("personsexo") String personsexo, @QueryParam("personestado") String personestado,
            @QueryParam("personescolaridad") String personescolaridad, @QueryParam("personsalario") String personsalario, @QueryParam("personserviciosalud") String personserviciosalud,
            @QueryParam("personservicioeps") String personservicioeps, @QueryParam("personservicioarl") String personservicioarl, @QueryParam("personserviciotipovincu") String personserviciotipovincu,
            @QueryParam("personactividad") String personactividad, @QueryParam("personactividaddurantenovedad") String personactividaddurantenovedad, @QueryParam("personafp") String personafp,
            @QueryParam("persontrabajoespecifico") String persontrabajoespecifico, @QueryParam("personexpano") String personexpano, @QueryParam("personexpmeses") String personexpmeses,
            @QueryParam("personexpdias") String personexpdias, @QueryParam("personcapacitacionesdes") String personcapacitacionesdes, @QueryParam("dateultdesc") String dateultdesc,
            @QueryParam("dateultreentre") String dateultreentre, @QueryParam("deshechospresonal") String deshechospresonal, @QueryParam("desdetalladanovedad") String desdetalladanovedad,
            @QueryParam("desdetalladahechos") String desdetalladahechos, @QueryParam("desfalla") String desfalla, @QueryParam("desconsecuencias") String desconsecuencias,
            @QueryParam("desact") String desact, @QueryParam("condiequipodes") String condiequipodes, @QueryParam("condilugardes") String condilugardes,
            @QueryParam("condiambdes") String condiambdes, @QueryParam("condiordenpublicodes") String condiordenpublicodes, @QueryParam("condiorgdes") String condiorgdes,
            @QueryParam("condicomrelaciondes") String condicomrelaciondes, @QueryParam("condicultseguridaddes") String condicultseguridaddes, @QueryParam("condiliderazgodes") String condiliderazgodes,
            @QueryParam("condiprocedimientodes") String condiprocedimientodes, @QueryParam("condirecursosdes") String condirecursosdes, @QueryParam("condisupervisiondes") String condisupervisiondes,
            @QueryParam("condicompetenciades") String condicompetenciades, @QueryParam("condicognitivades") String condicognitivades, @QueryParam("condiemocionalesdes") String condiemocionalesdes,
            @QueryParam("condiconotrosdes") String condiconotrosdes, @QueryParam("condifisicasdes") String condifisicasdes, @QueryParam("condicomportamentalesdes") String condicomportamentalesdes,
            @QueryParam("conditercerosdes") String conditercerosdes, @QueryParam("condiidcontroldes") String condiidcontroldes, @QueryParam("condiusomaterialdes") String condiusomaterialdes,
            @QueryParam("condimanejotransportedes") String condimanejotransportedes, @QueryParam("condieppkitdes") String condieppkitdes, @QueryParam("condieppkitdesvehi") String condieppkitdesvehi,
            @QueryParam("condiposturasdes") String condiposturasdes, @QueryParam("condiarmmunexplodes") String condiarmmunexplodes, @QueryParam("condicompviades") String condicompviades,
            @QueryParam("condiautoinflingidasdes") String condiautoinflingidasdes, @QueryParam("vialtipovehi") String vialtipovehi, @QueryParam("vialplaca") String vialplaca,
            @QueryParam("vialmarca") String vialmarca, @QueryParam("vialcilindraje") String vialcilindraje, @QueryParam("vialdeptres") String vialdeptres,
            @QueryParam("vialsoat") String vialsoat, @QueryParam("vialestadolicencia") String vialestadolicencia, @QueryParam("vialdesdanos") String vialdesdanos,
            @QueryParam("vialcostorep") String vialcostorep, @QueryParam("vialsegtipovehi") String vialsegtipovehi, @QueryParam("vialsegplaca") String vialsegplaca,
            @QueryParam("vialsegmarca") String vialsegmarca, @QueryParam("vialsegcilindraje") String vialsegcilindraje, @QueryParam("vialsegdeptres") String vialsegdeptres,
            @QueryParam("vialsegsoat") String vialsegsoat, @QueryParam("vialsegestadolicencia") String vialsegestadolicencia, @QueryParam("vialsegdesdanos") String vialsegdesdanos,
            @QueryParam("vialsegcostorep") String vialsegcostorep, @QueryParam("desperdhum") String desperdhum, @QueryParam("desperdamb") String desperdamb,
            @QueryParam("desperdmater") String desperdmater, @QueryParam("desperdotras") String desperdotras, @QueryParam("desperddias") String desperddias,
            @QueryParam("desperdnumeromuertos") String desperdnumeromuertos, @QueryParam("desperdafectacion") String desperdafectacion, @QueryParam("desperdestimacionvalor") String desperdestimacionvalor,
            @QueryParam("desperdgeneral") String desperdgeneral, @QueryParam("entreiduno") String entreiduno, @QueryParam("entrecargouno") String entrecargouno,
            @QueryParam("entreteluno") String entreteluno, @QueryParam("entredesuno") String entredesuno, @QueryParam("entreiddos") String entreiddos,
            @QueryParam("entrecargodos") String entrecargodos, @QueryParam("entreteldos") String entreteldos, @QueryParam("entredesdos") String entredesdos,
            @QueryParam("entredesafectado") String entredesafectado, @QueryParam("revcumplio") String revcumplio, @QueryParam("revcausas") String revcausas,
            @QueryParam("revmedidas") String revmedidas, @QueryParam("registrofilename1") String registrofilename1, @QueryParam("registrofilename2") String registrofilename2,
            @QueryParam("registrofilename3") String registrofilename3, @QueryParam("imgfirmas") String imgfirmas, @QueryParam("iduser") int iduser,
            @QueryParam("usuario_creador") String usuario_creador,
            @QueryParam("usuario_modificador") String usuario_modificador, @QueryParam("grupo_informacion") int grupo_informacion
    ) throws ClassNotFoundException, SQLException, IOException, Exception {

//        try {  
        
        PreparedStatement p = openCon().prepareStatement("INSERT INTO SESAT.FORMINVESTIGACION "
                + "             ( ID, PREINFORME, PERCENT, NITINS, NITCON, JEFAESTMAYOR, UNITAC, DIVCOMAN" +
        "FUERZATAREA, BRIGADADIRECCION, CEDE, DATENOVEDAD, HORANOVEDAD, MINNOVEDAD, DATEANALISIS" +
        "ZONANOVEDAD, COORDIRECCION, PAISNOVEDAD, DEPNOVEDAD, CIUDADNOVEDAD, CARACLUGAR, SITIOESPECIFICO" +
        "CLASENOVEDAD, NOVEDADINTERNAFUERZA, NOVEDADINTERNAFUERZASUB, GRAVEDAD, PARTECUERPO, PARTECUERPODES" +
        "ATENCIONINMEDIATA, INFOGRADO, INFOCARGO, INFONAME, INFOEXTRELACION, INFOEXTNAME, FUENTEAGENTE" +
        "AGENTE, OTROAGENTE, MECALESION, NOMBREREPORTA, GRADOREPORTA, CARGOREPORTA, PNOMBRE, SNOMBRE" +
        "PAPELLIDO, SAPELLIDO, PERSOGRADO, PERSOCARGO, ARMACUERPOESP, PERSODOC, DATEPERSONNAC, LUGARPERSONNAC" +
        "DATEPERSONINGRE, PERSONCONTINGENTE, TIMEPOSERVICIOANOS, TIMEPOSERVICIOMESES, TIMEPOSERVICIOANOSUNIDAD" +
        "TIMEPOSERVICIOMESESUNIDAD, HORASLABORADO, MINLABORADO, TIMEPOSERVICIOANOSCARGO, TIMEPOSERVICIOMESESCARGO" +
        "PERSONDIRECCION, PERSONCIUDAD, PERSONTEL, PERSONEDAD, PERSONSEXO, PERSONESTADO, PERSONESCOLARIDAD" +
        "PERSONSALARIO, PERSONSERVICIOSALUD, PERSONSERVICIOEPS, PERSONSERVICIOARL, PERSONSERVICIOTIPOVINCU, PERSONACTIVIDAD" +
        "PERSONACTIVIDADDURANTENOVEDAD, PERSONAFP, PERSONTRABAJOESPECIFICO, PERSONEXPANO, PERSONEXPMESES" +
        "PERSONEXPDIAS, PERSONCAPACITACIONESDES, DATEULTDESC, DATEULTREENTRE, DESHECHOSPRESONAL, DESDETALLADANOVEDAD" +
        "DESDETALLADAHECHOS, DESFALLA, DESCONSECUENCIAS, DESACT, CONDIEQUIPODES, CONDILUGARDES, CONDIAMBDES, CONDIORDENPUBLICODES" +
        "CONDIORGDES, CONDICOMRELACIONDES, CONDICULTSEGURIDADDES, CONDILIDERAZGODES, CONDIPROCEDIMIENTODES" +
        "CONDIRECURSOSDES, CONDISUPERVISIONDES, CONDICOMPETENCIADES, CONDICOGNITIVADES, CONDIEMOCIONALESDES" +
        "CONDICONOTROSDES, CONDIFISICASDES, CONDICOMPORTAMENTALESDES, CONDITERCEROSDES, CONDIIDCONTROLDES" +
        "CONDIUSOMATERIALDES, CONDIMANEJOTRANSPORTEDES, CONDIEPPKITDES, CONDIEPPKITDESVEHI, CONDIPOSTURASDES" +
        "CONDIARMMUNEXPLODES, CONDICOMPVIADES, CONDIAUTOINFLINGIDASDES, VIALTIPOVEHI, VIALPLACA, VIALMARCA, VIALCILINDRAJE" +
        "VIALDEPTRES, VIALSOAT, VIALESTADOLICENCIA, VIALDESDANOS, VIALCOSTOREP, VIALSEGTIPOVEHI, VIALSEGPLACA" +
        "VIALSEGMARCA, VIALSEGCILINDRAJE, VIALSEGDEPTRES, VIALSEGSOAT, VIALSEGESTADOLICENCIA, VIALSEGDESDANOS" +
        "VIALSEGCOSTOREP, DESPERDHUM, DESPERDAMB, DESPERDMATER, DESPERDOTRAS, DESPERDDIAS, DESPERDNUMEROMUERTOS" +
        "DESPERDAFECTACION, DESPERDESTIMACIONVALOR, DESPERDGENERAL, ENTREIDUNO, ENTRECARGOUNO, ENTRETELUNO" +
        "ENTREDESUNO, ENTREIDDOS, ENTRECARGODOS, ENTRETELDOS, ENTREDESDOS, ENTREDESAFECTADO, REVCUMPLIO, REVCAUSAS" +
        "REVMEDIDAS, REGISTROFILENAME1, REGISTROFILENAME2, REGISTROFILENAME3, IMGFIRMAS, IDUSER, DATECREACION, USUARIO_CREADOR" +
        "FECHA_CREACION, USUARIO_MODIFICADOR, FECHA_MODIFICACION, GRUPO_INFORMACION )"
       + "             VALUES "
       + " (4,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)");

//        int counter = 0;
        p.setString(1, preinforme);      //el primer interrogante
        p.setInt(2, percent);
        p.setString(3, nitins);
        p.setString(4, nitcon);
        p.setString(5, jefaestmayor);
        p.setString(6, unitac);
        p.setString(7, divcoman);
        p.setString(8, fuerzatarea);
        p.setString(9, brigadadireccion);
        p.setString(10, cede);
        p.setString(11, datenovedad);
        p.setString(12, horanovedad);
        p.setString(13, minnovedad);
        p.setString(14, dateanalisis);
        p.setString(15, zonanovedad);
        p.setString(16, coordireccion);
        p.setString(17, paisnovedad);
        p.setString(18, depnovedad);
        p.setString(19, ciudadnovedad);
        p.setString(20, caraclugar);
        p.setString(21, sitioespecifico);
        p.setString(22, clasenovedad);
        p.setString(23, novedadinternafuerza);
        p.setString(24, novedadinternafuerzasub);
        p.setString(25, gravedad);
        p.setString(26, partecuerpo);
        p.setString(27, partecuerpodes);
        p.setString(28, atencioninmediata);
        p.setString(29, infogrado);
        p.setString(30, infocargo);
        p.setString(31, infoname);
        p.setString(32, infoextrelacion);
        p.setString(33, infoextname);
        p.setString(34, fuenteagente);
        p.setString(35, agente);
        p.setString(36, otroagente);
        p.setString(37, mecalesion);
        p.setString(38, nombrereporta);
        p.setString(39, gradoreporta);
        p.setString(40, cargoreporta);
        p.setString(41, pnombre);
        p.setString(42, snombre);
        p.setString(43, papellido);
        p.setString(44, sapellido);
        p.setString(45, persogrado);
        p.setString(46, persocargo);
        p.setString(47, armacuerpoesp);
        p.setString(48, persodoc);
        p.setString(49, datepersonnac);
        p.setString(50, lugarpersonnac);
        p.setString(51, datepersoningre);
        p.setString(52, personcontingente);
        p.setString(53, timeposervicioanos);
        p.setString(54, timeposerviciomeses);
        p.setString(55, timeposervicioanosunidad);
        p.setString(56, timeposerviciomesesunidad);
        p.setString(57, horaslaborado);
        p.setString(58, minlaborado);
        p.setString(59, timeposervicioanoscargo);
        p.setString(60, timeposerviciomesescargo);
        p.setString(61, persondireccion);
        p.setString(62, personciudad);
        p.setString(63, persontel);
        p.setString(64, personedad);
        p.setString(65, personsexo);
        p.setString(66, personestado);
        p.setString(67, personescolaridad);
        p.setString(68, personsalario);
        p.setString(69, personserviciosalud);
        p.setString(70, personservicioeps);
        p.setString(71, personservicioarl);
        p.setString(72, personserviciotipovincu);
        p.setString(73, personactividad);
        p.setString(74, personactividaddurantenovedad);
        p.setString(75, personafp);
        p.setString(76, persontrabajoespecifico);
        p.setString(77, personexpano);
        p.setString(78, personexpmeses);
        p.setString(79, personexpdias);
        p.setString(80, personcapacitacionesdes);
        p.setString(81, dateultdesc);
        p.setString(82, dateultreentre);
        p.setString(83, deshechospresonal);
        p.setString(84, desdetalladanovedad);
        p.setString(85, desdetalladahechos);
        p.setString(86, desfalla);
        p.setString(87, desconsecuencias);
        p.setString(88, desact);
        p.setString(89, condiequipodes);
        p.setString(90, condilugardes);
        p.setString(91, condiambdes);
        p.setString(92, condiordenpublicodes);
        p.setString(93, condiorgdes);
        p.setString(94, condicomrelaciondes);
        p.setString(95, condicultseguridaddes);
        p.setString(96, condiliderazgodes);
        p.setString(97, condiprocedimientodes);
        p.setString(98, condirecursosdes);
        p.setString(99, condisupervisiondes);
        p.setString(100, condicompetenciades);
        p.setString(101, condicognitivades);
        p.setString(102, condiemocionalesdes);
        p.setString(103, condiconotrosdes);
        p.setString(104, condifisicasdes);
        p.setString(105, condicomportamentalesdes);
        p.setString(106, conditercerosdes);
        p.setString(107, condiidcontroldes);
        p.setString(108, condiusomaterialdes);
        p.setString(109, condimanejotransportedes);
        p.setString(110, condieppkitdes);
        p.setString(111, condieppkitdesvehi);
        p.setString(112, condiposturasdes);
        p.setString(113, condiarmmunexplodes);
        p.setString(114, condicompviades);
        p.setString(115, condiautoinflingidasdes);
        p.setString(116, vialtipovehi);
        p.setString(117, vialplaca);
        p.setString(118, vialmarca);
        p.setString(119, vialcilindraje);
        p.setString(120, vialdeptres);
        p.setString(121, vialsoat);
        p.setString(122, vialestadolicencia);
        p.setString(123, vialdesdanos);
        p.setString(124, vialcostorep);
        p.setString(125, vialsegtipovehi);
        p.setString(126, vialsegplaca);
        p.setString(127, vialsegmarca);
        p.setString(128, vialsegcilindraje);
        p.setString(129, vialsegdeptres);
        p.setString(130, vialsegsoat);
        p.setString(131, vialsegestadolicencia);
        p.setString(132, vialsegdesdanos);
        p.setString(133, vialsegcostorep);
        p.setString(134, desperdhum);
        p.setString(135, desperdamb);
        p.setString(136, desperdmater);
        p.setString(137, desperdotras);
        p.setString(138, desperddias);
        p.setString(139, desperdnumeromuertos);
        p.setString(140, desperdafectacion);
        p.setString(141, desperdestimacionvalor);
        p.setString(142, desperdgeneral);
        p.setString(143, entreiduno);
        p.setString(144, entrecargouno);
        p.setString(145, entreteluno);
        p.setString(146, entredesuno);
        p.setString(147, entreiddos);
        p.setString(148, entrecargodos);
        p.setString(149, entreteldos);
        p.setString(150, entredesdos);
        p.setString(151, entredesafectado);
        p.setString(152, revcumplio);
        p.setString(153, revcausas);
        p.setString(154, revmedidas);
        p.setString(155, registrofilename1);
        p.setString(156, registrofilename2);
        p.setString(157, registrofilename3);
        p.setString(158, imgfirmas);
        p.setInt(159, iduser);
        p.setString(160, usuario_creador);
        p.setString(161, usuario_modificador);
        p.setInt(162, grupo_informacion);
        
        //agregamos la sentencia al lote
//        p.addBatch();
        p.executeUpdate();      // Se ejecuta la inserción.
        //aumentamos el contados de lote
//        counter++;
//        //al tener 1000 o más sentencias, mandamos todas a ejecutar
//        //y reiniciamos el contador
//        if (counter == REGISTROS_BATCH) {
//            p.executeBatch();
//            counter = 0;
//        }
//        //revisamos si todavía hay sentencias pendientes de ejecutar
//        if (counter > 0) {
//            p.executeBatch();
//        }
//
//
//        p.executeUpdate();      // Se ejecuta la inserción.
        return Response.ok("ok").header("Access-Control-Allow-Origin", "*").build();
    }
}
