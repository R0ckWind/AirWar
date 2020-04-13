package com.rock.unit.battle;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.BattleUnit;
import com.rock.unit.factory.UnitFactory;
import com.rock.unit.factory.UnitFactory.UnitData;

public class SummonUnit extends BattleUnit{

	public SummonUnit(UnitData unitData, BasePlayer player) {
		super(unitData, player);
		this.state=GameParameter.UnitState_Alive;
	}

	//修正越界的情况
	protected void CorrentOverBorder()
	{
		if(coordinate_y<size_h/2||coordinate_y>borderY)
			this.state=GameParameter.UnitState_Dead;
	}
	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		if(this.state==GameParameter.UnitState_Alive)
		if(attackClock.isRing())
		{
			BaseUnit baseUnit=UnitFactory.getUnitFacory().createFireForUnit(this,false,90);
			this.getPlayer().addUnitToUnitList(baseUnit);
			attackClock.cooldown();
		}
	}

	@Override
	public double attack(BaseUnit target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		coordinate_y-=movement;
		CorrentOverBorder();
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
