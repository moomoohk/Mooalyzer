package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Method
{
	private ArrayList<String> modifiers;
	private Variable returnType;
	private String name;
	private ArrayList<Variable> parameters;
	private ArrayList<String> lines;

	public Method(ArrayList<String> modifiers, Variable returnType, String name)
	{
		this(modifiers, returnType, name, null);
	}

	public Method(ArrayList<String> modifiers, Variable returnType, String name, ArrayList<Variable> parameters)
	{
		this.modifiers = modifiers;
		this.returnType = returnType;
		this.name = name;
		this.parameters = parameters;
		this.lines = new ArrayList<String>();
	}

	public ArrayList<String> getModifiers()
	{
		return this.modifiers;
	}

	public String getModifiersAsString()
	{
		String temp = "";
		if (this.modifiers == null)
			return temp;
		for (String modifier : this.modifiers)
			temp += modifier + " ";
		return temp.substring(0, temp.length() - 1);
	}

	public Variable getReturnType()
	{
		return this.returnType;
	}

	public String getName()
	{
		return this.name;
	}

	public ArrayList<Variable> getParameters()
	{
		return this.parameters;
	}

	public String getParametersAsString()
	{
		String temp = "";
		if (this.parameters == null)
			return temp;
		for (Variable parameter : this.parameters)
			temp += parameter.toString() + ", ";
		return temp.substring(0, temp.length() - 1);
	}

	public void addLine(String line)
	{
		this.lines.add(line);
	}

	public ArrayList<String> getLines()
	{
		return this.lines;
	}

	public String toString()
	{
		return (getModifiersAsString().length() > 0 ? getModifiersAsString() + " " : "") + this.returnType + " " + this.name + "(" + getParametersAsString() + ")";
	}

	public String details()
	{
		return "Method:\n- Modifiers: " + getModifiersAsString() + "\n- Name: " + this.name + "\n- Return type: " + this.returnType + "\n- Parameters: " + getParametersAsString() + "\n- Line count:" + this.lines.size();
	}
}
