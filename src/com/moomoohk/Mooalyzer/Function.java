package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Function
{
	private String name;
	private ArrayList<String> modifiers;
	private ArrayList<String> parameters;

	public Function(String name, ArrayList<String> modifiers, ArrayList<String> parameters)
	{
		this.name = name;
		this.modifiers = modifiers == null ? new ArrayList<String>() : modifiers;
		this.parameters = parameters == null ? new ArrayList<String>() : parameters;
	}

	public String toString()
	{
		String st = "";
		for (String modifier : this.modifiers)
			st += modifier + " ";
		st += this.name + "(";
		if (this.parameters.size() == 0)
			return st + ")";
		else
		{
			for (String parameter : parameters)
				st += parameter + ", ";
			return st.substring(0, st.length() - 2) + ")";
		}
	}
}
