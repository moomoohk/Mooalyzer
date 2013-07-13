package com.moomoohk.Mooalyzer.interfaces;

import java.util.HashMap;

public abstract class Configuration
{
	protected HashMap<String, String> config;

	public Configuration()
	{
		config = new HashMap<String, String>();
		config.put("terminator", null);
		config.put("import", null);
		config.put("comment", null);
		config.put("samelinecurlies", null);
		config.put("ifthen", null);
		populateConfig();
	}

	protected abstract void populateConfig();

	public String getTerminator()
	{
		return config.get("terminator");
	}

	public String getImportDeclaration()
	{
		return config.get("import");
	}

	public String getComment()
	{
		return config.get("comment");
	}
	
	public String getCurliesConvention()
	{
		return config.get("samelinecurlies");
	}
	
	public String getIfThen()
	{
		return config.get("ifthen");
	}

	public HashMap<String, String> getConfig()
	{
		return this.config;
	}
}
