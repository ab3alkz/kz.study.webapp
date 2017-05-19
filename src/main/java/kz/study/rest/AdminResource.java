package kz.study.rest;

import kz.study.gson.GsonAdminValue;
import kz.study.session.AdminSession;
import kz.study.session.AnalizeSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import static kz.study.util.Util.objectToJson;
import static kz.study.wrapper.Wrapper.wrapToGsonAdminValue;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
@Stateless
@Path("admin")
public class AdminResource {

    @Context
    SecurityContext sc;
    @Context
    private HttpServletRequest request;
    @EJB
    private
    AdminSession adminSession;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @POST
    @Produces("application/json")
    @Path("addLessonByPart")
    public String addLessonByPart(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addLessonByPart(gson));
    }

    @POST
    @Produces("application/json")
    @Path("addVideoLessonByPart")
    public String addVideoLessonByPart(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addVideoLessonByPart(gson));
    }

    @POST
    @Produces("application/json")
    @Path("addAudioLessonByPart")
    public String addAudioLessonByPart(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addAudioLessonByPart(gson));
    }

    @POST
    @Produces("application/json")
    @Path("addGrammarLessonByPart")
    public String addGrammarLessonByPart(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addGrammarLessonByPart(gson));
    }

    @POST
    @Path("addImgToAudioLessonByPart")
    @Produces("image/jpeg")
    public Response uploadUserAva(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        Response.ResponseBuilder response = Response.ok(adminSession.addImgToAudioLessonByPart(gson, request, sc.getUserPrincipal().getName()));
        response.header("Content-Disposition", "filename=doc.jpg");
        return response.build();
    }

    @GET
    @Produces("application/json")
    @Path("getImageById")
    public String getImageById(@QueryParam("id") String id) {
        return objectToJson(adminSession.getImageById(id));
    }

    @GET
    @Produces("application/json")
    @Path("editAdmin")
    public String editAdmin(@QueryParam("param") String param) {
        return objectToJson(adminSession.editAdmin(param));
    }

    @POST
    @Produces("application/json")
    @Path("addDataAntOrSynonym")
    public String addDataAntOrSynonym(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addDataAntOrSynonym(gson));
    }

    @POST
    @Produces("application/json")
    @Path("addDataDGameW")
    public String addDataDGameW(MultivaluedMap<String, String> formParams) {
        GsonAdminValue gson = wrapToGsonAdminValue(formParams);
        return objectToJson(adminSession.addDataDGameW(gson));
    }
}