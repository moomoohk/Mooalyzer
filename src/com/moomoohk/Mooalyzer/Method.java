package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Method
{
	private ArrayList<String> modifiers;
	private Variable returnType;
	private String name;
	private ArrayList<Variable> parameters;

	public Method(ArrayList<String> modifiers, Variable returnType, String name)
	{
		this(modifiers, returnType, name, null);
	}
	
	public Method(ArrayList<String> modifiers, Variable returnType, String name, ArrayList<Variable> parameters)
	{
		this.modifiers = modifiers;
		this.returnType=returnType;
		this.name = name;
		this.parameters = parameters;
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
}
