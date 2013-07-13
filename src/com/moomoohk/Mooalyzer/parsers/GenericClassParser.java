package com.moomoohk.Mooalyzer.parsers;

import java.io.File;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.Class;
import com.moomoohk.Mooalyzer.Method;
import com.moomoohk.Mooalyzer.Variable;
import com.moomoohk.Mooalyzer.interfaces.ClassParser;
import com.moomoohk.Mooalyzer.interfaces.Configuration;

public class GenericClassParser implements ClassParser
{
	public Class parseClass(Configuration config, File f)
	{
		//		Scanner scanner = null;
		//		try
		//		{
		//			scanner = new Scanner(f);
		//		}
		//		catch (FileNotFoundException e)
		//		{
		//			e.printStackTrace();
		//			return null;
		//		}
		//		String test = "// States are used to differentiate between various widely different situations\n" + "const int _movement_state = 0; // character is moving on the ground\n" + "const int _ground_state = 1; // character has fallen down or is raising up, ATM ragdolls handle most of this\n"
		//				+ "const int _attack_state = 2; // character is performing an attack\n" + "const int _hit_reaction_state = 3; // character was hit or dealt damage to and has to react to it in some manner\n" + "const int _ragdoll_state = 4; // character is falling in ragdoll mode\n"
		//				+ "int state = _movement_state;\n" +
		//
		//				"bool blinking = false;  // Currently in the middle of blinking?\n" + "float blink_progress = 0.0f; // Progress from 0.0 (pre-blink) to 1.0 (post-blink)\n" + "float blink_delay = 0.0f; // Time until next blink\n" + "float blink_amount = 0.0f; // How open eyes currently are\n" +
		//
		//				"vec3 target_tilt(0.0f);\n" + "vec3 tilt(0.0f);\n" +
		//
		//				"float stance_move_fade = 0.0f;\n" + "float stance_move_fade_val = 0.0f;\n" + "vec3 head_dir;";
		Scanner scanner = new Scanner(System.in);
		int curlyCounter = 0, lineNumber = 0, saveCurlyLevel = 0;
		boolean scanningMethod = false;
		Method method = null;
		Class c = new Class();
		while (scanner.hasNextLine())
		{
			lineNumber++;
			System.out.println();
			String line = scanner.nextLine().trim();
			System.out.println(lineNumber + ": [" + line + "]");
			if (line.equals("STOP;"))
				break;
			boolean isMethod = false;
			if (line.contains("{"))
				curlyCounter++;
			if (line.startsWith(config.getComment()) || line.length() <= 2)
			{
				System.out.println("- Ignoring");
				continue;
			}
			if (line.trim().startsWith(config.getImportDeclaration()))
			{
				System.out.println("- Is an import");
				c.addImport(line.substring(line.trim().indexOf(" ")).trim());
				continue;
			}
			if (line.contains("("))
				if (!line.contains(config.getComment()) && !line.startsWith("if") && curlyCounter == 1 && !line.endsWith(config.getTerminator())
						|| (!line.endsWith(config.getTerminator()) && !line.contains("=") && line.contains("(") && line.contains(config.getComment()) && line.indexOf("(") < line.indexOf(config.getComment())))
				{
					System.out.println("- Possibly a method");
					isMethod = true;
				}
				else
				{
					System.out.println("- Possibly not a method");
					isMethod = false;
				}
			else
			{
				System.out.println("- Not a method");
				isMethod = false;
			}
			System.out.println("Curly count: " + curlyCounter);
			if (line.contains("}"))
			{
				curlyCounter--;
				if (scanningMethod)
				{
					c.addMethod(method);
				}
				scanningMethod = false;
				method = null;
			}
			if (isMethod)
			{
				saveCurlyLevel = curlyCounter;
				scanningMethod = true;
				method = parseMethod(config, line);
			}
			else
			{
				method = null;
				if (curlyCounter != 0 || parseVariable(config, line) == null)
					System.out.println("- Not a variable");
				else
				{
					System.out.println("- Is a variable");
					System.out.println(parseVariable(config, line).details());
					c.addVariable(parseVariable(config, line));
				}
			}
			if (scanningMethod)
				method.addLine(line);
		}
		System.out.println("------------------------------| BROKEN |------------------------------");
		return c;
	}

	private Variable parseVariable(Configuration config, String variable)
	{
		return new GenericVariableParser().parseVariable(config, variable);
	}

	private Method parseMethod(Configuration config, String method)
	{
		return new GenericMethodParser().parseMethod(config, method);
	}
}
