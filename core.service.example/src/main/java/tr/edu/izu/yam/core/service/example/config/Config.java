package tr.edu.izu.yam.core.service.example.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Config {

	public static SessionFactory buildSessionFactory(PropertiesBuilder builder, Class<?>... classes) throws Exception {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration();
			Properties settings = builder.build();
			configuration.setProperties(settings);
			if (null != classes && classes.length > 0) {
				for (Class<?> clazz : classes)
					configuration.addAnnotatedClass(clazz);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} else {
				throw new Exception("No annoted class specified for hibernate");
			}

		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory = null;
			throw e;
		}

		return sessionFactory;

	}

}
