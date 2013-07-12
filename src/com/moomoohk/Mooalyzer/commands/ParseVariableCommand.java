
package com.moomoohk.Mooalyzer.commands;

import java.awt.Color;

import com.moomoohk.Mooalyzer.Variable;
import com.moomoohk.Mootilities.MooCommands.Command;
import com.moomoohk.Mootilities.MooConsole.Console;

public class ParseVariableCommand extends Command<Console>
{

	public ParseVariableCommand(Console handler, String command, String help, int minParams, int maxParams, Color outputColor)
	{
		super(handler, command, help, minParams, maxParams, outputColor);
	}

	@Override
	public void execute(Console handler, String[] params)
	{
		handler.addText(Variable.parseVariable(Command.stringParams(params, 0)).details());
	}
}

