package kz.study.login;

import kz.study.gson.GsonUsers;
import kz.study.session.UserSession;
import org.apache.commons.codec.digest.Crypt;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static kz.study.util.Util.*;
import static kz.study.wrapper.Wrapper.wrapToGsonUsers;
import static org.eclipse.persistence.eis.EISConnectionSpec.USER;


/**
 * @author Bekzhan
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AuthServlet.class);

    private
    @EJB
    UserSession userSession;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {

                String j_username = request.getParameter("j_username");
                String j_password = request.getParameter("j_password");

                if (isNullOrEmpty(j_username) || isNullOrEmpty(j_password)) {
                    out.print(getResultGsonString(Boolean.FALSE, "Не все параметры заполнены"));
                }

                if (request.getUserPrincipal() != null) {
                    request.logout();
                }

                request.login(j_username, getMd5Apache(j_password));

                GsonUsers user = wrapToGsonUsers(userSession.getUser(j_username));

                if (user != null) {
                    HttpSession session = request.getSession(Boolean.TRUE);
                    session.setAttribute(USER, user);
                    out.print(getResultGsonString(Boolean.TRUE, j_username));
                }

            } catch (Exception e) {
                logger.error("error", e);
                out.print(getResultGsonString(Boolean.FALSE, "Ошибка авторизации"));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(Boolean.TRUE);
            session.removeAttribute(USER);
            request.logout();
            request.getSession().invalidate();

        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}