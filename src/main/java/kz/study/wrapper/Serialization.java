/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.wrapper;

import com.google.gson.Gson;
import kz.study.gson.*;

/**
 *
 * @author a.amanzhol
 */
public class Serialization {

    public static GsonUserDetail wrapToGsonUserDetailByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonUserDetail.class);
    }

    public static GsonRegistration wrapToGsonRegistrationByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonRegistration.class);
    }

    public static GsonFillWordsResult wrapToGsonFillWordsResultByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonFillWordsResult.class);
    }

    public static GsonTestQuestions wrapToGsonTestQuestionsByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonTestQuestions.class);
    }
    public static GsonWords wrapToGsonWordsByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonWords.class);
    }
    public static GsonAudiTest wrapToGsonAudiTestByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonAudiTest.class);
    }

    public static GsonIntelectualQuestion wrapToGsonIntelectualQuestionByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonIntelectualQuestion.class);
    }

    public static GsonTestType wrapToGsonTestTypeByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonTestType.class);
    }
}
