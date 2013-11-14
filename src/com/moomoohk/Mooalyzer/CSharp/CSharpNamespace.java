package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpNamespace
{
	private String name;
	private ArrayList<CSharpClass> classes;

	public CSharpNamespace(String name)
	{
		this.name = name;
		this.classes = new ArrayList<CSharpClass>();
	}

	public void addClass(CSharpClass c)
	{
		this.classes.add(c);
	}

	public void removeClass(CSharpClass c)
	{
		this.classes.remove(c);
	}

	public ArrayList<CSharpClass> getClasses()
	{
		return this.classes;
	}

	public String toString()
	{
		String st = "namespace " + this.name + "\n{\n";
		for (CSharpClass c : this.classes)
			st += "\t" + c.toString().replace("\n", "\n\t") + "\n";
		return st += "}";
	}
}
