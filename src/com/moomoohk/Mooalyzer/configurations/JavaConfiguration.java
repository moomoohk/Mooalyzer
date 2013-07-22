
package com.moomoohk.Mooalyzer.configurations;

import com.moomoohk.Mooalyzer.interfaces.Configuration;

public class JavaConfiguration extends Configuration
{
	protected void populateConfig()
	{
		config.put("usescurlies", "true");
		config.put("usesterminator", "true");
		config.put("terminator", ";");
		config.put("import", "import");
		config.put("comment", "//");
		config.put("samelinecurlies", "false");
		config.put("ifthen", "false");
		config.put("classdeclaration", "true");
	}
}

