package com.moomoohk.Mooalyzer.CSharp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.CSharp.CSharpComment.CSharpCommentType;
import com.moomoohk.Mooalyzer.CSharp.CSharpConstructor.CSharpConstructorType;

public class CSharpParser
{
	public static ArrayList<CSharpElement> elements;

	public static final int METHOD_LEVEL = 2;

	public static long parse(ArrayList<String> lines)
	{
		elements = new ArrayList<CSharpElement>();
		long start = System.currentTimeMillis();

		return System.currentTimeMillis() - start;
	}

	public static ArrayList<String> splitLine(String line)
	{
		ArrayList<String> split = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(line.substring(0, line.indexOf("(")));
		while (scanner.hasNext())
			split.add(scanner.next());
		String params = line.substring(line.indexOf("("));
		ArrayList<String> temp = new ArrayList<String>();
		for (char c : params.toCharArray())
		{
			if (c == ',' || c == ':' || c == '(' || c == ')' || c == ';')
			{
				if (builder.length() > 0)
					temp.add(builder.toString().trim());
				temp.add("" + c);
				builder = new StringBuilder();
			}
			else
				builder.append(c);
		}
		builder = new StringBuilder();
		for (String param : temp)
		{
			if (param.equals(",") || param.equals(":") || param.equals("(") || param.equals(")") || param.equals(";"))
			{
				if (builder.length() > 0)
					split.add(builder.toString().trim());
				split.add(param);
				builder = new StringBuilder();
			}
			else
				builder.append(param + " ");
		}
		return split;
	}

