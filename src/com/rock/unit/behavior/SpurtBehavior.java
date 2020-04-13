package com.rock.unit.behavior;

import com.rock.unit.BaseUnit;

//冲刺行为(无关移速的直线运动)
public class SpurtBehavior extends BaseBehavior{
	private int item_x;
	private int item_y;
	private int lastTime;
	public SpurtBehavior() {
		
	}
	public SpurtBehavior(int start_x,int start_y,int target_x,int target_y,int lastTime) {
		this.lastTime=lastTime;
		this.item_x=(target_x-start_x)/lastTime;
		this.item_y=(target_y-start_y)/lastTime;
	}
	
	@Override
	public BaseBehavior action(BaseUnit unit) {
		// TODO Auto-generated method stub
		int next_x=unit.getCoordinate_x()+item_x;
		int next_y=unit.getCoordinate_y()+item_y;
		unit.setCoordinate_x(next_x);
		unit.setCoordinate_y(next_y);
		if(lastTime--<0)
			return null;
		return this;
	}	
}
