package com.rock.unit.factory;

import java.util.HashMap;
import java.util.Map;

import com.rock.unit.buff.BuffState;

public class BuffFactory {
	//buff工厂
	private volatile static BuffFactory buffFactory;
	private BuffFactory()
	{
		buffMap=new HashMap<>();
	}
	//map记录下所有的buff
	private Map<String, BuffState> buffMap;
	
	public void putBuffStateIntoBuffMap(BuffState buffState)
	{
		buffMap.put(buffState.getName(), buffState);
	}
	public BuffState createBuffState(String type)
	{
		//如果需要buff就克隆一份
		return buffMap.get(type).cloneSelf();
	}
	public static BuffFactory getBuffFactory()
	{
		if(buffFactory==null)
		{
			synchronized(BuffFactory.class)
			{
				if(buffFactory==null)
				{
					buffFactory=new BuffFactory();
				}
			}
		}
		return buffFactory;
	}
}
