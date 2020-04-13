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
import com.rock.util.ClockCounter;

public class BossUnit extends EnemyUnit{

	private ClockCounter satackClock;
	public BossUnit(UnitData unitData, BasePlayer player) {
		super(unitData, player);
		satackClock=new ClockCounter(6,2);
	}
	
	//初始化出生点并添加出生行为
	@Override
	public void build() {
		int build_x=random.nextInt(-100, 100+SystemParameter.InterfaceW)-size_w;
		int build_y=random.nextInt(-100, 0)-size_h;
		int target_x=random.nextInt(0, 200)+size_w;
		int target_y=random.nextInt(0, 100)+size_h;
		this.coordinate_x=build_x;
		this.coordinate_y=build_y;
		addBehavior(new BuildBehavior(build_x,build_y,target_x,target_y));
	}
	@Override
	public void attack()
	{
		if(attackClock.isRing())
		{
			if(satackClock.isRing())
			{
				specialAttack(NineTimesAttack.skill);
				
//				BaseUnit playerUnit =GameFrame.getGameFrame().getRunningParameter().getPlayerUnit();
//				specialAttack(ColiisionAttack.skill,playerUnit);
				satackClock.cooldown();
			}
			BaseUnit baseUnit=UnitFactory.getUnitFacory().createFireForUnit(this,false,270);
			this.getPlayer().addUnitToUnitList(baseUnit);
			attackClock.cooldown();
			satackClock.run();
		}
	}
}
