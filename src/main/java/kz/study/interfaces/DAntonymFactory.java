package kz.study.interfaces;

import kz.study.entity.DAntonym;
import kz.study.entity.DGrammarLesson;
import kz.study.entity.DLesson;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DAntonymFactory<P extends DAntonym> {

    P create(String id, String value, String secValue);
}