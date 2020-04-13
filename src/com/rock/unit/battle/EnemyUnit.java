package com.rock.unit.battle;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.BattleUnit;
import com.rock.unit.behavior.BuildBehavior;
import com.rock.unit.behavior.MoveBehavior;
import com.rock.unit.factory.DieDrop;
import com.rock.unit.factory.UnitFactory;
import com.rock.unit.factory.UnitFactory.UnitData;

public class EnemyUnit extends BattleUnit{


	public EnemyUnit(UnitData unitData, BasePlayer player) {
		super(unitData, player);
		
		double multiple=GameParameter.GameLevel_Multiple[SystemParameter.CurrentLevel];
		this.HP_MAX=unitData.getHP()*multiple;
		this.HP_CUR=HP_MAX;
		this.damege=unitData.getDamege()*multiple;
		
		
		//y轴不能超过地图的55%
		this.borderY=(int) ((SystemParameter.InterfaceH-size_h)*0.55);
		if(unitData.getDieDrop()!=null)
			this.dieDrop=new DieDrop(unitData.getDieDrop(),player);
		else this.dieDrop=null;
	}
	//初始化出生点并添加出生行为
	@Override
	public void build() {
		int build_x=random.nextInt(-300, 300+SystemParameter.InterfaceW)-size_w;
		int build_y=random.nextInt(-300, 0)-size_h;
		int target_x=random.nextInt(0, 200)+size_w;
		int target_y=random.nextInt(0, 200)+size_h;
		this.coordinate_x=build_x;
		this.coordinate_y=build_y;
		addBehavior(new BuildBehavior(build_x,build_y,target_x,target_y));
	}
	@Override
	public void attack()
	{
		if(this.state==GameParameter.UnitState_Alive)
		if(attackClock.isRing())
		{
			BaseUnit baseUnit=UnitFactory.getUnitFacory().createFireForUnit(this,false,270);
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
		int offset_x=movement*random.pickOneFrom(30, 50)*random.pickOneFrom(-1,1);
		int offset_y=movement*random.pickOneFrom(15, 25)*random.pickOneFrom(-1,1);
		int target_x=coordinate_x+offset_x;
		int target_y=coordinate_y+offset_y;
		if(target_x<size_w/2||target_x>borderX)
			target_x=coordinate_x-offset_x;
		if(target_y<size_h/2||target_y>borderY)
			target_y=coordinate_y-offset_y;
		addBehavior(new MoveBehavior
				(coordinate_x, coordinate_y, target_x, target_y));

	}
	

	@Override
	public void beHit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void rewardKiller(BaseUnit unit) {
		// TODO Auto-generated method stub
		unit.getPlayer().increaseScore((int)this.HP_MAX);
		if(dieDrop!=null)
			this.getPlayer().addAllToUnitList(
				dieDrop.drop(coordinate_x,coordinate_y,size_w,size_h));
	}
	

}
