package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpVariable extends CSharpElement
{
	private String value;

	public CSharpVariable(String name, int line)
	{
		this(null, name, null, line);
	}

	public CSharpVariable(ArrayList<String> modifiers, String name, int line)
	{
		this(modifiers, name, null, line);
	}

	public CSharpVariable(ArrayList<String> modifiers, String name, String value, int line)
	{
		super(modifiers, name, line);
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return this.name;
	}

	public String getValue()
	{
		return this.value;
	}

	public String toString()
	{
		String s = super.toString();
		if (this.value != null)
			s += " = " + this.value;
		return s + ";";
	}
}
