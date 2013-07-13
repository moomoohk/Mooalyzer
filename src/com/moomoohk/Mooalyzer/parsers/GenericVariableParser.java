package com.moomoohk.Mooalyzer.parsers;

import java.util.ArrayList;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.Variable;
import com.moomoohk.Mooalyzer.interfaces.Configuration;
import com.moomoohk.Mooalyzer.interfaces.VariableParser;

public class GenericVariableParser implements VariableParser
{//vec3 eye_dir; // Direction eyes are looking
	public Variable parseVariable(Configuration config, String variable)
	{
		if ((variable.contains("=") && (variable.indexOf("=") != variable.lastIndexOf("=") || !variable.substring(0, variable.indexOf("=")).trim().contains(" "))) || (!variable.contains(config.getTerminator())) || (variable.indexOf(" ") == -1))
			return null;
		Scanner scanner = new Scanner(variable);
		int words = 0, beforeEquals = 0, afterEquals = 0;
		String scanned = "";
		while (scanner.hasNext())
		{
			String word = scanner.next();
			if(word.startsWith(config.getComment()))
				break;
			words++;
			if (words == 2 && word.equals("="))
				return null;
			scanned += word;
			if (scanned.contains("="))
				afterEquals++;
			else
				beforeEquals++;
		}
		ArrayList<String> modifiers = new ArrayList<String>();
		String modifierTemp = variable.substring(0, variable.contains("=") ? variable.indexOf("=") : variable.indexOf(config.getTerminator())+1).trim();
		scanner = new Scanner(modifierTemp);
		for (int i = 1; i <= words - afterEquals - 2; i++)
			modifiers.add(scanner.next());
		int skip = 0;
		for (String modifier : modifiers)
			skip += modifier.length() + 1;
		scanner = new Scanner(variable.substring(skip));
		if (variable.contains("="))
		{
			return new Variable(modifiers, scanner.next(), scanner.next(), variable.contains("=") ? variable.substring(variable.indexOf('=') + 1).trim().endsWith(";") ? variable.substring(variable.indexOf('=') + 1, variable.length() - 1).trim() : variable.substring(variable.indexOf('=') + 1).trim()
					: null);
		}
		else
		{
			String type = scanner.next();
			String name = scanner.next();
			if (name.endsWith(config.getTerminator()))
				name = name.substring(0, name.length() - 1);
			return new Variable(modifiers, type, name);
		}
	}

	public ArrayList<Variable> parseVariables(Configuration config, ArrayList<String> variables)
	{
		ArrayList<Variable> tempVariables = new ArrayList<Variable>();
		for (String variable : variables)
			tempVariables.add(parseVariable(config, variable));
		return tempVariables;
	}

	public ArrayList<Variable> parseVariables(Configuration config, String... variables)
	{
		ArrayList<Variable> tempVariables = new ArrayList<Variable>();
		for (String variable : variables)
			tempVariables.add(parseVariable(config, variable));
		return tempVariables;
	}
}
