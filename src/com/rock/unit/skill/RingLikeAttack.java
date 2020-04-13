package com.rock.unit.skill;

import com.rock.unit.BaseUnit;
import com.rock.unit.FireUnit;
import com.rock.unit.factory.UnitFactory;

//环形攻击(战机1)
public class RingLikeAttack extends BaseSkill{
	public static RingLikeAttack skill=new RingLikeAttack();
	
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
		FireUnit fireUnit;
		for(int i=0;i<360;i+=10)
		{
			fireUnit=UnitFactory.getUnitFacory().createFireForUnit(executor,false,i);
			fireUnit.setDamege(30);
			fireUnit.setPermeable(true);
			executor.getPlayer().addUnitToUnitList(fireUnit);
		}
		return 0;
	}

}
