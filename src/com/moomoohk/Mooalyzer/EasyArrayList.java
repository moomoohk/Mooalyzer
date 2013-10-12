
package com.moomoohk.Mooalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class EasyArrayList<T> extends ArrayList<T>
{
	public EasyArrayList(String ... params)
	{
		super((Collection<T>) Arrays.asList(params));
	}
}

