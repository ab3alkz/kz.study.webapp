package kz.study.session;

import kz.study.entity.DLesson;
import kz.study.entity.DUroven;
import kz.study.gson.GsonTreeDictionary;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static kz.study.util.Util.isNullOrEmpty;
import static kz.study.wrapper.Wrapper.wrapToGsonDLessonList;
import static kz.study.wrapper.Wrapper.wrapToGsonDUrovenList;
import static kz.study.wrapper.Wrapper.wrapToGsonTreeDictionary;

@Stateless
public class DictionarySession extends Utx {


    private static final Logger LOGGER = LoggerFactory.getLogger(DictionarySession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    public List<GsonTreeDictionary> getDParentList(String dicName, String newV) {
        List<GsonTreeDictionary> list = new ArrayList<>();
        switch (dicName) {
            case "dUrovenList":
                List<DUroven> dUrovens = em.createNamedQuery("DUroven.findAll").getResultList();
                list = wrapToGsonDUrovenList(dUrovens);
                break;
            case "dParentList":
                if (!isNullOrEmpty(newV)) {
                    List<DLesson> dLessons = em.createNamedQuery("DLesson.findByDUrovenId")
                            .setParameter("id", new DUroven(newV)).getResultList();
                    list = wrapToGsonDLessonList(dLessons);
                }
                break;
        }

        return list;
    }
}