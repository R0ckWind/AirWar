package com.rock.util;

import java.util.Random;

//随机数生成工具
public class RandomGenerator {
	private Random random;
	public RandomGenerator()
	{
		random=new Random();
	}
	//获得随机整数，闭区间[start,end]
	public int nextInt(int start,int end)
	{
		return random.nextInt(end-start+1)+start;
	}
	//从数组中选其一
	public int pickOneFrom(int... a)
	{
		int size=a.length;
		return a[random.nextInt(size)];
	}
	//根据概率返回随机结果
	public boolean randomEvent(double p)
	{
		return random.nextDouble()<p;
	}
}
