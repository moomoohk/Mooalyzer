package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Variable
{
	private ArrayList<String> modifiers;
	private String type;
	private String name;
	private String value;

	public Variable(String type, String name)
	{
		this(type, name, null);
	}

	public Variable(String type, String name, String value)
	{
		this(null, type, name, value);
	}

	public Variable(ArrayList<String> modifiers, String type, String name, String value)
	{
		this.modifiers = modifiers;
		this.type = type;
		this.name = name;
		this.value = value;
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

	public String getType()
	{
		return this.type;
	}

	public String getName()
	{
		return this.name;
	}

	public String getValue()
	{
		return this.value;
	}

	public String details()
	{
		return "Variable:\n- Modifiers: [" + getModifiersAsString() + "]\n- Name: [" + this.name + "]\n- Type: [" + this.type + "]\n- Value: [" + this.value + "]\n- ToString: [" + toString() + "]";
	}

	public String toString()
	{
		return getModifiersAsString() + " " + this.type + " " + this.name + " = " + this.value + ";";
	}
}
