package com.rock.unit.factory;

import java.util.ArrayList;
import java.util.List;

import com.rock.camp.BasePlayer;
import com.rock.unit.BaseUnit;
import com.rock.util.RandomGenerator;


public class DieDrop {
	public static RandomGenerator random=new RandomGenerator();
	public List<String> dropList;
	private BasePlayer player;
	private int num;
	
	public DieDrop(String[] drop,BasePlayer player)
	{
		num=0;
		dropList=new ArrayList<>();
		this.player=player;
		for(int i=0;i<drop.length;i++)
		{
			String []arr=drop[i].split("\\*");
			double probability=Double.valueOf(arr[1]);
			
			if(random.randomEvent(probability))
				dropList.add(arr[0]);
				num+=1;
				
		}
	}
	public List<BaseUnit> drop(int x,int y,int w,int h)
	{
		List<BaseUnit> result=new ArrayList<>(num);
		int size=dropList.size();
		for(int i=0;i<size;i++)
		{
			BaseUnit unit=UnitFactory.getUnitFacory()
					.createItemUnit(dropList.get(i), player,x,y,w,h);
			result.add(unit);
		}
		return result;
	}
	
	
}
