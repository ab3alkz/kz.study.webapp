package kz.study.interfaces;

import kz.study.entity.DGameWordAnswer;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DGameWordAnswerFactory<P extends DGameWordAnswer> {
    P create(String id, String var1, String var2, String var3, String var4);
}