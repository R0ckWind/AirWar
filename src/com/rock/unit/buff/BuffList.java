package com.rock.unit.buff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuffList {
	/**
	 * 状态列表
	 */
	private List<BuffState> buffList;//状态列表

	public BuffList()
	{
		this.buffList=new ArrayList<>(6);
	}
	//更新状态时间
	public void run()
	{
		//使用迭代器可以实现遍历删除
		Iterator<BuffState> iterator = buffList.iterator();  
        while (iterator.hasNext()) {  
        	BuffState state = iterator.next();  
        	int duration=state.getDuration();
            if (duration<=0) {  
                iterator.remove();//将元素从列表中删除
            }  
            //每一个状态的持续时间都减1
            state.setDuration(--duration);
        }  
	}
	
	public void add(BuffState buff)
	{
		buffList.add(buff);
	}
}
