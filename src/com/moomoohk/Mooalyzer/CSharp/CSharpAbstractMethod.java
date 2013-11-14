package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpAbstractMethod extends CSharpMethod
{
	public CSharpAbstractMethod(String name)
	{
		this(null, name, null);
	}

	public CSharpAbstractMethod(ArrayList<String> modifiers, String name)
	{
		this(modifiers, name, null);
	}

	public CSharpAbstractMethod(String name, ArrayList<String> parameters)
	{
		this(null, name, parameters);
	}

	public CSharpAbstractMethod(ArrayList<String> modifiers, String name, ArrayList<String> parameters)
	{
		super(modifiers, name);
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
