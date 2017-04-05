package kz.study.session;

import kz.study.entity.AlphLinks;
import kz.study.gson.GsonAllDic;
import kz.study.gson.GsonResult;
import kz.study.util.Utx;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.getSingleResultOrNull;
import static kz.study.wrapper.Wrapper.wrapToGsonAlphLinksList;

/**
 * @author beljerin
 */
@Stateless
public class LearnSession extends Utx {


    private static final Logger logger = Logger.getLogger(LearnSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;


    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 30;

    public GsonResult getAllLeters() {
        List<AlphLinks> alphLinks = (List<AlphLinks>) getSingleResultOrNull(em.createNamedQuery("AlphLinks.findAll"));
        GsonAllDic gsonAllDic = (GsonAllDic) wrapToGsonAlphLinksList(alphLinks);
        return getGsonResult(Boolean.TRUE, gsonAllDic);
    }
}