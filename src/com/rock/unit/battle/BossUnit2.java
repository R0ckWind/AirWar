package com.rock.unit.battle;

import com.rock.UI.GameFrame;
import com.rock.camp.BasePlayer;
import com.rock.parameter.SystemParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.behavior.BuildBehavior;
import com.rock.unit.factory.UnitFactory;
import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.unit.skill.ColiisionAttack;
import com.rock.unit.skill.NineTimesAttack;
import com.rock.unit.skill.TrebleAttack;
import com.rock.util.ClockCounter;

public class BossUnit2 extends BossUnit{
	
	private ClockCounter satackClock;
	public BossUnit2(UnitData unitData, BasePlayer player) {
		super(unitData, player);
		satackClock=new ClockCounter(6,2);
	}
	@Override
	public void attack()
	{
		if(attackClock.isRing())
		{
			if(satackClock.isRing())
			{
				//概率发出九线攻击或冲撞攻击
				if(random.randomEvent(0.7))
					specialAttack(NineTimesAttack.skill);
				else 
				{
					BaseUnit playerUnit =GameFrame.getGameFrame().getRunningParameter().getPlayerUnit();
					specialAttack(ColiisionAttack.skill,playerUnit);
				}
				satackClock.cooldown();
			}
			//默认三线攻击
			specialAttack(TrebleAttack.skill);
			attackClock.cooldown();
			satackClock.run();
		}
	}
}
