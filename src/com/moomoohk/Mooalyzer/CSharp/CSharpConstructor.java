package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpConstructor extends CSharpMethod
{
	public static enum CSharpConstructorType
	{
		THIS, BASE, NORMAL;
	}

	private CSharpConstructorType type = CSharpConstructorType.NORMAL;
	private ArrayList<String> callParameters;

	public CSharpConstructor(String className)
	{
		this(null, className, null, CSharpConstructorType.NORMAL, null);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className)
	{
		this(modifiers, className, null, CSharpConstructorType.NORMAL, null);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters)
	{
		this(modifiers, className, parameters, CSharpConstructorType.NORMAL, null);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters, CSharpConstructorType type)
	{
		this(modifiers, className, parameters, type, null);
	}

	public CSharpConstructor(String className, CSharpConstructorType type)
	{
		this(null, className, null, type, null);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, CSharpConstructorType type)
	{
		this(modifiers, className, null, type, null);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters, CSharpConstructorType type, ArrayList<String> callParameters)
	{
		super(modifiers, className, parameters);
		this.type = type;
		this.callParameters = callParameters == null ? new ArrayList<String>() : callParameters;
	}

	public CSharpConstructorType getType()
	{
		return this.type;
	}

	public ArrayList<String> getCallParameters()
	{
		return this.callParameters;
	}

	public String toString()
	{
		String s = super.toString();
		switch (this.type)
		{
		case NORMAL:
			return s;
		case THIS:
			s += " : this(";
			break;
		case BASE:
			s += " : base(";
			break;
		}
		for (String callParameter : this.callParameters)
			s += callParameter + ", ";
		if (this.callParameters.size() > 0)
			s = s.substring(0, s.length() - 2);
		return s += ")";
	}
}
