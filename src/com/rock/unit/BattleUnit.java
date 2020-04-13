package com.rock.unit;

import java.util.Map;


import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.battle.BossUnit;
import com.rock.unit.buff.BuffList;
import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.util.ClockCounter;
import com.rock.util.RandomGenerator;

//战斗单位类
public abstract class BattleUnit extends BaseUnit{

	protected RandomGenerator random;//随机事件生成器
	protected int dyingFlag=GameParameter.Dying_Animation_Frames_Last*GameParameter.Dying_Animation_Icon_Size;
	/*
	 * 记录自身(不包括生成的子弹)击中的目标
	 * 主要是为了避免反复碰撞
	 * map中的单位会在一段时间之后移除
	 */
	protected Map<Long,Integer> hitMap;
	
	public BattleUnit(UnitData unitData,BasePlayer player)
	{
		this.EXP=0;
		
		this.HP_MAX=unitData.getHP();
		this.MP_MAX=100;
		this.HP_CUR=HP_MAX;
		this.MP_CUR=unitData.getMP();
		
		this.state=GameParameter.UnitState_Build;
		this.buffList=new BuffList();
		this.deBuffList=new BuffList();
		this.name=unitData.getName();
		this.damege=unitData.getDamege();
		this.aSpeed=unitData.getaSpeed();
		this.critChance=unitData.getCritChance();
		this.defence=unitData.getDefence();
		this.movement=unitData.getMovement();
		this.currentSkin=unitData.getCommonSkin();
		this.commonSkin=unitData.getCommonSkin();
		this.transformSkin=unitData.getTransformSkin();
		this.beHitSkin=unitData.getBeHitSkin();
		this.size_w=unitData.getSize_w();
		this.size_h=unitData.getSize_h();
		this.hitable=unitData.isHitable();
		this.fireUnit=unitData.getFire();
		this.blood=unitData.isBlood();
		this.player=player;
		this.borderX=SystemParameter.InterfaceW-size_w/2;
		this.borderY=SystemParameter.InterfaceH-size_h/2;
		this.random=new RandomGenerator();
		//计算攻击间隔
		int cap=(int)(1000/aSpeed/GameParameter.UnitAction_TimeUnit);
		this.attackClock=new ClockCounter(cap);//初始化攻击时钟
		build();
	}
	
	public BattleUnit(UnitData unitData,int x,int y,BasePlayer player)
	{
		this(unitData,player);
		this.coordinate_x=x;
		this.coordinate_y=y;
	}
	//修正越界的情况
	protected void CorrentOverBorder()
	{
		if(coordinate_x<size_w/2)
			coordinate_x=size_w/2;
		else if(coordinate_x>borderX)
			coordinate_x=borderX;
		if(coordinate_y<size_h/2)
			coordinate_y=size_h/2;
		else if(coordinate_y>borderY)
			coordinate_y=borderY;
	}
	
	
	@Override
	public void action()
	{
		super.action();
		attackClock.run();//攻击时钟时间流逝
		beHitClock.run();//被攻击时钟时间流逝
		if(beHitClock.isRing())
		{
			if(this.state<=GameParameter.UnitState_Alive)
				this.currentSkin=this.commonSkin;
		}
		this.mapRefresh();
	}
	
	@Override
	public void beHitBy(BaseUnit unit) {
		
		//如果自己是不可攻击的，直接跳过
		if(this.hitable==false)
			return;
		//如果攻击者在一段时间内曾经撞击过自己，直接跳过
		if(unit.hitMap.containsKey(this.unitID))
			return;
		//如果不是是死亡或者在死亡状态，直接跳过
		if(this.state>GameParameter.UnitState_Alive)
			return;
		
		if(unit instanceof ItemUnit)
			return;
		//创建状态也会有击中反馈
		this.currentSkin=this.beHitSkin;//修改单位贴图
		
		beHitClock.cooldown();//被攻击时钟冷却
		
		//只有alive状态才会掉血
		if(this.state!=GameParameter.UnitState_Alive)
			return;
		unit.addHit(this);//否则对方的撞击队列中加入自己
		
		//被子弹攻击
		if(unit instanceof FireUnit)
		{
			this.HP_CUR-=(unit.getDamege()-this.defence);
		}
		//被战机撞击
		else if(unit instanceof BattleUnit)
		{
			this.HP_CUR-=(unit.getDamege()-this.defence)*5;
		}
		if(this.HP_CUR<=0)
		{
			this.HP_CUR=0;
			this.state=GameParameter.UnitState_Dying;
			rewardKiller(unit);
		}


	}
	
	@Override
	public void dying() {
		//this.skin=new ImageIcon();
		if(state==GameParameter.UnitState_Dying)
			dyingFlag--;
		if(dyingFlag<0)
			die();	
		else //死亡时将贴图替换为爆炸贴图
			this.currentSkin=GameParameter.Dying_Skin_Icon
				[dyingFlag/GameParameter.Dying_Animation_Frames_Last];

	}
	
	@Override
	public void die() {
		this.state=GameParameter.UnitState_Dead;
	}

	
}
