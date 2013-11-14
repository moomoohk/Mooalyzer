package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public abstract class CSharpElement
{
	protected ArrayList<CSharpAttribute> attributes;
	protected ArrayList<String> modifiers;
	protected String name;

	public CSharpElement(ArrayList<String> modifiers, String name)
	{
		this.attributes = new ArrayList<CSharpAttribute>();
		this.modifiers = modifiers == null ? new ArrayList<String>() : modifiers;
		this.name = name;
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

	public String details()
	{
		String st = "Name: " + this.name;
		if (this.modifiers != null && this.modifiers.size() > 0)
		{
			st += "\nModifiers: ";
			for (String modifier : this.modifiers)
				st += "[" + modifier + "]";
		}
		else
			st += "\nModifiers: [none]";
		return st;
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
