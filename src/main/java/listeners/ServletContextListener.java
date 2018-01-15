package listeners;

import db.transaction.ConnectionManager;
import db.transaction.TransactionManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.sql.*;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private final static Logger LOGGER = Logger.getLogger(ServletContextListener.class);

    // Public constructor is required by servlet spec
    public ServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        initLog4j(sce.getServletContext());
        LOGGER.trace("init context");

        TransactionManager transactionManager = new TransactionManager(getDataSource());

        transactionManager.execute(() -> {
            Connection con = ConnectionManager.getConnection();
            Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                rs = st.executeQuery("SELECT * from users");
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    private DataSource getDataSource() {
        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/appname");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initLog4j(ServletContext sc) {
        System.out.println("Log4JInitServlet is initializing log4j");
        String log4jLocation = sc.getInitParameter("log4j-properties-location");
        System.out.println(log4jLocation);

        if (log4jLocation == null) {
            System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String log4jProp = sc.getRealPath("WEB-INF/log4j.properties");
            //String log4jProp = webAppPath + log4jLocation;
            File properties = new File(log4jProp);
            if (properties.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }

}
