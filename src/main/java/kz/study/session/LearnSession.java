package kz.study.session;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import kz.study.entity.AlphLinks;
import kz.study.entity.AudioLessons;
import kz.study.entity.VideoLessons;
import kz.study.gson.GsonAllDic;
import kz.study.gson.GsonDatatableData;
import kz.study.gson.GsonResult;
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

    @PersistenceContext(unitName = "study")
    private EntityManager em;


    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 10;

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