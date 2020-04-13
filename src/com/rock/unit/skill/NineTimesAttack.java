package com.rock.unit.skill;

import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;

//九线攻击
public class NineTimesAttack extends BaseSkill{
	public static NineTimesAttack skill=new NineTimesAttack();
	@Override
	public void setCost() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double excute(BaseUnit executor,BaseUnit target) {
		BaseUnit baseUnit;
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,190);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,210);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,230);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,250);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,270);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,290);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,310);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,330);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,350);
		executor.getPlayer().addUnitToUnitList(baseUnit);
		return executor.getDamege();	
	}
}
