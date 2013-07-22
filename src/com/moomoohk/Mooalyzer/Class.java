package com.moomoohk.Mooalyzer;

import java.util.ArrayList;

public class Class
{
	private ArrayList<String> imports;
	private String name;
	private ArrayList<Method> methods;
	private ArrayList<Variable> variables;
	private ArrayList<Class> nestedClasses;

	public Class()
	{
		this.imports = new ArrayList<String>();
		this.methods = new ArrayList<Method>();
		this.variables = new ArrayList<Variable>();
		this.nestedClasses = new ArrayList<Class>();
	}

	public void addImport(String i)
	{
		this.imports.add(i);
	}

	public ArrayList<String> getImports()
	{
		return this.imports;
	}

	public String getImportsAsString()
	{
		String temp = "";
		if (this.imports.size() == 0)
			return temp;
		for (String tempImport : this.imports)
			temp += "[" + tempImport.toString() + "], ";
		return temp.substring(0, temp.length() - 1);
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

	public String getMethodsAsString()
	{
		String temp = "";
		if (this.methods.size() == 0)
			return temp;
		for (Method method : this.methods)
			temp += "[" + method.toString() + "], ";
		return temp.substring(0, temp.length() - 1);
	}

	public ArrayList<Variable> getVariables()
	{
		return this.variables;
	}

	public String getVariablesAsString()
	{
		String temp = "";
		if (this.variables.size() == 0)
			return temp;
		for (Variable variable : this.variables)
			temp += "[" + variable.toString() + "], ";
		return temp.substring(0, temp.length() - 1);
	}
	
	public ArrayList<Class> getNestedClasses()
	{
		return this.nestedClasses;
	}

	public String details()
	{
		return "Class:\n- Imports (" + this.imports.size() + "): " + getImportsAsString() + "\n- Methods (" + this.methods.size() + "): " + getMethodsAsString() + "\n- Variables (" + this.variables.size() + "): " + getVariablesAsString();
	}
}
