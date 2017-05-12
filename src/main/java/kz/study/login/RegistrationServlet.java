package kz.study.login;

import kz.study.session.UserSession;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static kz.study.util.Util.getResultGsonString;
import static kz.study.util.Util.objectToJson;


/**
 * @author a.amanzhol
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);

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

                String json = request.getParameter("json");
                out.print(objectToJson(userSession.registration(json)));


            } catch (Exception e) {
                logger.error("error", e);
                out.print(getResultGsonString(Boolean.FALSE, "Ошибка регистраций"));
            }
        }
    }

}
