package com.moomoohk.Mooalyzer.configurations;

import com.moomoohk.Mooalyzer.interfaces.Configuration;

public class AngelScriptConfiguration extends Configuration
{
	protected void populateConfig()
	{
		config.put("terminator", ";");
		config.put("import", "#include");
		config.put("comment", "//");
		config.put("samelinecurlies", "true");
		config.put("ifthen", "false");
	}
}
