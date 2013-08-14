package com.moomoohk.Mooalyzer.Lua;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;

import com.moomoohk.Mooalyzer.Utils;

public class LuaParser
{
	public static LuaScript parseScript(File script) throws FileNotFoundException
	{
		ArrayList<LuaStatement> statements = new ArrayList<LuaStatement>();
		Stack<LuaStatement> statementStack = new Stack<LuaStatement>();
		int level = 0;
		Scanner scanner = new Scanner(script);
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine().trim();
			if (line.length() == 0)
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
					LuaStatement pop = statementStack.pop();
					level--;
					if (level == 0)
					{
						pop.addStatement(new LuaStatement(null, "end"));
						statements.add(pop);
					}
					else
						pop.addStatement(new LuaStatement(null, "end"));
				}
				else
				{
					if (level == 0)
						statements.add(currentStatement);
					else
					{
						currentStatement.setParent(statementStack.peek());
						statementStack.peek().addStatement(currentStatement);
					}
				}
		}
		return new LuaScript(statements);
	}

	public static void main(String[] args)
	{
		LuaScript script = null;
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		if (fd.getFile() != null)
			try
			{
				script = parseScript(new File(fd.getDirectory() + "/" + fd.getFile()));
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (script == null)
			System.out.println("A problem occurred during the parse.");
		else
		{
			System.out.println("-- Parsed with the Lua Mooalyzer --");
			System.out.println(script.toString());
			System.out.println("-- END --");
		}
		System.exit(0);
	}
}