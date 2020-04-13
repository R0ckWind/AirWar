package com.rock.unit;

import javax.swing.ImageIcon;

import com.rock.UI.GameFrame;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.unit.skill.BaseSkill;

//子弹类
public class FireUnit extends BaseUnit{

	protected boolean permeable;//是否可穿透
	protected double radians;//弧度
	public FireUnit(UnitData unitData,BaseUnit unit,boolean critHit,int angle)
	{
		int factor=1;
		if(critHit)
			factor*=2;
		this.EXP=0;
		this.HP_MAX=unitData.getHP();
		this.MP_MAX=unitData.getMP();
		this.HP_CUR=HP_MAX;
		this.MP_CUR=MP_MAX;
		this.state=GameParameter.UnitState_Alive;
		this.name=unitData.getName();
		this.aSpeed=unitData.getaSpeed();
		this.defence=unitData.getDefence();
		this.HP_MAX=unitData.getHP();
		this.MP_MAX=unitData.getMP();
		this.currentSkin=unitData.getCommonSkin();
		this.commonSkin=unitData.getCommonSkin();
		this.transformSkin=unitData.getTransformSkin();
		this.beHitSkin=unitData.getBeHitSkin();
		this.size_w=unitData.getSize_w()*factor;
		this.size_h=unitData.getSize_h()*factor;
		this.hitable=unitData.isHitable();
		this.permeable=unitData.isPermeable();
		this.radians=Math.toRadians(angle);
		
		this.player=unit.getPlayer();	
		this.damege=unit.getDamege()*factor;//子弹的伤害就是单位的伤害
		this.movement=unit.getMovement()*2;//子弹的移速是单位移速的2倍
		this.coordinate_x=unit.getCoordinate_x();
		this.coordinate_y=unit.getCoordinate_y();

	}
	@Override
	public void action()
	{
		super.action();
		beHitClock.run();//被攻击时钟时间流逝
		if(beHitClock.isRing())
		{
			if(this.state<=GameParameter.UnitState_Alive)
				this.currentSkin=this.commonSkin;
		}
		this.mapRefresh();
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


	@Override
	public double specialAttack(BaseSkill skill, BaseUnit target) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//移动方向根据弧度而定
	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(state==GameParameter.UnitState_Alive)
		{
			this.coordinate_y-=movement*Math.sin(radians);
			this.coordinate_x+=movement*Math.cos(radians);
		}
		//如果越界就die
		if(this.coordinate_x<0||
				this.coordinate_x>SystemParameter.InterfaceW||
				this.coordinate_y<0||
				this.coordinate_y>SystemParameter.InterfaceH)
			die();
	}

	@Override
	public void beHit() {
			
	}
	
	@Override
	public void beHitBy(BaseUnit unit) {
		
		//如果击中的是战斗机
		if(unit instanceof BattleUnit)
		{
			//如果不可穿透，直接消失
			if(!permeable)
				die();
		}
		//如果子弹是可攻击的子弹
		else if(this.isHitable())
		{
			if(!unit.hitMap.containsKey(this.getUnitID()))
			{
				this.HP_CUR--;
				this.currentSkin=this.beHitSkin;
				unit.addHit(this);
				if(this.HP_CUR<=0)
				{		
					die();
				}
			}
			
		}
	}
	
	@Override
	public void dying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		state=GameParameter.UnitState_Dead;
	}

	@Override
	public void rewardKiller(BaseUnit unit) {
		// TODO Auto-generated method stub
		
	}

	public boolean isPermeable() {
		return permeable;
	}

	public void setPermeable(boolean permeable) {
		this.permeable = permeable;
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}



}
