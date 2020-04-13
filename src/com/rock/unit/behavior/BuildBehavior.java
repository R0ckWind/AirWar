package com.rock.unit.behavior;

import com.rock.parameter.GameParameter;
import com.rock.unit.BaseUnit;

//出生行为(从地图之外飞行到地图之内)
public class BuildBehavior extends BaseBehavior{
	private int start_x;
	private int start_y;
	private int target_x;
	private int target_y;
	private int direct_x;
	private int direct_y;

	public BuildBehavior(int start_x,int start_y,int target_x,int target_y) {
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
		// TODO Auto-generated method stub
		int movement=unit.getMovement();
		int next_x=start_x+movement*direct_x;
		start_x=next_x;
		if(next_x>=target_x&&next_x<target_x+movement)
		{
			next_x=target_x;
			direct_x=0;
		}
		if(next_x<target_x&&next_x>target_x-movement)
		{
			next_x=target_x;	
			direct_x=0;
		}
		int next_y=start_y+movement*direct_y;
		start_y=next_y;
		if(next_y>=target_y&&next_y<target_y+movement)
		{
			next_y=target_y;
			direct_y=0;
		}
		if(next_y<target_y&&next_y>target_y-movement)
		{
			next_y=target_y;
			direct_y=0;
		}
		unit.setCoordinate_x(next_x);
		unit.setCoordinate_y(next_y);
		if(next_x==target_x&&next_y==target_y)
		{
			unit.setState(GameParameter.UnitState_Alive);
			return null;
		}
		return this;
	}	
}
