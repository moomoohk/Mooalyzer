package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpFile
{
	private ArrayList<CSharpNamespace> namespaces;

	public CSharpFile()
	{
		this.namespaces = new ArrayList<CSharpNamespace>();
	}

	public void addNamespace(CSharpNamespace namespace)
	{
		this.namespaces.add(namespace);
	}

	public void removeNamespace(CSharpNamespace namespace)
	{
		this.namespaces.remove(namespace);
	}

	public ArrayList<CSharpNamespace> getNamespaces()
	{
		return this.namespaces;
	}

	public String toString()
	{
		String st = "";
		for (CSharpNamespace namespace : this.namespaces)
			st += namespace.toString() + "\n\n";
		return st;
	}
}
