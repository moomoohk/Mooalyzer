package com.moomoohk.Mooalyzer.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.moomoohk.Mooalyzer.Variable;
import com.moomoohk.Mootilities.MooCommands.Command;
import com.moomoohk.Mootilities.MooConsole.Console;

public class ParseVariablesCommand extends Command<Console>
{

	public ParseVariablesCommand(Console handler, String command, String help, int minParams, int maxParams, Color outputColor)
	{
		super(handler, command, help, minParams, maxParams, outputColor);
	}

	@Override
	public void execute(Console handler, String[] params)
	{
		for (Variable v : Variable.parseVariables(new ArrayList<String>(Arrays.asList(params))))
			handler.addText(v.details()+"\n");
	}
}
