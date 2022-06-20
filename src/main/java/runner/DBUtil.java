package runner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
    private static DataSource dataSource;
    private static final String DATABASE = "kpistat";
    private static final String JNDI_LOOKUP_SERVICE = "java:/comp/env/jdbc/"+DATABASE;

    static {
        try {
            Context context = new InitialContext();
            Object lookup = context.lookup(JNDI_LOOKUP_SERVICE);
            if (lookup != null) {
                dataSource = (DataSource) lookup;
            }
            else {
                new RuntimeException("Data source wasn't found");
            }
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}