package com.sipre.util;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("app")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.getList.InsertListcat.class);
        resources.add(com.getList.JSONList.class);
        resources.add(com.sipre.elemprestados.ElemprestadoJSON.class);
        resources.add(com.sipre.elemprestados.InsertElemprestados.class);
        resources.add(com.sipre.formaccioninvestig.FormaccioninvestigJSON.class);
        resources.add(com.sipre.formaccioninvestig.InsertFormaccioninvestig.class);
        resources.add(com.sipre.forminvestigacion.FormInvestigacionJSON.class);
        resources.add(com.sipre.forminvestigacion.InsertFormInvest.class);
        resources.add(com.sipre.formsbase.FormsBaseJSON.class);
        resources.add(com.sipre.formsbase.InsertFormsBase.class);
        resources.add(com.sipre.process.InsertProcess.class);
        resources.add(com.sipre.process.ProcessJSON.class);
        resources.add(com.sipre.quiz.InsertQuiz.class);
        resources.add(com.sipre.quiz.QuizJSON.class);
        resources.add(com.sipre.usersecure.InsertUsersecure.class);
        resources.add(com.sipre.usersecure.UsersecureJSON.class);
    }
    
}