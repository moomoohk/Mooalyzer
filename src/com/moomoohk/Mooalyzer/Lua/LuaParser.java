package com.moomoohk.Mooalyzer.Lua;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import com.moomoohk.Mooalyzer.Utils;

public class LuaParser
{
	public static LuaScript parseScript(File script)
	{
		ArrayList<LuaStatement> statements = new ArrayList<LuaStatement>();
		Stack<LuaStatement> statementStack = new Stack<LuaStatement>();
		int level = 0;
		Scanner scanner = new Scanner("test(1, 2)\n" + "test(3, 4)\n" + "function test(param1, param2)\n" + "if param1 > param2 then\n" + "return param1\n" + "end\n" + "return param2\n" + "end\n" + "test(4, 5)");
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			if (line.trim().length() == 0)
				continue;
			ArrayList<String> split = Utils.splitWords(line);
			LuaStatement currentStatement = LuaStatement.parse(split);
			if (split.get(0).equals("function") || split.get(0).equals("if") || split.get(0).equals("else") || split.get(0).equals("elseif") || split.get(0).equals("for"))
			{
				if (level != 0)
				{
					currentStatement.setParent(statementStack.peek());
					statementStack.peek().addStatement(currentStatement);
				}
				statementStack.push(currentStatement);
				level++;
			}
			else
				if (split.get(0).equals("end"))
				{
					level--;
					if (level == 0)
						statements.add(statementStack.pop());
					else
						statementStack.pop().addStatement(new LuaStatement(null, "end"));
				}
				else
					if (level == 0)
						statements.add(currentStatement);
					else
					{
						currentStatement.setParent(statementStack.peek());
						statementStack.peek().addStatement(currentStatement);
					}
			System.out.println("Level: " + level);
			System.out.println("Line: " + line);
			System.out.println("-");
		}
		System.out.println();
		System.out.println("DONE");
		System.out.println(new LuaScript(statements).toString());
		return null;
	}

	public static void main(String[] args)
	{
		LuaStatement temp1 = new LuaStatement(null, "if param1 > param2 then");
		LuaStatement temp2 = new LuaStatement(temp1, "return param1");
		temp1.addStatement(temp2);
		//		System.out.println(temp1.toString());
		parseScript(null);
	}
}