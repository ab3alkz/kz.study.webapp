/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bekzhan
 */
public class GsonGenerator {

    private static Boolean isNullOrEmpty(String value) {
        return (value == null || value.trim().isEmpty());
    }

    private static String getFirstUpper(String value) {
        if (!isNullOrEmpty(value)) {
            return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
        }
        return null;
    }

    private static Boolean isSystemField(String fieldName) {
        if (fieldName.indexOf("_persistence") == 0) {
            return true;
        }

        if (fieldName.equals("serialVersionUID")) {
            return true;
        }

        return false;
    }

    private static String getSpace(int cnt) {
        String space = "";

        for (int i = 0; i < cnt; i++) {
            space = space + " ";
        }

        return space;
    }

    private static String getNewLine(int cnt) {
        String line = "";

        for (int i = 0; i < cnt; i++) {
            line = line + "\n";
        }

        return line;
    }

    private static String getGsonListType(Field field) {
        String listFieldClass = field.getGenericType().toString();
        String fieldType = listFieldClass.substring(listFieldClass.lastIndexOf('.') + 1).replace(">", "");
        return "List<Gson" + fieldType + ">";
    }

    private static String getGsonFieldType(Field field) {

        String fieldType = field.getType().getSimpleName();

        switch (fieldType) {
            case "Short":
            case "short":
                return "Short";
            case "Serializable":
            case "byte[]":
                return "byte[]";
            case "BigDecimal":
                return "BigDecimal";
            case "BigInteger":
                return "BigInteger";
            case "Integer":
                return "Integer";
            case "long":
            case "Long":
                return "Long";
            case "Date":
            case "String":
                return "String";
            case "Character":
                return "Character";
            case "Boolean":
                return "Boolean";
            case "List":
                return getGsonListType(field);
            default:
                return "Gson" + fieldType;
        }
    }

    public static String getGsonClass(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Map<String, String> fieldList = new HashMap<>();
        StringBuilder sBuffer = new StringBuilder();
        String className = obj.getClass().getSimpleName();

        sBuffer.append(String.format("public class Gson%1$2s {", className));
        sBuffer.append(getNewLine(2));

        for (Field field : obj.getClass().getDeclaredFields()) {

            String fieldName = field.getName();

            if (fieldName.indexOf("_persistence") != 0 && !fieldName.equals("serialVersionUID")) {
                String fielTypeGson = getGsonFieldType(field);
                String propertyLine = getSpace(4) + "private " + fielTypeGson + " " + fieldName + ";";
                sBuffer.append(propertyLine);
                sBuffer.append(getNewLine(1));
                fieldList.put(fieldName, fielTypeGson);
            }
        }

        // getter setter
        for (Map.Entry<String, String> entry : fieldList.entrySet()) {
            String getterSetter
                    = getSpace(4) + "public %1$2s get%2$2s() { \n"
                    + getSpace(8) + "return %3$2s; \n"
                    + getSpace(4) + "} \n"
                    + "\n"
                    + getSpace(4) + "public void set%2$2s(%1$2s %3$2s) {\n"
                    + getSpace(8) + "this.%3$2s = %3$2s;\n"
                    + getSpace(4) + "}";

            getterSetter = String.format(getterSetter, entry.getValue(), getFirstUpper(entry.getKey()), entry.getKey());

            sBuffer.append(getNewLine(1));
            sBuffer.append(getterSetter);
            sBuffer.append(getNewLine(1));
        }

        sBuffer.append(getNewLine(1));
        sBuffer.append("}");

        return sBuffer.toString();
    }

    public static String getGsonWrapper(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Map<String, String> fieldList = new HashMap<>();
        StringBuilder sBuffer = new StringBuilder();
        String className = obj.getClass().getSimpleName();

        for (Field field : obj.getClass().getDeclaredFields()) {
            String fieldName = field.getName();

            if (!isSystemField(fieldName)) {
                String fielTypeGson = getGsonFieldType(field);
                fieldList.put(fieldName, fielTypeGson);
            }
        }

        sBuffer.append(String.format("public static Gson%1$2s wrapToGson%1$2s(%1$2s obj) {", className));
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("if (obj != null) {");
        sBuffer.append(getNewLine(1));
        sBuffer.append(String.format(getSpace(8) + "Gson%1$2s gson = new Gson%1$2s();", className));
        sBuffer.append(getNewLine(1));

        for (Map.Entry<String, String> entry : fieldList.entrySet()) {

            String wrapSetter;

            if (entry.getValue().indexOf("Gson") == 0) {
                wrapSetter = getSpace(8) + "gson.set%1$2s(wrapTo%2$2s(obj.get%1$2s()));";
                wrapSetter = String.format(wrapSetter, getFirstUpper(entry.getKey()), entry.getValue());
            } else {
                wrapSetter = getSpace(8) + "gson.set%1$2s(obj.get%1$2s());";
                wrapSetter = String.format(wrapSetter, getFirstUpper(entry.getKey()));
            }

            sBuffer.append(wrapSetter);
            sBuffer.append(getNewLine(1));
        }

        sBuffer.append(getSpace(8)).append("return gson;");
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("}");
        sBuffer.append(getNewLine(1));
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("return null;");
        sBuffer.append(getNewLine(1));
        sBuffer.append("}");

        return sBuffer.toString();
    }

    public static String getGsonWrapperList(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sBuffer = new StringBuilder();
        String className = obj.getClass().getSimpleName();

        String wrapperText = 
                "public static List<Gson%1$2s> wrapToGson%1$2sList(List<%1$2s> list) {\n"
                + getSpace(4) + "List<Gson%1$2s> gsonList = new ArrayList<>();        \n"
                + getSpace(4) +  "for (%1$2s o : list) {\n"
                + getSpace(8) +  "gsonList.add(wrapToGson%1$2s(o));\n"
                + getSpace(4) +  "}\n"
                + getSpace(4) +  "return gsonList;\n"
                + "}";

        
        sBuffer.append(String.format(wrapperText, className));        
        return sBuffer.toString();
    }

    public static String generateGsonClasses(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sBuffer = new StringBuilder();
        sBuffer.append(getGsonClass(obj));
        sBuffer.append(getNewLine(2));
        sBuffer.append(getGsonWrapper(obj));
        sBuffer.append(getNewLine(2));
        sBuffer.append(getGsonWrapperList(obj));        
        return sBuffer.toString();
    }
}
