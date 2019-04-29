package tr.edu.izu.yam.core.service.example.config;

import java.util.Properties;

import org.hibernate.cfg.Environment;

public class PropertiesBuilder {

	private String driver = null;
	private String url = null;
	private String user = null;
	private String pass = null;
	private String dialect = null;
	private boolean showSql = Boolean.TRUE;
	private String currentSessionContextClass = null;
	
	public PropertiesBuilder() {
	}

	public PropertiesBuilder withPostgreSQL() {
		this.driver = "org.postgresql.Driver";
		this.dialect = "org.hibernate.dialect.PostgreSQLDialect";
		return this;
	}

	public PropertiesBuilder showSQL() {
		this.showSql = Boolean.TRUE;
		return this;
	}

	public PropertiesBuilder hideSQL() {
		this.showSql = Boolean.FALSE;
		return this;
	}

	public PropertiesBuilder currentSessionContextClass(String aValue) {
		this.currentSessionContextClass = aValue;
		return this;
	}

	public PropertiesBuilder driver(String aDriver) {
		this.driver = aDriver;
		return this;
	}

	public PropertiesBuilder dialect(String aDialect) {
		this.dialect = aDialect;
		return this;
	}

	public PropertiesBuilder url(String anURL) {
		this.url = anURL;
		return this;
	}

	public PropertiesBuilder user(String anUser) {
		this.user = anUser;
		return this;
	}

	public PropertiesBuilder pass(String aPassword) {
		this.pass = aPassword;
		return this;
	}

	public Properties build() throws Exception {
		Properties settings = new Properties();
		if (null != this.driver && !this.driver.trim().isEmpty())
			settings.put(Environment.DRIVER, this.driver);
		else
			throw new Exception("`driver` attribute must set");
		if (null != this.url && !this.url.trim().isEmpty())
			settings.put(Environment.URL, this.url);
		else
			throw new Exception("`url` attribute must set");
		if (null != this.user && !this.user.trim().isEmpty())
			settings.put(Environment.USER, this.user);
		else
			throw new Exception("`user` attribute must set");
		if (null != this.pass && !this.pass.trim().isEmpty())
			settings.put(Environment.PASS, "iO3uIsj)");
		else
			throw new Exception("`password` must set");
		if (null != this.dialect && !this.dialect.trim().isEmpty())
			settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		else
			throw new Exception("`dialect` must set");

		if (null != this.currentSessionContextClass && !this.currentSessionContextClass.trim().isEmpty())
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, this.currentSessionContextClass);
		else
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		
		
		if (this.showSql)
			settings.put(Environment.SHOW_SQL, "true");
		else
			settings.put(Environment.SHOW_SQL, "false");
		
		

		return settings;
	}

}
