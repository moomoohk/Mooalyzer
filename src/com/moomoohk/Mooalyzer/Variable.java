package com.moomoohk.Mooalyzer;


public class Variable
{
	private String type;
	private String name;
	private String value;

	public Variable(String type, String name)
	{
		this(type, name, null);
	}

	public Variable(String type, String name, String value)
	{
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public String getType()
	{
		return this.type;
	}

	public String getName()
	{
		return this.name;
	}

	public String getValue()
	{
		return this.value;
	}

	public String details()
	{
		return "Variable:\n- Name: [" + this.name + "]\n- Type: [" + this.type + "]\n- Value: [" + this.value + "]\n- ToString: [" + toString() + "]";
	}

	public String toString()
	{
		return this.type + " " + this.name + " = " + this.value + ";";
	}
}
