package com.moomoohk.Mooalyzer.Lua;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Utils;

public class LuaStatement
{
	public static enum LuaStatementType
	{
		ELSE, ELSEIF, FOR, FUNCTION, IF, OTHER;
	}

	private LuaStatement parent;
	private String line;
	private ArrayList<LuaStatement> childStatements;
	private LuaStatementType type;

	public LuaStatement(LuaStatement parent, String line)
	{
		this(parent, line, LuaStatementType.OTHER);
	}

	public LuaStatement(LuaStatement parent, String line, LuaStatementType type)
	{
		this(parent, line, new ArrayList<LuaStatement>(), type);
	}

	public LuaStatement(LuaStatement parent, String line, ArrayList<LuaStatement> childStatements, LuaStatementType type)
	{
		this.parent = parent;
		this.line = line;
		this.childStatements = (childStatements == null ? new ArrayList<LuaStatement>() : childStatements);
		this.type = type;
	}

	public void addStatement(LuaStatement statement)
	{
		this.childStatements.add(statement);
	}

	public void setParent(LuaStatement parent)
	{
		this.parent = parent;
	}

	public LuaStatement getParent()
	{
		return this.parent;
	}

	public String getLine()
	{
		return this.line;
	}

	public ArrayList<LuaStatement> getChildStatements()
	{
		return this.childStatements;
	}

	public LuaStatementType getType()
	{
		return this.type;
	}

	public static LuaStatement parse(ArrayList<String> line)
	{
		for (LuaStatementType type : LuaStatementType.values())
			if (line.get(0).equalsIgnoreCase(type.toString()))
				return new LuaStatement(null, Utils.getListAsString(line, false, false), type);
		return new LuaStatement(null, Utils.getListAsString(line, false, false), LuaStatementType.OTHER);
	}

	public String toString()
	{
		String st = getLine();
		for (int i = 0; i < childStatements.size(); i++)
		{
			st+="\n\t";
			st += childStatements.get(i).toString();
		}
		return st;
	}

	public String details()
	{
		return "Type: " + getType();
	}
}