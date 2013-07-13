package com.moomoohk.Mooalyzer.parsers;

import java.util.ArrayList;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.Variable;
import com.moomoohk.Mooalyzer.interfaces.VariableParser;

public class GenericVariableParser implements VariableParser
{
	public Variable parseVariable(String temp)
	{
		Scanner scanner = new Scanner(temp);
		int words = 0;
		while (scanner.hasNext())
		{
			words++;
			scanner.next();
		}
		if (words == 4)
			return new Variable(scanner.next(), scanner.next(), temp.contains("=") ? temp.substring(temp.indexOf('=') + 1).trim().endsWith(";") ? temp.substring(temp.indexOf('=') + 1, temp.length() - 1).trim() : temp.substring(temp.indexOf('=') + 1).trim() : null);
		else
		{
			ArrayList<String> modifiers = new ArrayList<String>();
			String modiferTemp = temp.substring(0, temp.indexOf("=")).trim();
			scanner = new Scanner(modiferTemp);
			for (int i = 1; i <= words - 4; i++)
				modifiers.add(scanner.next());
			int skip = 0;
			for (String modifier : modifiers)
				skip += modifier.length() + 1;
			scanner = new Scanner(temp.substring(skip));
			return new Variable(modifiers, scanner.next(), scanner.next(), temp.contains("=") ? temp.substring(temp.indexOf('=') + 1).trim().endsWith(";") ? temp.substring(temp.indexOf('=') + 1, temp.length() - 1).trim() : temp.substring(temp.indexOf('=') + 1).trim() : null);
		}
	}

	public ArrayList<Variable> parseVariables(ArrayList<String> tempVariables)
	{
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for (int i = 0; i < tempVariables.size(); i += 4)
			variables.add(parseVariable(tempVariables.get(i) + " " + tempVariables.get(i + 1) + " " + tempVariables.get(i + 2)));
		return variables;
	}

	public ArrayList<Variable> parseVariables(String... tempVariables)
	{
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for(String variable:tempVariables)
		{
			Scanner scanner=new Scanner(variable);
			
		}
		return variables;
	}

	public String getTerminator()
	{
		return ";";
	}
}
