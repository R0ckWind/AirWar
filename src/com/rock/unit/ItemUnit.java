package com.rock.unit;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.battle.PlayerUnit;
import com.rock.unit.behavior.MoveBehavior;
import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.util.RandomGenerator;

//道具类
public abstract class ItemUnit extends BaseUnit{

	protected RandomGenerator random;
	public ItemUnit(UnitData unitData,BasePlayer player,int x,int y,int w,int h)
	{
		this.HP_MAX=unitData.getHP();
		this.MP_MAX=unitData.getMP();
		this.HP_CUR=HP_MAX;
		this.MP_CUR=MP_MAX;
		this.state=GameParameter.UnitState_Alive;
		this.name=unitData.getName();
		this.movement=2;
		this.currentSkin=unitData.getCommonSkin();
		this.size_w=unitData.getSize_w();
		this.size_h=unitData.getSize_h();
		this.random=new RandomGenerator();
		this.coordinate_x=x+random.nextInt(0, w);
		this.coordinate_y=y+random.nextInt(0, h);
		this.borderX=SystemParameter.InterfaceW-size_w;
		this.borderY=SystemParameter.InterfaceH-size_h;
		this.hitable=false;
		this.blood=false;
		this.player=player;
	}
	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double attack(BaseUnit target) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//移动总是朝着地图下方的某一个区域
	@Override
	public void move() {
		int target_x=random.nextInt(size_w, borderX);
		int target_y=random.nextInt((int)(0.65*SystemParameter.InterfaceH), borderY);
		addBehavior(new MoveBehavior
				(coordinate_x, coordinate_y, target_x, target_y));
		
	}

	@Override
	public void beHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beHitBy(BaseUnit unit) {
	
		if(unit instanceof PlayerUnit)
		{
			this.state=GameParameter.UnitState_Dead;
			rewardKiller(unit);
		}
	}

	@Override
	public void dying() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void rewardKiller(BaseUnit unit);


}
