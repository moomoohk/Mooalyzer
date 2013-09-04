package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpClass extends CSharpElement
{
	private String parentClass;
	private ArrayList<String> interfaces;
	private ArrayList<CSharpMethod> methods;
	private ArrayList<CSharpVariable> variables;

	public CSharpClass(ArrayList<String> modifiers, String name, String parentClass, ArrayList<String> interfaces)
	{
		super(modifiers, name);
		this.parentClass = parentClass;
		this.interfaces = interfaces;
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

	public String getParentClass()
	{
		return this.parentClass;
	}

	public ArrayList<String> getInterfaces()
	{
		return this.interfaces;
	}

	public ArrayList<CSharpMethod> getMethods()
	{
		return this.methods;
	}

	public ArrayList<CSharpVariable> getVariables()
	{
		return this.variables;
	}
}
