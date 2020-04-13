package com.rock.unit.item;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.ItemUnit;
import com.rock.unit.battle.PlayerUnit;
import com.rock.unit.factory.UnitFactory.UnitData;

//回血道具
public class RestorerItem extends ItemUnit{

	public RestorerItem(UnitData unitData, BasePlayer player, int x, int y, int w, int h) {
		super(unitData, player, x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beHitBy(BaseUnit unit) {
	
		if(unit instanceof PlayerUnit)
		{
			//满血不能捡血药
			if(unit.getHP_CUR()<unit.getHP_MAX())
			{
				this.state=GameParameter.UnitState_Dead;
				rewardKiller(unit);
			}
			
		}
	}
	
	@Override
	public void rewardKiller(BaseUnit unit) {
		// TODO Auto-generated method stub
		unit.gainHeal(this.HP_MAX);
	}

}
