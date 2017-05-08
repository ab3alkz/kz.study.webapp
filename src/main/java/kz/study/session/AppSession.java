/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.session;

import kz.study.entity.*;
import kz.study.gson.*;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static kz.study.util.DateUtil.dateToString;
import static kz.study.util.DateUtil.stringToSqlDate;
import static kz.study.util.Util.*;
import static kz.study.wrapper.Serialization.*;
import static kz.study.wrapper.Wrapper.*;


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
        for (int i = 0; i < cnt; i++) {
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

    public List<GsonTestQuestions> getRandom25Guestions(Integer srcId, Integer start, Integer count, String lang) {
        List<TestQuestions> list = getTestQuestionsList(srcId, 0, 100);

        List<GsonTestQuestions> result = new ArrayList<>();
        randList = new ArrayList<>();
        Integer cnt = 20 > list.size() - 1 ? list.size() - 1 : 20;
        for (int i = 0; i < cnt; i++) {
            Integer randIdx = getRandIdxWord(0, list.size() - 1);
            randList.add(randIdx);
            result.add(wrapToGsonTestQuestions(list.get(randIdx), lang));
        }

        /*for (int i = 1; i <= 55; i++) {
            TestQuestions t = new TestQuestions(getSequenceNextVal(), i + " Lorem Ipsum is simply dummy ?", "text of the printing ",
                    " typesetting industry", "standard dummy text", "ever since the 1500s", 2);

            result.add(t);
        }*/
        return result;
    }

    public GsonDatatableData getTestingListById(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 10;
        GsonDatatableData data = new GsonDatatableData();
        data.setData(getTestQuestionsList(srcId, start, count));
        data.setPos(start);
        data.setTotal_count(getTestQuestionsCount(srcId).intValue());
        return data;
    }

    private Integer getRandIdxWord(Integer minimum, Integer maximum) {
        Integer rand = getRandom(minimum, maximum);
        if (randList.contains(rand)) {
            return getRandIdxWord(minimum, maximum);
        }
        return rand;
    }

    private static Integer getRandom(Integer minimum, Integer maximum) {
        Random r = new Random();
        return r.nextInt((maximum - minimum) + 1) + minimum;
    }

    public List<Words> getTestTypeList() {
        return em.createNamedQuery("TestType.findAll").getResultList();
    }

    public List<TestType> getTestTypeListByIsPublic(Integer isPublic, String lang) {
        return wrapToGsonTestTypeList(em.createNamedQuery("TestType.findByIsPublic").setParameter("isPublic", isPublic).getResultList(), lang);
    }

    public List<GameResult> getGameResultList() {
        return wrapToGsonGameResultList(em.createNamedQuery("GameResult.findAll").setFirstResult(0).setMaxResults(10).getResultList());
    }

    public List<TestQuestions> getTestQuestionsList(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 12;

        return em.createNamedQuery("TestQuestions.findBySrcId").setParameter("srcId", srcId).setFirstResult(start).setMaxResults(count).getResultList();
    }

    public Long getTestQuestionsCount(Integer srcId) {
        return (Long) getSingleResultOrNull(em.createQuery("SELECT count(g) FROM TestQuestions g WHERE g.srcId = :srcId").setParameter("srcId", srcId));
    }

    public Integer getSequenceNextVal() {
        ArmaSequense sequense = (ArmaSequense) getSingleResultOrNull(em.createNamedQuery("ArmaSequense.findVal"));
        if (sequense == null) {
            sequense = new ArmaSequense(1, 0);
        }
        sequense.setVal(sequense.getVal() + 1);
        em.merge(sequense);

        return sequense.getVal();
    }

    public GsonResult setGameResult(Integer gameId, String type, String uName, Long result, String json) {

        GameResult obj = new GameResult();
        obj.setId(getSequenceNextVal());
        obj.setGameId(new TestType(gameId));
        obj.setResult(result);
        obj.setuName(new Users(uName));
        obj.setInfo(json);
        obj.setgDate(stringToSqlDate(dateToString(new java.util.Date(), "dd.MM.yyyy HH.mm.ss"), "dd.MM.yyyy HH.mm.ss"));
        em.merge(obj);

        switch (type) {
            case "intelectualTest":
                GsonIntelectualQuestion gson = wrapToGsonIntelectualQuestionByJsonString(json);
                return intelectualQuestionValidate(gson);
        }
        return getGsonResult(true, null);
    }

    public GsonResult removeQuestionById(Integer id) {
        Query q = em.createQuery("delete  FROM TestQuestions g WHERE g.id = :id").setParameter("id", id);
        q.executeUpdate();
        return getGsonResult(true, null);
    }

    public GsonResult saveQuestion(String json) {
        try {
            GsonTestQuestions gson = wrapToGsonTestQuestionsByJsonString(json);
            TestQuestions questions = new TestQuestions();
            questions.setAnsw1(gson.getAnsw1());
            questions.setAnsw2(gson.getAnsw2());
            questions.setAnsw3(gson.getAnsw3());
            questions.setAnsw4(gson.getAnsw4());
            questions.setQuestion(gson.getQuestion());
            questions.setSrcId(gson.getSrcId());

            if (isNullOrEmpty(gson.getId())) {
                questions.setId(getSequenceNextVal());
            } else {
                questions.setId(Integer.parseInt(gson.getId()));
            }
            em.merge(questions);
            return getGsonResult(true, questions);
        } catch (Exception e) {
            e.printStackTrace();
            return getGsonResult(false, e.toString());
        }
    }

    public GsonResult saveTestType(String json) {
        try {
            GsonTestType gson = wrapToGsonTestTypeByJsonString(json);
            TestType testType = new TestType();
            testType.setId(getSequenceNextVal());
            testType.setType(gson.getType());
            testType.setIsPublic(1);
            testType.setName(gson.getName());
            testType.setLevel(0);
            em.merge(testType);
            return getGsonResult(true, testType);
        } catch (Exception e) {
            e.printStackTrace();
            return getGsonResult(false, e.toString());
        }
    }

    public List<GsonIntelectualQuestion> getIntellectualGuestions(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 20;
        return wrapToGsonIntelectualQuestionList(
                em.createNamedQuery("IntelectualQuestion.findBySrcId")
                        .setParameter("srcId", srcId)
                        .setFirstResult(start)
                        .setMaxResults(count)
                        .getResultList());
    }

    private GsonResult intelectualQuestionValidate(GsonIntelectualQuestion gson) {
        try {
            for (GsonIntelectualQuestion obj : gson.getData()) {
                String userAnsw = obj.getAnsw();
                IntelectualQuestionAnswers answerDB = (IntelectualQuestionAnswers)
                        getSingleResultOrNull(em.createNamedQuery("IntelectualQuestionAnswers.findByQuestionId")
                                .setParameter("questionId", obj.getId()));
                if (answerDB == null || isNullOrEmpty(answerDB.getAnswer())) {
                    obj.setDbAnsw("деректер қорында жауап берілмеген");
                } else {

                    obj.setDbAnsw(answerDB.getAnswer());
                    if(isNullOrEmpty(userAnsw)) {
                        obj.setResult(getGsonResult(false,"Сіз жауап бермедіңіз"));
                    }else {
                        obj.setResult(isIntelectualQuestionValidate(userAnsw, answerDB.getAnswer()));
                    }
                }

            }
            return getGsonResult(true, gson);

        } catch (Exception e) {

            return getGsonResult(false, e.toString());
        }
    }

    private GsonResult isIntelectualQuestionValidate(String userAnsw, String dBAnswer) throws Exception {
        userAnsw = getReplaceSpecialChars(userAnsw);
        dBAnswer = getReplaceSpecialChars(dBAnswer);
        userAnsw = userAnsw.trim();
        dBAnswer = dBAnswer.trim();

        if (isNullOrEmpty(dBAnswer)) {
            return getGsonResult(false, getGsonResult(false, "Деректер қорында жауап жазылмаған"));
        }
        if (isNullOrEmpty(userAnsw)) {
            return getGsonResult(false, getGsonResult(true, 0));
        }
        if (userAnsw.equals(dBAnswer)) {
            return getGsonResult(true, getGsonResult(true, 100));
        }

        String[] dBAnswerSentenceArr = dBAnswer.split("\\.");
        String[] uAnswerSentenceArr = userAnsw.split("\\.");
        Integer containsSentences = 0;
        for (String dBAnswerSentence : dBAnswerSentenceArr) {
            if (stringContainsItemFromList(dBAnswerSentence, uAnswerSentenceArr)) {
                containsSentences++;
            }
        }
        String[] dBAnswerWordsArr = dBAnswer.split(" ");
        String[] uAnswerWordsArr = userAnsw.split(" ");


        Integer containsWords = 0;
        for (String dBAnswerWord : dBAnswerWordsArr) {
            if (stringContainsItemFromList(dBAnswerWord, uAnswerWordsArr)) {
                containsWords++;
            }
        }

        return getGsonResult(false,
                "предложений " + dBAnswerSentenceArr.length + " совпадений <" + containsSentences + ">   из " + uAnswerSentenceArr.length +
                        getNewLine() + "слов  " + dBAnswerWordsArr.length + " совпадений <" + containsWords + "> из " + uAnswerWordsArr.length);
    }

    private String getNewLine() {
        return "<br />";
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (inputStr.trim().toLowerCase().contains(items[i].trim().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String getReplaceSpecialChars(String str) {
        return str
                .replace("                    ", " ")
                .replace("                   ", " ")
                .replace("                  ", " ")
                .replace("                 ", " ")
                .replace("                ", " ")
                .replace("               ", " ")
                .replace("              ", " ")
                .replace("             ", " ")
                .replace("            ", " ")
                .replace("           ", " ")
                .replace("          ", " ")
                .replace("         ", " ")
                .replace("        ", " ")
                .replace("       ", " ")
                .replace("      ", " ")
                .replace("     ", " ")
                .replace("    ", " ")
                .replace("   ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("-", "")
                .replace("...", ".")
                .replace("..", ".")
                .replace(",,", ",")
                .replace(", ", ",")
                .replace("???", "?")
                .replace("??", "?")
                .replace("!!!", "!")
                .replace("!!", "!")
                .replace("<", "")
                .replace(">", "")
                .replace("'", "")
                .replace("\"", "")
                .replace("/", "")
                .replace("+", "")
                .replace("\n", "")
                .replace("*", "")
                .replace("`", "")
                .replace("`", "");

    }


    public List<DTestType> getDTestTypeList() {
        return em.createNamedQuery("DTestType.findAll").getResultList();
    }
}
