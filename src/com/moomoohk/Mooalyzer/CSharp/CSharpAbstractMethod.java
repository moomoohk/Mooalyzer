package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpAbstractMethod extends CSharpMethod
{
	public CSharpAbstractMethod(String name, int line)
	{
		this(null, name, null, line);
	}

	public CSharpAbstractMethod(ArrayList<String> modifiers, String name, int line)
	{
		this(modifiers, name, null, line);
	}

	public CSharpAbstractMethod(String name, ArrayList<String> parameters, int line)
	{
		this(null, name, parameters, line);
	}

	public CSharpAbstractMethod(ArrayList<String> modifiers, String name, ArrayList<String> parameters, int line)
	{
		super(modifiers, name, line);
	}

	public String toString()
	{
		return super.toString() + ";";
	}

	public String lines()
	{
		return "";
	}
}
