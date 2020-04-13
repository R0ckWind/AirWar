package com.rock.unit.battle;

import com.rock.UI.GameFrame;
import com.rock.camp.BasePlayer;
import com.rock.parameter.RunningParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.BattleUnit;
import com.rock.unit.behavior.BuildBehavior;
import com.rock.unit.factory.UnitFactory;
import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.unit.skill.RingLikeAttack;


public class PlayerUnit extends BattleUnit{
	
	public PlayerUnit(UnitData unitData, BasePlayer player) {
		super(unitData, player);
	}
	
	@Override
	public void build() {
		int target_x=SystemParameter.InterfaceW/2;
		int target_y=(int)(SystemParameter.InterfaceH-1.5*size_h);
		int build_x=target_x;
		int build_y=target_y+150;
		coordinate_x=build_x;
		coordinate_y=build_y;
		addBehavior(new BuildBehavior(build_x,build_y,target_x,target_y));
		defaultSkill=RingLikeAttack.skill;
	}
	@Override
	public void move()
	{
		RunningParameter runningParm=GameFrame.getGameFrame().getRunningParameter();
		int offsetX=runningParm.getDirectionX()*movement;
		int offsetY=runningParm.getDirectionY()*movement;
		coordinate_x+=offsetX;
		coordinate_y+=offsetY;
		CorrentOverBorder();
	}
	@Override
	public void attack()
	{
		RunningParameter runningParm=GameFrame.getGameFrame().getRunningParameter();
		if(runningParm.isAttack())
		{
			//可以攻击
			if(attackClock.isRing())
			{
				boolean crit=random.randomEvent(this.critChance);
				BaseUnit baseUnit=UnitFactory.getUnitFacory().createFireForUnit(this,crit,90);
				this.getPlayer().addUnitToUnitList(baseUnit);
				attackClock.cooldown();
			}
		}
	}


	@Override
	public double attack(BaseUnit target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void beHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rewardKiller(BaseUnit unit) {
		// TODO Auto-generated method stub
		
	}


}
