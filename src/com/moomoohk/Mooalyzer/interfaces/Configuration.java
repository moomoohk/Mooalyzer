package com.moomoohk.Mooalyzer.interfaces;

import java.util.HashMap;

public abstract class Configuration
{
	protected HashMap<String, String> config;

	public Configuration()
	{
		config = new HashMap<String, String>();
		config.put("usescurlies", null);
		config.put("usesterminator", null);
		config.put("terminator", null);
		config.put("import", null);
		config.put("comment", null);
		config.put("samelinecurlies", null);
		config.put("ifthen", null);
		config.put("classdeclaration", null);
		populateConfig();
	}

	protected abstract void populateConfig();

	public String getPropery(String property)
	{
		return this.config.get(property);
	}

	public HashMap<String, String> getConfig()
	{
		return this.config;
	}
}
