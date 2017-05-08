/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.rest;


import kz.study.session.DictionarySession;
import kz.study.session.LearnSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import static kz.study.util.Util.isNullOrEmpty;
import static kz.study.util.Util.objectToJson;

/*
* REST Web Service
* @author a.beljerin
*/
@Stateless
@Path("dic")
public class DictionaryResource {

    @Context
    SecurityContext sc;
    @Context
    private HttpServletRequest request;
    @EJB
    private
    DictionarySession dictionarySession;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @GET
    @Produces("application/json")
    public String load(@QueryParam("name") String dicName, @QueryParam("newV") String newV) {
        return objectToJson(dictionarySession.getDParentList(dicName, newV));
    }
}