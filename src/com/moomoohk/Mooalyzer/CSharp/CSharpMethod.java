package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpMethod extends CSharpElement
{
	private ArrayList<String> parameters;
	private ArrayList<String> lines;

	public CSharpMethod(String name, int line)
	{
		this(null, name, null, line);
	}

	public CSharpMethod(ArrayList<String> modifiers, String name, int line)
	{
		this(modifiers, name, null, line);
	}

	public CSharpMethod(String name, ArrayList<String> parameters, int line)
	{
		this(null, name, parameters, line);
	}

	public CSharpMethod(ArrayList<String> modifiers, String name, ArrayList<String> parameters, int line)
	{
		super(modifiers, name, line);
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
			s += line + "\n";
		return s + "}";
	}
}
