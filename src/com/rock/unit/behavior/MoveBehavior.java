package com.rock.unit.behavior;

import com.rock.unit.BaseUnit;

//移动行为(移速相关，y轴速度减半，到达目标地址停止)
public class MoveBehavior extends BaseBehavior{

	private int start_x;
	private int start_y;
	private int target_x;
	private int target_y;
	private int direct_x;
	private int direct_y;
	
	public MoveBehavior(int start_x,int start_y,int target_x,int target_y) {
		this.start_x=start_x;
		this.start_y=start_y;
		this.target_x=target_x;
		this.target_y=target_y;
		if(this.start_x>this.target_x)
			direct_x=-1;
		else if(this.start_x<this.target_x) 
			direct_x=1;
		else direct_x=0;
		if(this.start_y>this.target_y)
			direct_y=-1;
		else if(this.start_y<this.target_y) 
			direct_y=1;
		else direct_y=0;
	}
	@Override
	public BaseBehavior action(BaseUnit unit) {
		int movement_x=unit.getMovement();
		int movement_y=movement_x/2;
		if(movement_y<=0)
			movement_y=1;
		int next_x=start_x+movement_x*direct_x;
		start_x=next_x;
		if(next_x>=target_x&&next_x<target_x+movement_x)
		{
			next_x=target_x;
			direct_x=0;
		}
		if(next_x<target_x&&next_x>target_x-movement_x)
		{
			next_x=target_x;	
			direct_x=0;
		}

		int next_y=start_y+movement_y*direct_y;
		start_y=next_y;
		if(next_y>=target_y&&next_y<target_y+movement_y)
		{
			next_y=target_y;
			direct_y=0;
		}
		if(next_y<target_y&&next_y>target_y-movement_y/2)
		{
			next_y=target_y;
			direct_y=0;
		}
		unit.setCoordinate_x(next_x);
		unit.setCoordinate_y(next_y);
		unit.attack();
		if(next_x==target_x&&next_y==target_y)
			return null;
		return this;
	}

}
