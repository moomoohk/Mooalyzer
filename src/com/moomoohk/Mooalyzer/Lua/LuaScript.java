package com.moomoohk.Mooalyzer.Lua;

import java.util.ArrayList;

public class LuaScript
{
	private ArrayList<LuaStatement> statements;

	public LuaScript(ArrayList<LuaStatement> statements)
	{
		this.statements = statements;
	}

	public String toString()
	{
		String st = "";
		for (LuaStatement statement : statements)
			st += statement.toString()+"\n";
		return st;
	}
}