	public static ArrayList<String> cutList(ArrayList<String> list, int startIndex, int endIndex)
	{
		if (startIndex < 0 || startIndex >= list.size() || endIndex < startIndex || endIndex >= list.size())
			return null;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = startIndex; i <= endIndex; i++)
			temp.add(list.get(i));
		return temp;
	}

	public static int indexOfString(ArrayList<String> lines, String s)
	{
		return indexOfString(lines, s, -1, -1);
	}

	public static int indexOfString(ArrayList<String> lines, String s, int curlyLevel, int bracketLevel)
	{
		int cl = 0, bl = 0;
		if (lines != null)
			for (int i = 0; i < lines.size(); i++)
			{
				for (char c : lines.get(i).toCharArray())
				{
					if (c == '{')
						cl++;
					if (c == '(')
						bl++;
				}
				System.out.println(lines.get(i));
				System.out.println(cl + " " + bl + " " + curlyLevel + " " + bracketLevel);
				System.out.println(lines.get(i).contains(s));
				System.out.println(lines.get(i).contains(s) && (curlyLevel >= 0 ? cl == curlyLevel : true) && (bracketLevel >= 0 ? bl == bracketLevel : true));
				if (lines.get(i).contains(s) && (curlyLevel >= 0 ? cl == curlyLevel : true) && (bracketLevel >= 0 ? bl == bracketLevel : true))
					return i;
				for (char c : lines.get(i).toCharArray())
				{
					if (c == '}')
						cl--;
					if (c == ')')
						bl--;
				}
			}
		return -1;
	}

	public static void parseComments(ArrayList<String> lines)
	{
		ArrayList<String> save = lines;
		int commentBlockCount = 0, inlineCommentCount = 0, position = 1;
		for (int i = indexOfString(save, "/*"); i != -1; i = indexOfString(save, "/*"))
		{
			position += i;
			int add = 0;
			ArrayList<String> commentLines = new ArrayList<String>();
			for (int j = i; j <= indexOfString(save, "*/"); j++)
				if (save.get(j).replace("/*", "").replace("*/", "").replace("*", "").trim().length() > 0)
				{
					commentLines.add(save.get(j).replace("/*", "").replace("*/", "").replace("*", "").replace("\t", "").trim());
					//					System.out.println(save.get(j).replace("/*", "").replace("*/", "").replace("*", "").replace("\t", "").trim());
				}
				else
				{
					if (save.get(j).contains("/*"))
						add++;
					if (save.get(j).contains("*/"))
						add++;
				}
			commentBlockCount++;
			elements.add(new CSharpComment(commentLines, CSharpCommentType.BLOCK, position));
			position += commentLines.size() + add;
			save = cutList(save, indexOfString(save, "*/") + 1, save.size() - 1);
		}
		save = lines;
		position = 0;
		for (int i = indexOfString(save, "//"); indexOfString(save, "//") != -1; i = indexOfString(save, "//"))
		{
			position += i;
			if (!save.get(i).trim().startsWith("*") && !save.get(i).trim().startsWith("/*") && save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim().length() != 0)
			{
				position++;
				inlineCommentCount++;
				ArrayList<String> line = new ArrayList<String>();
				line.add(save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim());
				elements.add(new CSharpComment(line, CSharpCommentType.INLINE, position));
			}
			save = cutList(save, i + 1, save.size() - 1);
		}
	}

	//	public static void parseMethods(ArrayList<String> lines) //TODO: Same line curlies
	//	{
	//		ArrayList<String> save = lines;
	//		int position = 1;
	//		for (int i = indexOfString(save, "(", METHOD_LEVEL, 2); indexOfString(save, "(", METHOD_LEVEL, 2) != -1; i = indexOfString(save, "(", METHOD_LEVEL, 2))
	//		{
	//			position += i;
	//			String line = save.get(i);
	//			System.out.println(line);
	//			if (!line.contains("="))
	//			{
	//				ArrayList<String> split = splitLine(line);
	//				ArrayList<String> modifiers = new ArrayList<String>();
	//				ArrayList<String> parameters = parseParameters(split);
	//				String name = split.get(split.indexOf("(") - 1);
	//				for (int j = 0; j < split.indexOf("(") - 1; j++)
	//					modifiers.add(split.get(j));
	//				if (split.get(split.size() - 1).equals(";"))
	//				{
	//					elements.add(new CSharpAbstractMethod(modifiers, name, parameters, position));
	//					position += 1;
	//				}
	//				else
	//					if (split.contains(":")) //TODO: Fix base and this params
	//					{
	//						CSharpConstructor constructor = null;
	//						if (split.get(split.indexOf(":") + 1).equals("base"))
	//							constructor = new CSharpConstructor(modifiers, name, parameters, CSharpConstructorType.BASE, position);
	//						if (split.get(split.indexOf(":") + 1).equals("this"))
	//							constructor = new CSharpConstructor(modifiers, name, parameters, CSharpConstructorType.THIS, position);
	//						ArrayList<String> methodLines = cutList(save, indexOfString(save, "{", METHOD_LEVEL, -1), indexOfString(save, "}", METHOD_LEVEL, -1));
	//						int add = 0;
	//						//					System.out.println(line);
	//						//					printList(methodLines);
	//						for (String s : methodLines)
	//						{
	//							constructor.addLine(s);
	//							add++;
	//						}
	//						elements.add(constructor);
	//						position += 1 + add;
	//					}
	//					else
	//					{
	//						CSharpMethod method = new CSharpMethod(modifiers, name, parameters, position);
	//						ArrayList<String> methodLines = cutList(save, indexOfString(save, "{", METHOD_LEVEL, -1), indexOfString(save, "}", METHOD_LEVEL, -1));
	//						int add = 0;
	//						for (String s : methodLines)
	//						{
	//							method.addLine(s); //TODO: Sort out curlies getting added
	//							add++;
	//						}
	//						elements.add(method);
	//						position += 1 + add;
	//					}
	//			}
	//			save = cutList(save, i + 1, save.size() - 1);
	//		}
	//	}

	public static ArrayList<String> parseParameters(ArrayList<String> split)
	{
		ArrayList<String> parameters = new ArrayList<String>();
		StringBuilder param = new StringBuilder();
		for (int j = split.indexOf("(") + 1; j < split.indexOf(")"); j++)
		{
			if (!split.get(j).equals(","))
				param.append(split.get(j) + " ");
			else
			{
				parameters.add(param.toString().trim());
				param = new StringBuilder();
			}
		}
		parameters.add(param.toString().trim());
		return parameters;
	}

	public static void parseVariables(ArrayList<String> lines)
	{
		ArrayList<String> save = lines;
		for (int i = indexOfString(save, "", METHOD_LEVEL - 1, -1); indexOfString(save, "", METHOD_LEVEL - 1, -1) != -1; i = indexOfString(save, "", METHOD_LEVEL - 1, -1))
		{
			// if has brackets must have equal sign
			String line = save.get(i);
			if (line.contains("="))
			{
				//				System.out.println(line);
			}
			save = cutList(save, i + 1, save.size() - 1);
		}
	}

	public static void printElementList()
	{
		System.out.println(elements.size() + " elements");
		for (CSharpElement element : elements)
		{
			System.out.println("Line " + element.getLine() + ": " + element.getClass().getSimpleName() + ": " + element.getName());
			//			if (element instanceof CSharpMethod)
			//				System.out.println(((CSharpMethod) element).lines());
			System.out.println(element.toString() + "\n-");
		}
	}

	public static void sortElements()
	{
		Collections.sort(elements, new Comparator<CSharpElement>()
		{
			@Override
			public int compare(CSharpElement arg0, CSharpElement arg1)
			{
				return arg0.getLine() - arg1.getLine();
			}
		});
	}

	public static String fixCurlies(File f)
	{
		String fixed = "";
		try
		{
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				line = line.replace("{", "\n{\n");
				line = line.replace("}", "\n}\n");
				fixed += "\n" + line;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return fixed;
	}

	public static CSharpClass parseClass(String header, String classBlock)
	{
		String name = null;
		ArrayList<String> modifiers = new ArrayList<String>(), inherits = new ArrayList<String>();
		Scanner scanner = new Scanner(header);
		boolean reachedName = false, reachedColon = false;
		while (scanner.hasNext())
		{
			String next = scanner.next();
			if (next.equals("class"))
			{
				name = scanner.next();
				if (name.contains(":"))
				{
					reachedColon = true;
					if (!name.endsWith(":"))
						inherits.add(name.substring(name.indexOf(":")).replace(":", "").replace(",", ""));
					name = name.substring(0, name.indexOf(":"));
				}
				reachedName = true;
			}
			if (next.contains(":"))
				reachedColon = true;
			if (!reachedName)
				modifiers.add(next);
			if (reachedColon && !next.equals("class"))
				inherits.add(next);
		}
		CSharpClass cSharpClass = new CSharpClass(modifiers, name, inherits);
		System.out.println(cSharpClass.details() + "\n");
		return cSharpClass;
	}

	public static CSharpNamespace parseNamespace(String header, String namespaceBlock)
	{
		CSharpNamespace cSharpNamespace = new CSharpNamespace(header.substring("namespace".length()).trim());
		Scanner scanner = new Scanner(namespaceBlock);
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			if (line.trim().length() == 0)
				continue;
			if (line.contains("class"))
			{
				String block = closeBlock(namespaceBlock.substring(line.length() + 1).trim());
				cSharpNamespace.addClass(parseClass(line, block));
				namespaceBlock = namespaceBlock.substring(block.length() + 1).trim();
			}
		}
		return cSharpNamespace;
	}

	public static CSharpFile parseFile(String file)
	{
		CSharpFile cSharpFile = new CSharpFile();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			if (line.trim().length() == 0)
				continue;
			if (line.contains("namespace"))
			{
				String block = closeBlock(file.substring(line.length() + 1).trim());
				cSharpFile.addNamespace(parseNamespace(line, block));
				file = file.substring(block.length() + 1).trim();
			}
		}
		return cSharpFile;
	}

	public static String closeBlock(String block)
	{
		int level = 0;
		for (int i = 0; i < block.length(); i++)
		{
			char c = block.charAt(i);
			if (c == '{')
				level++;
			if (c == '}')
			{
				level--;
				if (level == 0)
					return block.substring(0, i + 1);
			}
		}
		return block;
	}

	public static void main(String[] args)
	{
		String cSharpFile = fixCurlies(new File("Test CSharp file"));
		System.out.println(parseFile(cSharpFile));
		//		//		ArrayList<String> lines = new ArrayList<String>();
		//		Scanner scanner = new Scanner(fixCurlies(new File("Test CSharp file")));
		//		String everything = "";
		//		while (scanner.hasNext())
		//			everything += scanner.next() + " ";
		//		//		System.out.println(everything);
		//
		//		String temp = everything;
		//		int level = 1, newI = temp.indexOf("{");
		//		boolean condition = temp.indexOf("{") != -1;
		//		for (int i = temp.indexOf("{"); condition; i = newI)
		//		{
		//			if (level <= 3)
		//			{
		//				System.out.println("Level: " + level);
		//				if (temp.charAt(0) == '{')
		//					temp = temp.substring(1).trim();
		//				String lineBefore = temp.substring(0, temp.indexOf("{"));
		//				if (lineBefore.contains(";"))
		//					lineBefore = lineBefore.substring(lineBefore.lastIndexOf(";") + 1);
		//				System.out.println("Before: " + lineBefore.trim());
		//				System.out.println("Temp: " + temp);
		//				System.out.println(temp.indexOf("{") + " " + temp.indexOf("}"));
		//				String toEnd = temp.substring(temp.indexOf("{"), temp.indexOf("}") == temp.length() ? temp.indexOf("}") : temp.indexOf("}") + 1);
		//				//				System.out.println((toEnd.length() - toEnd.replace("{", "").length()) + " " + (toEnd.length() - toEnd.replace("}", "").length()));
		//				if (toEnd.length() - toEnd.replace("{", "").length() == toEnd.length() - toEnd.replace("}", "").length())
		//					level--;
		//				//				System.out.println(toEnd);
		//				condition = temp.indexOf("{") != -1;
		//				newI = temp.indexOf("{");
		//				level++;
		//				temp = temp.substring(i).trim();
		//			}
		//			else
		//			{
		//				temp = temp.substring(i).trim();
		//				level--;
		//				condition = temp.indexOf("}") != -1;
		//				newI = temp.indexOf("}");
		//			}
		//			System.out.println("-");
		//		}
		//			lines.add(scanner.nextLine());
		//		//		printList(lines);
		//		//		PrintStream out = System.out;
		//		//		System.setOut(new DebugOutput(System.out));
		//		long time = parse(lines);
		//		//		System.setOut(out);
		//		System.out.println("-");
		//		System.out.println("Parsed in " + time + "ms");
		//		System.out.println("-");
		//		sortElements();
		//		mapElements();
		//		printElementList();
		CSharpFile file = new CSharpFile();
		CSharpNamespace namespace1 = new CSharpNamespace("namespace1");
		file.addNamespace(namespace1);
		ArrayList<String> modifiers = new ArrayList<String>();
		modifiers.add("public");
		CSharpClass class1 = new CSharpClass(modifiers, "class1", null);
		namespace1.addClass(class1);
		CSharpVariable var1 = new CSharpVariable(null, "var var1", "value");
		CSharpVariable var2 = new CSharpVariable("var var2");
		CSharpMethod method1 = new CSharpMethod("void method1");
		method1.addLine("this is a test line;");
		class1.addVariable(var1);
		class1.addVariable(var2);
		class1.addMethod(method1);
		CSharpNamespace namespace2 = new CSharpNamespace("namespace2");
		CSharpClass class2 = new CSharpClass(null, "class2", null);
		namespace2.addClass(class2);
		file.addNamespace(namespace2);
		//		System.out.println(file.toString());
	}
}