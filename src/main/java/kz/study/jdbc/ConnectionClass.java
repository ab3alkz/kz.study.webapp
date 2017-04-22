package kz.study.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author a.kussein
 * @since 04.05.2016.
 */
public class ConnectionClass {
    private DataSource dsSrc;
    private DataSource getFromContextSrc() throws NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc:mysql://localhost:3306/arma");
        return dataSource;
    }

    private DataSource getArmaBd(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("pma");
        dataSource.setPassword("123456");
        dataSource.setServerName("localhost");
        return dataSource;
    }

    public DataSource getDsSrc() throws NamingException {
        if(dsSrc==null){
            dsSrc = getFromContextSrc();
            if(dsSrc==null){
                return getArmaBd();
            }
        }
        return dsSrc;
    }
}
