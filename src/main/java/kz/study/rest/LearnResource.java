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
    public String beforeCreateApp() {
        return objectToJson(learnSession.getAllLeters());
    }

    @GET
    @Produces("application/json")
    @Path("getVideoById")
    public String getVideoById(@QueryParam("id") final int id) {
        return objectToJson(learnSession.getVideoById(id));
    }
}