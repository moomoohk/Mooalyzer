package com.moomoohk.Mooalyzer.Test;

import java.util.Scanner;

import com.moomoohk.Mooalyzer.parsers.GenericVariableParser;

public class Test
{
//	public static Console console = new Console();
//
//	static
//	{
//		ArrayList<Command<?>> commands=new ArrayList<Command<?>>();
//		commands.add(new ParseVariableCommand(console, "parsevariable", "Parses input into a variable object. Usage: parse <variable>", 2, -1, Color.white));
//		commands.add(new ParseVariablesCommand(console, "parsevariables", "Parses input into a list of variable objects. Usage: parse <variables>", 2, -1, Color.white));
//		console.loadCommands(commands);
//		console.setVisible(true);
//	}
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		GenericVariableParser parser = new GenericVariableParser();
		sc.useDelimiter(parser.getTerminator());
		while (sc.hasNext())
			System.out.println(parser.parseVariable(sc.next()).details());
	}
}
