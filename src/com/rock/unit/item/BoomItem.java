package com.rock.unit.item;

import com.rock.camp.BasePlayer;
import com.rock.unit.BaseUnit;
import com.rock.unit.ItemUnit;
import com.rock.unit.factory.UnitFactory.UnitData;

//超级炸弹道具
public class BoomItem extends ItemUnit{

	public BoomItem(UnitData unitData, BasePlayer player, int x, int y, int w, int h) {
		super(unitData, player, x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rewardKiller(BaseUnit unit) {
		unit.recoveryMana(this.HP_CUR);
		
	}

}
