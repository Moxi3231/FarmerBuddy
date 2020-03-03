/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author moxan
 */
public class DBContext {

    private Configuration configuration;
    private StandardServiceRegistry serviceRegistry;
    private static DBContext context;
    // builds a session factory from the service registry
    private SessionFactory sessionFactory;

    private Session session;// = sessionFactory.openSession();
    private DBContext()
    {
        this.configuration  = new Configuration().configure("farmerbuddy/hibernate.cfg.xml");
        this.serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    public static DBContext getDbContext()
    {
         if(context==null)
        {
           context =  new DBContext();
        }
         return context;
    }
    public Configuration getConfig()
    {
        return this.configuration;
    }
    public StandardServiceRegistry getServiceRegistry()
    {
        return this.serviceRegistry;
    }
    public Session getSession()
    {
       
        this.session = sessionFactory.openSession();
        return this.session;
    }
    public void closeSession()
    {
        this.session.close();
    }
    public void close()
    {
        this.sessionFactory.close();
    }
  /*  Transaction t = session.beginTransaction();

    //session.save(e1);
    t.commit ();

    System.out.println (

    "successfully saved");
    session.close ();

    sessionFactory.close ();

*/
}
