package kz.study.session;

import kz.study.entity.*;
import kz.study.gson.GsonAdminValue;
import kz.study.gson.GsonResult;
import kz.study.gson.GsonTreeDictionary;
import kz.study.interfaces.*;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static kz.study.util.Util.*;
import static kz.study.wrapper.Wrapper.*;

/**
 * @author beljerin
 */
@Stateless
public class AdminSession extends Utx {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    @PersistenceContext(unitName = "armaSecond")
    private EntityManager em2;

    public GsonResult addLessonByPart(final GsonAdminValue gson) {
        try {
            if (isNullOrEmpty(gson.getId())) {
                DLessonFactory<DLesson> aNew = DLesson::new;
                DLesson dLesson = aNew.create(createGuid(), gson.getNameRus(), gson.getNameKaz(), gson.getNameLan(), new DUroven(gson.getParamId()));

                em.persist(dLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Сохранено");
            } else {
                DLesson dLesson = new DLesson(gson.getId());
                dLesson.setNameRus(gson.getNameRus());
                dLesson.setNameKaz(gson.getNameKaz());
                dLesson.setNameLan(gson.getNameLan());
                dLesson.setdUroven(new DUroven(gson.getParamId()));
                em.merge(dLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Изменено");
            }

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public GsonResult addVideoLessonByPart(final GsonAdminValue gson) {
        try {
            if (gson.getId() == null) {
                DVideoLessonFactory<DVideoLesson> aNew = DVideoLesson::new;
                DVideoLesson dVideoLesson = aNew.create(createGuid(), gson.getNameRus(), gson.getNameKaz(), gson.getNameLan(),
                        gson.getLink(), gson.getDescRus(), gson.getDescKaz(), gson.getDescLan(), new DLesson(gson.getParamId()));

                em.persist(dVideoLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Сохранено");
            } else {
                DVideoLesson dLesson = new DVideoLesson(gson.getId());
                dLesson.setNameRus(gson.getNameRus());
                dLesson.setNameKaz(gson.getNameKaz());
                dLesson.setNameLan(gson.getNameLan());
                dLesson.setDescRus(gson.getDescRus());
                dLesson.setDescKaz(gson.getDescKaz());
                dLesson.setDescLan(gson.getDescLan());
                dLesson.setLink(gson.getLink());
                dLesson.setdLesson(new DLesson(gson.getParamId()));
                em.merge(dLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Изменено");
            }

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public GsonResult addAudioLessonByPart(final GsonAdminValue gson) {
        try {
            String id = createGuid();

            DAudioLessonFactory<DAudioLesson> aNew = DAudioLesson::new;
            DAudioLesson dAudioLesson = aNew.create(id, gson.getNameRus(), gson.getLink(),
                    gson.getDescKaz(), gson.getDescLan(), new DLesson(gson.getParamId()));

            em.persist(dAudioLesson);
            em.flush();

            return getGsonResult(Boolean.TRUE, id);

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    private Users getUserByUName(String uName) {
        return (Users) getSingleResultOrNull(em.createNamedQuery("Users.findByUName").setParameter("uName", uName));
    }

    private void setGsonUserSession(HttpServletRequest request, Users user) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", user);
    }

    public GsonResult addGrammarLessonByPart(final GsonAdminValue gson) {
        try {
            if (isNullOrEmpty(gson.getId())) {
                DGrammarLessonFactory<DGrammarLesson> aNew = DGrammarLesson::new;
                DGrammarLesson dGrammarLesson = aNew.create(createGuid(), gson.getNameRus(),
                        gson.getDescRus(), new DLesson(gson.getParamId()));

                em.persist(dGrammarLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Сохранено");
            } else {
                DGrammarLesson dLesson = new DGrammarLesson(gson.getId());
                dLesson.setNameRus(gson.getNameRus());
                dLesson.setDescRus(gson.getDescRus());
                dLesson.setdLessonId(new DLesson(gson.getParamId()));
                em.merge(dLesson);
                em.flush();

                return getGsonResult(Boolean.TRUE, "Изменено");
            }

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    public GsonResult editAdmin(final String param) {
        switch (param) {
            case "1":
                return getGsonResult(Boolean.TRUE, getDlessonData());
            case "2":
                return getGsonResult(Boolean.TRUE, getDVideoLessonData());
            case "3":
                return getGsonResult(Boolean.TRUE, getDGrammarData());
            case "4":
                return getGsonResult(Boolean.TRUE, getDAudioData());
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    private List<GsonTreeDictionary> getDlessonData() {
        return (List<GsonTreeDictionary>) wrapToGsonDLessonList(em.createNamedQuery("DLesson.findAll").getResultList());
    }

    private List<GsonTreeDictionary> getDVideoLessonData() {
        return (List<GsonTreeDictionary>) wrapToDVideoLessonEditList(em.createNamedQuery("DVideoLesson.findAll").getResultList());
    }

    private List<GsonTreeDictionary> getDGrammarData() {
        return (List<GsonTreeDictionary>) wrapToDGrammarLessonEditList(em.createNamedQuery("DGrammarLesson.findAll").getResultList());
    }

    private List<GsonTreeDictionary> getDAudioData() {
        return (List<GsonTreeDictionary>) wrapToDAudioLessonEditList(em.createNamedQuery("DAudioLesson.findAll").getResultList());
    }

    public GsonResult addDataAntOrSynonym(final GsonAdminValue gson) {
        try {
            switch (gson.getParamId()) {
                case "1":
                    return addAntonym(gson);
                case "2":
                    return addSynonym(gson);
            }

        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }
        return getGsonResult(Boolean.FALSE, "Ошибка");
    }

    private GsonResult addAntonym(final GsonAdminValue gson) {
        DAntonymFactory<DAntonym> aNew = DAntonym::new;
        DAntonym dAntonym = aNew.create(createGuid(), gson.getNameRus(), gson.getNameKaz());

        em2.persist(dAntonym);
        em2.flush();

        return getGsonResult(Boolean.TRUE, "Сохранено");
    }

    private GsonResult addSynonym(final GsonAdminValue gson) {
        String s = gson.getNameRus();
        s = s.replace(".","");
        s = s.replace(",","");
        s = s.replace("-","");
        DSynonymFactory<DSynonym> aNew = DSynonym::new;
        DSynonym dSynonym = aNew.create(createGuid(), s);

        em2.persist(dSynonym);
        em2.flush();

        return getGsonResult(Boolean.TRUE, "Сохранено");
    }

    public GsonResult addDataDGameW(GsonAdminValue gson) {
        String wordAnswerId = createGuid();
        String answer = "";

        if (gson.getCh_var1().equals("1")) {
            answer = gson.getVar1();
        } else if (gson.getCh_var2().equals("1")) {
            answer = gson.getVar2();
        } else if (gson.getCh_var3().equals("1")) {
            answer = gson.getVar3();
        } else if (gson.getCh_var4().equals("1")) {
            answer = gson.getVar4();
        } else {
            return getGsonResult(Boolean.FALSE, "Выберите правильный ответ");
        }

        DGameWordAnswerFactory<DGameWordAnswer> aNew = DGameWordAnswer::new;
        DGameWordAnswer dGameWordAnswer = aNew.create(wordAnswerId, gson.getVar1(), gson.getVar2(), gson.getVar3(), gson.getVar4());

        em.persist(dGameWordAnswer);
        em.flush();

        DGameWordFactory<DGameWord> factory = DGameWord::new;
        DGameWord dGameWord = factory.create(createGuid(), new DLesson(gson.getParamId()),
                gson.getQuestion(), new DGameWordAnswer(wordAnswerId), answer);

        em.persist(dGameWord);
        em.flush();

        return getGsonResult(Boolean.TRUE, "Сохранено");
    }

    public GsonResult addDataDGameWBrain(GsonAdminValue gson) {
        DGameBrain dGame = new DGameBrain();
        dGame.setId(createGuid());
        dGame.setDescRus(gson.getDescRus());
        dGame.setdLesson(new DLesson(gson.getParamId()));

        em.persist(dGame);
        em.flush();

        return getGsonResult(Boolean.TRUE, "Сохранено");
    }
}