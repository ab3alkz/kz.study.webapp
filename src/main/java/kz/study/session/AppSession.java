/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.session;

import kz.study.entity.GameResult;
import kz.study.entity.Words;
import kz.study.gson.GsonResult;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static kz.study.util.Util.createGuid;
import static kz.study.util.Util.getGsonResult;


/**
 * @author a.amanzhol
 */
@Stateless
public class AppSession extends Utx {


    private static final Logger logger = LoggerFactory.getLogger(AppSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;


    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 30;

    private List<Integer> randList;

    public List<Words> getRandom10WordList() {
        List<Words> list = em.createNamedQuery("Words.findAll").setFirstResult(0).setMaxResults(100).getResultList();
        List<Words> result = new ArrayList<>();
        randList = new ArrayList<>();
        Integer cnt = 10 > list.size() - 1 ? list.size() - 1 : 10;
        for (int i = 0; i <= cnt; i++) {
            Integer randIdx = getRandIdxWord(0, list.size() - 1);
            randList.add(randIdx);
            result.add(list.get(randIdx));
        }
        return result;
        /*List<Words> wordsList = new ArrayList<>();
        int i =1;
        wordsList.add(new Words("a"+(i++),"Сәлеметсізбе"));
        wordsList.add(new Words("a"+(i++),"Жаз"));
        wordsList.add(new Words("a"+(i++),"Көктем"));
        wordsList.add(new Words("a"+(i++),"Күз"));
        wordsList.add(new Words("a"+(i++),"Әке"));
        wordsList.add(new Words("a"+(i++),"Қазақстан"));
        wordsList.add(new Words("a"+(i++),"Қыс"));
        wordsList.add(new Words("a"+(i++),"Балапан"));
        wordsList.add(new Words("a"+(i++),"Мысық"));
        wordsList.add(new Words("a"+(i++),"Қазақ"));
        return wordsList;*/
    }

    private Integer getRandIdxWord(Integer minimum, Integer maximum) {
        Integer rand = getRandom(minimum, maximum);
        if (randList.contains(rand)) {
            return getRandIdxWord(minimum, maximum);
        }
        return rand;
    }

    private static Integer getRandom(Integer minimum , Integer maximum) {
        Random r = new Random();
        return r.nextInt((maximum - minimum) + 1) + minimum;
    }

    public List<Words> getTestTypeList() {
        return em.createNamedQuery("TestType.findAll").getResultList();
    }

    public GsonResult setGameResult(String gameId, String uName, Long result) {
        GameResult obj = new GameResult();
        obj.setId(createGuid());
        obj.setGameId(gameId);
        obj.setResult(result);
        obj.setuName(uName);
        em.merge(obj);
        return getGsonResult(true, null);
    }
}
