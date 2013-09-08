package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public abstract class CSharpElement
{
	protected ArrayList<CSharpAttribute> attributes;
	protected ArrayList<String> modifiers;
	protected String name;
	protected int line;
	
	public CSharpElement(ArrayList<String> modifiers, String name, int line)
	{
		this.attributes = new ArrayList<CSharpAttribute>();
		this.modifiers = modifiers == null ? new ArrayList<String>() : modifiers;
		this.name = name;
		this.line = line;
	}

	public ArrayList<CSharpAttribute> getAttributes()
	{
		return this.attributes;
	}

	public ArrayList<String> getModifiers()
	{
		return this.modifiers;
	}

	public void addAttribute(CSharpAttribute attribute)
	{
		attribute.setChild(this);
		this.attributes.add(attribute);
	}

	public String getName()
	{
		return this.name;
	}

	public int getLine()
	{
		return this.line;
	}

	public String toString()
	{
		String st = "";
		for (String modifier : this.modifiers)
			st += modifier + " ";
		st += this.name;
		return st;
	}
}
