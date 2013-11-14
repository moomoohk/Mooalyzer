package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpMethod extends CSharpElement
{
	private ArrayList<String> parameters;
	private ArrayList<String> lines;

	public CSharpMethod(String name)
	{
		this(null, name, null);
	}

	public CSharpMethod(ArrayList<String> modifiers, String name)
	{
		this(modifiers, name, null);
	}

	public CSharpMethod(String name, ArrayList<String> parameters)
	{
		this(null, name, parameters);
	}

	public CSharpMethod(ArrayList<String> modifiers, String name, ArrayList<String> parameters)
	{
		super(modifiers, name);
		this.parameters = parameters == null ? new ArrayList<String>() : parameters;
		this.lines = new ArrayList<String>();
	}

	public void addLine(String line)
	{
		this.lines.add(line);
	}

	public ArrayList<String> getParameters()
	{
		return this.parameters;
	}

	public ArrayList<String> getLines()
	{
		return this.lines;
	}

	public String toString()
	{
		String st = super.toString() + "(";
		for (String parameter : this.parameters)
			st += parameter + ", ";
		if (this.parameters.size() > 0)
			st = st.substring(0, st.length() - 2);
		st += ")";
		return st;
	}

	public String lines()
	{
		String s = "{\n";
		for (String line : this.lines)
			s += "\t" + line + "\n";
		return s + "}";
	}
}
