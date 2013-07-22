package com.moomoohk.Mooalyzer.parsers;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Method;
import com.moomoohk.Mooalyzer.Utils;
import com.moomoohk.Mooalyzer.interfaces.Configuration;
import com.moomoohk.Mooalyzer.interfaces.MethodParser;

public class GenericMethodParser implements MethodParser
{
	public Method parseMethod(Configuration config, String line)
	{
		ArrayList<String> split = Utils.splitWords(line);
		if (split.get(split.size() - 1).equals("{"))
			split.remove(split.size() - 1);
		ArrayList<String> modifiers = new ArrayList<String>();
		ArrayList<String> parameters = new ArrayList<String>();
		String returnType = "", name = "";
		boolean reachedParams = false;
		int i = 1;
		String param = "";
		for (String word : split)
		{
			if (word.equals("("))
			{
				reachedParams = true;
				name = modifiers.remove(modifiers.size() - 1).trim();
				returnType = modifiers.remove(modifiers.size() - 1).trim();
			}
			if (!reachedParams)
				modifiers.add(word.trim());
			else
				if (!word.equals("("))
					if (i % 2 == 0)
						parameters.add(param + " " + word);
					else
						param = word;
			if (!word.equals(","))
				i++;
		}
		return new Method(modifiers, returnType, name, parameters);
	}

	public ArrayList<Method> parseMethods(Configuration config, ArrayList<String> methods)
	{
		return null;
	}
}
