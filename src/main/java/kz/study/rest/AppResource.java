/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.entity.TestType;
import kz.study.session.AppSession;
import kz.study.util.GsonGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.lang.reflect.InvocationTargetException;
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
    public String setGameResult(@QueryParam("gameId") Integer gameId,
                                @QueryParam("type") String type,
                                @QueryParam("uName") String uName,
                                @QueryParam("json") String json,
                                @QueryParam("result") Long result) {
        return objectToJson(appSession.setGameResult(gameId,type, uName, result, json));
    }

    @GET
    @Produces("application/json")
    @Path("getTestTypeList")
    public String getTestTypeList(@QueryParam("isPublic") Integer isPublic) {
        return objectToJson(appSession.getTestTypeListByIsPublic(isPublic));
    }

    @GET
    @Produces("application/json")
    @Path("getGameResultList")
    public String getGameResultList(@QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getGameResultList());
    }

    @GET
    @Produces("application/json")
    @Path("getRandom25Guestions")
    public String getRandom25Guestions(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getRandom25Guestions(srcId, start, count));
    }

    @GET
    @Produces("application/json")
    @Path("getTestingListById")
    public String getTestingListById(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getTestingListById(srcId, start, count));
    }

    @GET
    @Produces("application/json")
    @Path("removeQuestionById")
    public String removeQuestionById(@QueryParam("id") Integer id) {
        return objectToJson(appSession.removeQuestionById(id));
    }

    @POST
    @Produces("application/json")
    @Path("saveQuestion")
    public String saveQuestion(String json) {
        return objectToJson(appSession.saveQuestion(json));
    }


    @POST
    @Produces("application/json")
    @Path("saveTestType")
    public String saveTestType(String json) {
        return objectToJson(appSession.saveTestType(json));
    }


    @GET
    @Produces("application/json")
    @Path("getIntellectualGuestions")
    public String getIntellectualGuestions(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getIntellectualGuestions(srcId, start, count));
    }

    @GET
    @Produces("application/json")
    @Path("getGsonClass")
    public String getGsonClass() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        TestType obj = new TestType();
        return GsonGenerator.generateGsonClasses(obj);
    }

    @GET
    @Produces("application/json")
    @Path("getDTestTypeList")
    public String getDTestTypeList()  {
        return objectToJson(appSession.getDTestTypeList());
    }
}
