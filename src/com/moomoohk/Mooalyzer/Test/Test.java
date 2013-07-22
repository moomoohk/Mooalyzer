package com.moomoohk.Mooalyzer.Test;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Class;
import com.moomoohk.Mooalyzer.Utils;
import com.moomoohk.Mooalyzer.configurations.AngelScriptConfiguration;
import com.moomoohk.Mooalyzer.configurations.JavaConfiguration;
import com.moomoohk.Mooalyzer.parsers.GenericClassParser;
import com.moomoohk.Mooalyzer.parsers.GenericMethodParser;

public class Test
{
	//	public static Console console = new Console();
	//
	//	static
	//	{
	//		ArrayList<Command<?>> commands=new ArrayList<Command<?>>();
	//		commands.add(new ParseVariableCommand(console, "parsevariable", "Parses input into a variable object. Usage: parse <variable>", 2, -1, Color.white));
	//		commands.add(new ParseVariablesCommand(console, "parsevariables", "Parses input into a list of variable objects. Usage: parse <variables>", 2, -1, Color.white));
	//		console.loadCommands(commands);
	//		console.setVisible(true);
	//	}

	public static void main(String[] args)
	{
		System.out.println(new GenericMethodParser().parseMethod(new JavaConfiguration(), "void HitByItem(string material, vec3 point, int id, int type) {").toString());
		System.out.println(new GenericMethodParser().parseMethod(new JavaConfiguration(), "void HitByItem(string material, vec3 point, int id, int type) {").details());
		Class c = new GenericClassParser().parseClass(new AngelScriptConfiguration(), null);
		System.out.println("Parsed with " + new AngelScriptConfiguration().getClass().getName().substring(new AngelScriptConfiguration().getClass().getName().lastIndexOf(".") + 1));
		System.out.println(c.details());
	}
}
