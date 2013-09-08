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

	public CSharpConstructor(String className, int line)
	{
		this(null, className, null, CSharpConstructorType.NORMAL, null, line);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, int line)
	{
		this(modifiers, className, null, CSharpConstructorType.NORMAL, null, line);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters, int line)
	{
		this(modifiers, className, parameters, CSharpConstructorType.NORMAL, null, line);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters, CSharpConstructorType type, int line)
	{
		this(modifiers, className, parameters, type, null, line);
	}

	public CSharpConstructor(String className, CSharpConstructorType type, int line)
	{
		this(null, className, null, type, null, line);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, CSharpConstructorType type, int line)
	{
		this(modifiers, className, null, type, null, line);
	}

	public CSharpConstructor(ArrayList<String> modifiers, String className, ArrayList<String> parameters, CSharpConstructorType type, ArrayList<String> callParameters, int line)
	{
		super(modifiers, className, parameters, line);
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
		return s += ") ";
	}
}
