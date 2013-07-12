
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
		return new Variable(scanner.next(), scanner.next(), temp.contains("=") ? temp.substring(temp.indexOf('=') + 1).trim().endsWith(";") ? temp.substring(temp.indexOf('=') + 1, temp.length() - 1).trim() : temp.substring(temp.indexOf('=') + 1).trim() : null);
	}

	public ArrayList<Variable> parseVariables(ArrayList<String> tempVariables)
	{
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for (int i = 0; i < tempVariables.size(); i += 4)
			variables.add(parseVariable(tempVariables.get(i) + " " + tempVariables.get(i + 1) + " " + tempVariables.get(i + 2)));
		return variables;
	}
	
	public String getTerminator()
	{
		return ";";
	}
}

