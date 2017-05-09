package kz.study.session;

import kz.study.entity.*;
import kz.study.gson.GsonAdminValue;
import kz.study.gson.GsonResult;
import kz.study.interfaces.DAudioLessonFactory;
import kz.study.interfaces.DLessonFactory;
import kz.study.interfaces.DVideoLessonFactory;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;

import static kz.study.util.Util.createGuid;
import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.getSingleResultOrNull;

/**
 * @author beljerin
 */
@Stateless
public class AdminSession extends Utx {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    public GsonResult addLessonByPart(final GsonAdminValue gson) {
        try {
            DLessonFactory<DLesson> aNew = DLesson::new;
            DLesson dLesson = aNew.create(createGuid(), gson.getNameRus(), gson.getNameKaz(), gson.getNameLan(), new DUroven(gson.getParamId()));

            em.persist(dLesson);
            em.flush();

            return getGsonResult(Boolean.TRUE, "Сохранено");

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public GsonResult addVideoLessonByPart(final GsonAdminValue gson) {
        try {
            DVideoLessonFactory<DVideoLesson> aNew = DVideoLesson::new;
            DVideoLesson dVideoLesson = aNew.create(createGuid(), gson.getNameRus(), gson.getNameKaz(), gson.getNameLan(),
                    gson.getLink(), gson.getDescRus(), gson.getDescKaz(), gson.getDescLan(), new DLesson(gson.getParamId()));

            em.persist(dVideoLesson);
            em.flush();

            return getGsonResult(Boolean.TRUE, "Сохранено");

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public GsonResult addAudioLessonByPart(final GsonAdminValue gson) {
        try {
            String id = createGuid();

            DAudioLessonFactory<DAudioLesson> aNew = DAudioLesson::new;
            DAudioLesson dAudioLesson = aNew.create(id, gson.getNameRus(), gson.getNameKaz(), gson.getNameLan(),
                    gson.getLink(), gson.getDescRus(), gson.getDescKaz(), gson.getDescLan(), new DLesson(gson.getParamId()));

            em.persist(dAudioLesson);
            em.flush();

            return getGsonResult(Boolean.TRUE, id);

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public byte[] addImgToAudioLessonByPart(final GsonAdminValue gson, HttpServletRequest request, String uName) {
        try {

            DAudioLessonImg audioLessonImg = new DAudioLessonImg();
            audioLessonImg.setId(createGuid());
            audioLessonImg.setdAudioLesson(new DAudioLesson(gson.getAudioLessonId()));
            audioLessonImg.setImg(gson.getImg().getBytes());
            audioLessonImg.setLink(gson.getAudioLink());


            em.persist(audioLessonImg);
            em.flush();

            Users user = getUserByUName(uName);

            setGsonUserSession(request, user);

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return null;
    }

    private Users getUserByUName(String uName) {
        return (Users) getSingleResultOrNull(em.createNamedQuery("Users.findByUName").setParameter("uName", uName));
    }

    private void setGsonUserSession(HttpServletRequest request, Users user) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", user);
    }

    public String getImageById(String id) {
        String ava = "";
        DAudioLessonImg obj = (DAudioLessonImg)
                getSingleResultOrNull(em.createNamedQuery("DAudioLessonImg.findById").setParameter("id", new DAudioLesson(id)));
        if (obj != null) {
            if (obj.getImg() != null) {
                ava = new String(obj.getImg(), StandardCharsets.UTF_8);
            }
        }
        return ava;
    }

}