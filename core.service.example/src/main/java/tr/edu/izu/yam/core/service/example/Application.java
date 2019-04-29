package tr.edu.izu.yam.core.service.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tr.edu.izu.yam.core.service.example.config.Config;
import tr.edu.izu.yam.core.service.example.config.PropertiesBuilder;
import tr.edu.izu.yam.core.user.CoreUser;

public class Application {

	private static SessionFactory getSessionFactory() {
		PropertiesBuilder propertiesBuilder = null;
		SessionFactory sessionFactory = null;

		try {
			// uygun adresi giriniz.
			String url = "jdbc:postgresql://localhost:5432/yam";
			// uygun kullanici adini giriniz.
			String user = "yamuser";
			// kullanici adina ait sifreyi giriniz.
			String pass = "123456";
			propertiesBuilder = new PropertiesBuilder().withPostgreSQL().url(url).user(user).pass(pass);
			sessionFactory = Config.buildSessionFactory(propertiesBuilder, CoreUser.class);
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory = null;
		}

		return sessionFactory;
	}

	private static void query(SessionFactory sessionFactory) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query<CoreUser> q = session.createQuery("from CoreUser", CoreUser.class);

            List<CoreUser> users = q.list();

            for (CoreUser user : users) 
                    System.out.println(String.format("User:%s", user.getUserName()));
            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != session) {
				session.close();
				session = null;
			}
		}
	}

	public static void transact(SessionFactory sessionFactory) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// TODO: test edilecek model pojosuna ait sql veya hql yazilir.
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (null != session) {
				session.close();
				session = null;
			}
		}
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory = getSessionFactory();

		if (null != sessionFactory) {
			query(sessionFactory);
			transact(sessionFactory);
		} else {
			System.out.println("SessionFactory is null");
		}

	}

}
