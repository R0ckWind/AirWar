package com.rock.unit.behavior;

import com.rock.unit.BaseUnit;

public class RunToPlayer extends BaseBehavior {

	private BaseUnit target;
	public RunToPlayer(BaseUnit unit)
	{
		this.target=unit;
	}
	@Override
	public BaseBehavior action(BaseUnit unit) {
		// TODO Auto-generated method stub
		int movement=10;
		int direct_x=target.getCoordinate_x()-unit.getCoordinate_x();
		int direct_y=target.getCoordinate_y()-unit.getCoordinate_y();
		int target_x;
		int target_y;
		if((direct_x>0&&direct_x<=movement)||(direct_x<0&&direct_x>=movement))
		{
			target_x=target.getCoordinate_x();
		}
		if((direct_y>0&&direct_y<=movement)||(direct_y<0&&direct_y>=movement))
		{
			target_y=target.getCoordinate_y();
		}
		
		if(direct_x>0)
			direct_x=1;
		if(direct_x<0)
			direct_x=-1;
		if(direct_y>0)
			direct_y=1;
		if(direct_y<0)
			direct_y=-1;
		
		target_x=unit.getCoordinate_x()+movement*direct_x;
		target_y=unit.getCoordinate_y()+movement*direct_y;
		unit.setCoordinate_x(target_x);
		unit.setCoordinate_y(target_y);
		if(target_x==unit.getCoordinate_x()&&target_y==unit.getCoordinate_y())
			return null;
		return this;
	}

}
