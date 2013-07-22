package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Method
{
	private ArrayList<String> modifiers;
	private String returnType;
	private String name;
	private ArrayList<String> parameters;
	private ArrayList<String> lines;

	public Method(ArrayList<String> modifiers, String returnType, String name)
	{
		this(modifiers, returnType, name, null);
	}

	public Method(ArrayList<String> modifiers, String returnType, String name, ArrayList<String> parameters)
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

	public String getReturnType()
	{
		return this.returnType;
	}

	public String getName()
	{
		return this.name;
	}

	public ArrayList<String> getParameters()
	{
		return this.parameters;
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
		return (Utils.getListAsString(this.modifiers, false, false).length() > 0 ? Utils.getListAsString(this.modifiers, false, false) + " " : "") + this.returnType + " " + this.name + "(" + Utils.getListAsString(this.parameters, false, true) + ")";
	}

	public String details()
	{
		return "Method:\n- Modifiers: " + Utils.getListAsString(this.modifiers, true, false) + "\n- Name: " + this.name + "\n- Return type: " + this.returnType + "\n- Parameters: " + Utils.getListAsString(this.parameters, true, false) + "\n- Line count:" + this.lines.size();
	}
}
