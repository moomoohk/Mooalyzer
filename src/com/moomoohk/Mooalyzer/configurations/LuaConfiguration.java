
package com.moomoohk.Mooalyzer.configurations;

import com.moomoohk.Mooalyzer.interfaces.Configuration;

public class LuaConfiguration extends Configuration
{
	protected void populateConfig()
	{
		config.put("usescurlies", "false");
		config.put("usesterminator", "false");
		config.put("import", "require");
		config.put("comment", "--");
		config.put("ifthen", "false");
		config.put("classdeclaration", "false");
	}
}

