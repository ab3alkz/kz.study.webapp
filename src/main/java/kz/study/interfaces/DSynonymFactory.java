package kz.study.interfaces;

import kz.study.entity.DSynonym;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DSynonymFactory<P extends DSynonym> {

    P create(String id, String value);
}