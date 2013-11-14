package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpClass extends CSharpElement
{
	private ArrayList<String> inherits;
	private ArrayList<CSharpMethod> methods;
	private ArrayList<CSharpVariable> variables;

	public CSharpClass(ArrayList<String> modifiers, String name, ArrayList<String> inherits)
	{
		super(modifiers, name);
		this.inherits = inherits;
		this.methods = new ArrayList<CSharpMethod>();
		this.variables = new ArrayList<CSharpVariable>();
	}

	public void addMethod(CSharpMethod method)
	{
		this.methods.add(method);
	}

	public void addVariable(CSharpVariable variable)
	{
		this.variables.add(variable);
	}

	public ArrayList<String> getInherits()
	{
		return this.inherits;
	}

	public ArrayList<CSharpMethod> getMethods()
	{
		return this.methods;
	}

	public ArrayList<CSharpVariable> getVariables()
	{
		return this.variables;
	}

	public String getHeader()
	{
		String st = "";
		for (String modifier : this.modifiers)
			st += modifier + " ";
		st += "class " + this.name;
		if (this.inherits != null && this.inherits.size() > 0)
		{
			st += ":" + this.inherits.get(0);
			for (int i = 1; i < this.inherits.size(); i++)
				st += ", " + this.inherits.get(i);
		}
		return st;
	}

	public String details()
	{
		String st = super.details();
		st += "Header: " + getHeader();
		if (this.inherits != null && this.inherits.size() > 0)
		{
			st += "\nInherits: ";
			for (String inherit : this.inherits)
				st += "[" + inherit + "]";
		}
		else
			st += "\nInherits: [none]";
		st += "\nMethods: " + this.methods.size();
		st += "\nVariables: " + this.methods.size();
		return st;
	}

	public String toString()
	{
		String st = getHeader();
		st += "\n{\n";
		for (CSharpVariable var : this.variables)
			st += "\t" + var.toString().replace("\n", "\n\t") + "\n";
		for (CSharpMethod method : this.methods)
			st += "\t" + method.toString() + "\n\t" + method.lines().replace("\n", "\n\t") + "\n";
		return st += "}";
	}
}
