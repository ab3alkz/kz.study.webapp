/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.entity.TestQuestions;
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
    SecurityContext sc;
    @Context
    private HttpServletRequest request;
    @EJB
    private
    AppSession appSession;

    @GET
    @Path("changeLang")
    public String changeLang(@QueryParam("lang") String lang) {
        return objectToJson(appSession.changeLang(lang));
    }

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

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
        return objectToJson(appSession.setGameResult(gameId, type, uName, result, json));
    }

    @GET
    @Produces("application/json")
    @Path("getTestTypeList")
    public String getTestTypeList(@QueryParam("isPublic") Integer isPublic, @QueryParam("lang") String lang) {
        return objectToJson(appSession.getTestTypeListByIsPublic(isPublic, lang));
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
    public String getRandom25Guestions(@QueryParam("srcId") Integer srcId,
                                       @QueryParam("start") Integer start,
                                       @QueryParam("count") Integer count,
                                       @QueryParam("lang") String lang) {
        return objectToJson(appSession.getRandom25Guestions(srcId, start, count, lang));
    }

    @GET
    @Produces("application/json")
    @Path("getRandom25determineGuestions")
    public String getRandom25determineGuestions(
            @QueryParam("start") Integer start,
            @QueryParam("count") Integer count,
            @QueryParam("lang") String lang) {
        return objectToJson(appSession.getRandom25determineGuestions(start, count, lang));
    }

    @GET
    @Produces("application/json")
    @Path("getTestingListById")
    public String getTestingListById(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getTestingListById(srcId, start, count));
    }

    @GET
    @Produces("application/json")
    @Path("getAudiTestingListBySrcId")
    public String getAudiTestingListBySrcId(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getAudiTestingListBySrcId(srcId, start, count));
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


    @GET
    @Produces("application/json")
    @Path("removeAudiQuestionById")
    public String removeAudiQuestionById(@QueryParam("id") Integer id) {
        return objectToJson(appSession.removeAudiQuestionById(id));
    }


    @POST
    @Produces("application/json")
    @Path("saveAudiQuestion")
    public String saveAudiQuestion(String json) {
        return objectToJson(appSession.saveAudiQuestion(json));
    }


    @POST
    @Produces("application/json")
    @Path("saveTestType")
    public String saveTestType(String json) {
        return objectToJson(appSession.saveTestType(json));
    }


    @POST
    @Produces("application/json")
    @Path("saveLevel")
    public String saveLevel(String json) {
        return objectToJson(appSession.saveLevel(json));
    }


    @GET
    @Produces("application/json")
    @Path("getIntellectualGuestions")
    public String getIntellectualGuestions(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getIntellectualGuestionsList(srcId, start, count));
    }

    @GET
    @Produces("application/json")
    @Path("getGsonClass")
    public String getGsonClass() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        TestQuestions obj = new TestQuestions();
        return GsonGenerator.generateGsonClasses(obj);
    }

    @GET
    @Produces("application/json")
    @Path("getDTestTypeList")
    public String getDTestTypeList() {
        return objectToJson(appSession.getDTestTypeList());
    }


    @GET
    @Produces("application/json")
    @Path("getIntellectTestingListById")
    public String getIntellectTestingListById(@QueryParam("srcId") Integer srcId, @QueryParam("start") Integer start, @QueryParam("count") Integer count) {
        return objectToJson(appSession.getIntellectTestingListById(srcId, start, count));
    }


    @POST
    @Produces("application/json")
    @Path("saveIntellectQuestion")
    public String saveIntellectQuestion(String json) {
        return objectToJson(appSession.saveIntellectQuestion(json));
    }


    @GET
    @Produces("application/json")
    @Path("removeIntellectualQuestionById")
    public String removeIntellectualQuestionById(@QueryParam("id") Integer id) {
        return objectToJson(appSession.removeIntellectualQuestionById(id));
    }

    @GET
    @Produces("application/json")
    @Path("getUrovenList")
    public String getUrovenList() {
        return objectToJson(appSession.getUrovenList());
    }


    @GET
    @Produces("application/json")
    @Path("getRandom10AudiList")
    public String getRandom10AudiList(@QueryParam("srcId") Integer srcId) {
        return objectToJson(appSession.getRandom10AudiList(srcId));
    }

}
