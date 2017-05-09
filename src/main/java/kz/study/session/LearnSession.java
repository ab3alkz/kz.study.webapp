package kz.study.session;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import kz.study.entity.*;
import kz.study.gson.*;
import kz.study.lang.Lang;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.getSingleResultOrNull;
import static kz.study.wrapper.Wrapper.*;

/**
 * @author beljerin
 */
@Stateless
public class LearnSession extends Utx {

    private static final Logger LOGGER = LoggerFactory.getLogger(LearnSession.class);
    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 10;
    public static Lang language = Lang.valueOf("Ru");

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    public GsonResult getLessonValue(String part) {
        String id = null;
        switch (part) {
            case "A1":
                id = "1";
                break;
            case "A2":
                id = "2";
                break;
            case "B1":
                id = "3";
                break;
            case "B2":
                id = "4";
                break;
            case "C1":
                id = "5";
                break;
        }
        List<GsonAllDic> l =
                wrapToGsonAllDicList(em.createNamedQuery("DLesson.findByDUrovenId").setParameter("id", new DUroven(id)).getResultList());
        return getGsonResult(Boolean.TRUE, l);
    }

    public GsonResult getVideoFormById(String param) {
        try {
            List<GsonAdminValue> videoLessons =
                    wrapToDVideoLessonList(em.createNamedQuery("DVideoLesson.findByDUrovenId")
                            .setParameter("id", new DLesson(param)).getResultList());

            return getGsonResult(Boolean.TRUE, videoLessons);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }

    public GsonResult getAudioFormById(String id) {
        try {
            List<GsonAdminValue> list =
                    wrapToDAudioLessonList(em.createNamedQuery("DAudioLesson.findByDUrovenId")
                            .setParameter("id", new DLesson(id)).getResultList());
            return getGsonResult(Boolean.TRUE, list);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }

    public GsonResult getGrammarFormById(String id) {
        try {
            List<GsonAdminValue> list =
                    wrapToDGrammarLessonList(em.createNamedQuery("DGrammarLesson.findByDUrovenId")
                            .setParameter("id", new DLesson(id)).getResultList());
            return getGsonResult(Boolean.TRUE, list);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }

    /*------------------------------------------------------------------------------*/

    public GsonResult getAllLeters(final int id) {
        try {
            AlphLinks alphLinks = (AlphLinks) getSingleResultOrNull(
                    em.createNamedQuery("AlphLinks.findById").setParameter("id", id)
            );
            return getGsonResult(Boolean.TRUE, wrapToGsonAlphLinks(alphLinks));
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }

    public GsonResult getVideoById(int id) {
        try {
            VideoLessons videoLessons = (VideoLessons) getSingleResultOrNull(
                    em.createNamedQuery("VideoLessons.findById").setParameter("id", id));
            GsonAllDic gsonAllDic = wrapToGsonVideoLessons(videoLessons);
            return getGsonResult(Boolean.TRUE, gsonAllDic);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }

    public GsonResult getAudioById(int id) {
        try {
            List<AudioLessons> audioLessons =
                    em.createNamedQuery("AudioLessons.findByParamId").setParameter("paramId", id).getResultList();
            List<GsonAllDic> list = wrapToGsonAudioLessonsList(audioLessons);
            return getGsonResult(Boolean.TRUE, list);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return null;
    }
}