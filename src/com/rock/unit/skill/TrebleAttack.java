package com.rock.unit.skill;

import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;

//三线攻击
public class TrebleAttack extends BaseSkill{
	public static TrebleAttack skill=new TrebleAttack();
	
	@Override
	public void setCost() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double excute(BaseUnit executor,BaseUnit target) {
		BaseUnit baseUnit;
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,250);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,270);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,300);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		return executor.getDamege();
	}

}
