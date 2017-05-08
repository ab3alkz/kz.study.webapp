/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.session.LearnSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import static kz.study.util.Util.objectToJson;

/*
* REST Web Service
* @author a.beljerin
*/
@Stateless
@Path("lrn")
public class LearnResource {

    @Context
    private HttpServletRequest request;

    @Context
    SecurityContext sc;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @EJB
    private
    LearnSession learnSession;

    @GET
    @Produces("application/json")
    @Path("getAllLetters")
    public String beforeCreateApp(@QueryParam("id") final int id) {
        return objectToJson(learnSession.getAllLeters(id));
    }

    @GET
    @Produces("application/json")
    @Path("getVideoById")
    public String getVideoById(@QueryParam("id") final int id) {
        return objectToJson(learnSession.getVideoById(id));
    }

    @GET
    @Produces("application/json")
    @Path("getAudioById")
    public String getAudioById(@QueryParam("id") final int id) {
        return objectToJson(learnSession.getAudioById(id));
    }

    @GET
    @Produces("application/json")
    @Path("getLessonValue")
    public String getLessonValue(@QueryParam("part") final String part) {
        return objectToJson(learnSession.getLessonValue(part));
    }

    @GET
    @Produces("application/json")
    @Path("getVideoFormById")
    public String getVideoFormById(@QueryParam("param") final String param) {
        return objectToJson(learnSession.getVideoFormById(param));
    }

    @GET
    @Produces("application/json")
    @Path("getAudioFormById")
    public String getAudioFormById(@QueryParam("param") final String param) {
        return objectToJson(learnSession.getAudioFormById(param));
    }
}