package com.rock.unit.item;

import com.rock.camp.BasePlayer;
import com.rock.unit.BaseUnit;
import com.rock.unit.ItemUnit;
import com.rock.unit.factory.UnitFactory.UnitData;

//积分道具
public class ScoreItem extends ItemUnit{

	public ScoreItem(UnitData unitData, BasePlayer player, int x, int y, int w, int h) {
		super(unitData, player, x, y, w, h);
	}

	@Override
	public void rewardKiller(BaseUnit unit) {
		// TODO Auto-generated method stub
		unit.getPlayer().increaseScore((int)this.HP_MAX);
	}

}
