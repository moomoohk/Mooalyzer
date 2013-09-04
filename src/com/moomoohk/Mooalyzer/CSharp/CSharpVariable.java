package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpVariable extends CSharpElement
{
	private String value;

	public CSharpVariable(String name)
	{
		this(null, name, null);
	}

	public CSharpVariable(ArrayList<String> modifiers, String name)
	{
		this(modifiers, name, null);
	}

	public CSharpVariable(ArrayList<String> modifiers, String name, String value)
	{
		super(modifiers, name);
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
