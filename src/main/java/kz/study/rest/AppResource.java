/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.session.AppSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;

import static kz.study.util.Util.objectToJson;

/*
* REST Web Service
* @author a.amanzhol
*/
@Stateless
@Path("app")
public class AppResource {

    @Context
    private HttpServletRequest request;

    @Context
    SecurityContext sc;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @EJB
    AppSession appSession;

    @GET
    @Produces("application/json")
    @Path("beforeCreateApp")
    public String beforeCreateApp(@QueryParam("id") BigDecimal id, @QueryParam("personid") BigDecimal sicid) {
        return null;
    }

    @GET
    @Produces("application/json")
    @Path("getRandom10WordList")
    public String getRandom10WordList() {
        return objectToJson(appSession.getRandom10WordList());
    }

    @GET
    @Produces("application/json")
    @Path("setGameResult")
    public String setGameResult(@QueryParam("gameId") String gameId, @QueryParam("uName") String uName, @QueryParam("result") String result) {
        return objectToJson(appSession.setGameResult(gameId,uName,result));
    }

    @GET
    @Produces("application/json")
    @Path("getTestTypeList")
    public String getTestTypeList() {
        return objectToJson(appSession.getTestTypeList());
    }

}
