package com.rock.unit.skill;

import com.rock.unit.BaseUnit;

import com.rock.unit.behavior.SpurtBehavior;
//冲撞攻击
public class ColiisionAttack extends BaseSkill{
	public static ColiisionAttack skill=new ColiisionAttack();
	
	@Override
	public void setCost() {
		
	}
	
	@Override
	public double excute(BaseUnit executor,BaseUnit target) {
		int time=(target.getCoordinate_y()-executor.getCoordinate_y())/4;
		if(time<100) time=100;
		buildSpurt(executor,target.getCoordinate_x(),target.getCoordinate_y(),time);
		return executor.getDamege()*5;	
	}
	private void buildSpurt(BaseUnit unit,int target_x,int target_y,int lastTime)
	{
		//分为：缓慢启动(10%)、速度飙升(20%)、静止(30%)、匀速返回(40%)
		int start_x=unit.getCoordinate_x();
		int start_y=unit.getCoordinate_y();
		int distance_x=target_x-start_x;
		int distance_y=target_y-start_y;
		int next_x=start_x+(int)(distance_x*0.2);
		int next_y=start_y+(int)(distance_y*0.2);
		int part1=(int)(lastTime*0.1);
		unit.addBehavior(new SpurtBehavior(start_x,start_y,next_x,next_y,part1));
		
		start_x=next_x;
		start_y=next_y;
		next_x=target_x;
		next_y=target_y;
		int part2=(int)(lastTime*0.2);
		unit.addBehavior(new SpurtBehavior(start_x,start_y,next_x,next_y,part2));
		
		start_x=next_x;
		start_y=next_y;
		next_x=target_x;
		next_y=target_y;
		int part3=(int)(lastTime*0.3);
		unit.addBehavior(new SpurtBehavior(start_x,start_y,next_x,next_y,part3));
		
		start_x=next_x;
		start_y=next_y;
		next_x=unit.getCoordinate_x();
		next_y=unit.getCoordinate_y();
		int part4=(int)(lastTime*0.4);
		unit.addBehavior(new SpurtBehavior(start_x,start_y,next_x,next_y,part4));
	}

	
}
