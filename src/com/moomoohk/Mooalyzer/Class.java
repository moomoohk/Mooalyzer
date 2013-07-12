package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Class
{
	private ArrayList<Method> methods;
	private ArrayList<Variable> variables;

	public Class()
	{
		this.methods = new ArrayList<Method>();
		this.variables = new ArrayList<Variable>();
	}
	
	public void addMethod(Method m)
	{
		this.methods.add(m);
	}
	
	public void addVariable(Variable v)
	{
		this.variables.add(v);
	}
	
	public void removeMethod(Method m)
	{
		this.methods.remove(m);
	}
	
	public void removeVariable(Variable v)
	{
		this.variables.remove(v);
	}
	
	public ArrayList<Method> getMethods()
	{
		return this.methods;
	}
	
	public ArrayList<Variable> getVariables()
	{
		return this.variables;
	}
}
