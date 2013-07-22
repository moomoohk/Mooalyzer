package com.moomoohk.Mooalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils
{
	public static ArrayList<String> splitWords(String line)
	{
		String beforeParams = line.substring(0, line.indexOf('('));
		Scanner scanner = new Scanner(beforeParams);
		ArrayList<String> temp = new ArrayList<String>();
		while (scanner.hasNext())
			temp.add(scanner.next().trim());
		temp.add("(");
		String params = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
		Scanner paramScanner = new Scanner(params);
		paramScanner.useDelimiter(",");
		while (paramScanner.hasNext())
		{
			scanner = new Scanner(paramScanner.next());
			while (scanner.hasNext())
				temp.add(scanner.next().trim());
			if (paramScanner.hasNext())
				temp.add(",");
		}
		scanner = new Scanner(line.substring(line.indexOf(")") + 1));
		while (scanner.hasNext())
			temp.add(scanner.next());
		return temp;
	}

	public static String getListAsString(List<?> list, boolean boxes, boolean commas)
	{
		String temp = "";
		if (list == null || list.size() == 0)
			return temp;
		for (Object item : list)
			temp += (boxes ? "[" : "") + item.toString() + (boxes ? "]" : "") + (commas ? ", " : " ");
		return temp.substring(0, temp.length() - (commas ? 2 : 1));
	}
}
