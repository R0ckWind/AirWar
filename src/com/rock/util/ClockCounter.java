package com.rock.util;
//时钟计数器
public class ClockCounter {
	private int capTime;
	private int presentTime;
	private boolean ring;//true时可用
	public ClockCounter(int cap)
	{
		this.capTime=cap;
		this.presentTime=0;
		this.ring=true;
	}
	//未激活，并设置初始时间
	public ClockCounter(int cap,int activation)
	{
		
		this.capTime=cap;
		this.presentTime=activation;
		this.ring=false;
	}
	public void run()
	{
		if(ring)
			return;
		//不可用时，时间增加
		presentTime++;
		if(presentTime>=capTime)
		{
			ring=true;
			presentTime=0;
		}	

	}
	public boolean isRing()
	{
		return ring;
	}
	//进入冷却
	public void cooldown()
	{
		ring=false;
	}
	//获取当前时间
	public int getPresent()
	{
		return presentTime;
	}
}
