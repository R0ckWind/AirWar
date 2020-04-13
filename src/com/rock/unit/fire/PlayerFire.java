package com.rock.unit.fire;

import com.rock.unit.BaseUnit;
import com.rock.unit.FireUnit;
import com.rock.unit.factory.UnitFactory.UnitData;

public class PlayerFire extends FireUnit{

	//玩家的子弹，发出的位置靠上
	public PlayerFire(UnitData unitData,BaseUnit unit,boolean critHit,int angle)
	{
		super(unitData, unit, critHit,angle);
		coordinate_y=coordinate_y-unit.getSize_h()/2;
	}
}
