/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.session.UserSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;

import static kz.study.util.Util.objectToJson;

/*
* REST Web Service
* @author a.amanzhol
*/
@Stateless
@Path("user")
public class UserResource {

    @Context
    private HttpServletRequest request;

    @Context
    SecurityContext sc;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @EJB
    UserSession userSession;


    @POST
    @Produces("application/json")
    @Path("registration")
    public String registration(MultivaluedMap<String, String> formParams) {
        return objectToJson(userSession.registration(formParams));
    }
}
