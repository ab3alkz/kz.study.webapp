package kz.study.util;

import javax.annotation.Resource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

/**
 * @author amanzhol-ak;
 * @since on 01.11.2016.
 */
public class Utx {

    @Resource
    private javax.transaction.UserTransaction utx;

    public void utxBegin() {
        // при вызове падает NotSupportedException, возможно он уже открыт
        try {
            utx.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
    }

    protected void utxRollback() {
        try {
            System.out.println("rollback successful");
            utx.rollback();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {

        }
    }
}