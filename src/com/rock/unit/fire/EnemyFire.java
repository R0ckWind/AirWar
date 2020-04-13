package com.rock.unit.fire;

import com.rock.unit.BaseUnit;
import com.rock.unit.FireUnit;
import com.rock.unit.factory.UnitFactory.UnitData;

public class EnemyFire extends FireUnit{
	
	//敌人的子弹，主要区别在于发出的位置靠下
	public EnemyFire(UnitData unitData, BaseUnit unit, boolean critHit,int angle) {
		super(unitData, unit, critHit,angle);
		coordinate_y=coordinate_y+unit.getSize_h()/2;
	}
}
