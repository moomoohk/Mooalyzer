package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpComment extends CSharpElement
{
	public static enum CSharpCommentType
	{
		BLOCK, INLINE;
	}

	private CSharpCommentType type;
	private ArrayList<String> lines;

	public CSharpComment(ArrayList<String> lines, CSharpCommentType type)
	{
		super(null, lines.get(0).length() > 5 ? lines.get(0).substring(0, 5) + "..." : lines.get(0) + (lines.size() > 1 ? " " + lines.get(1) : "") + (lines.size() > 2 ? "..." : ""));
		this.lines = lines;
		this.type = type;
	}

	public CSharpCommentType getType()
	{
		return this.type;
	}

	public ArrayList<String> getLines()
	{
		return this.lines;
	}

	public String toString()
	{
		String st = "";
		switch (this.type)
		{
		case BLOCK:
			st += "/*\n";
			for (String line : this.lines)
				st += "* " + line + "\n";
			st += "*/";
			break;
		case INLINE:
			st = "// " + this.lines.get(0);
			break;
		}
		return st;
	}
}
