package com.rock.unit.skill;

import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;

//召唤小飞机(战机2)
public class SummonFriend extends BaseSkill{
	
	public static SummonFriend skill=new SummonFriend();
	
	@Override
	public void setCost() {
		// TODO Auto-generated method stub
		manaCost=1;
	}
	@Override
	public double excute(BaseUnit executor, BaseUnit target) {
		
		if(executor.getMP_CUR()<=0)
			return 0;
		//扣除MP
		executor.costMana(manaCost);
		
		BaseUnit baseUnit;
		baseUnit=UnitFactory.getUnitFacory().createBattleUnit("小飞机", executor.getPlayer());
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit.setCoordinate_x(executor.getCoordinate_x()-executor.getSize_w());
		baseUnit.setCoordinate_y(executor.getCoordinate_y()-executor.getSize_h()/2);
		baseUnit=UnitFactory.getUnitFacory().createBattleUnit("小飞机", executor.getPlayer());
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit.setCoordinate_x(executor.getCoordinate_x());
		baseUnit.setCoordinate_y(executor.getCoordinate_y()-executor.getSize_h()/2);
		baseUnit=UnitFactory.getUnitFacory().createBattleUnit("小飞机", executor.getPlayer());
		executor.getPlayer().addUnitToUnitList(baseUnit);
		baseUnit.setCoordinate_x(executor.getCoordinate_x()+executor.getSize_w());
		baseUnit.setCoordinate_y(executor.getCoordinate_y()-executor.getSize_h()/2);
		return 0;
	}

}
