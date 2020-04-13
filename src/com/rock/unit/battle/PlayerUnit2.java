package com.rock.unit.battle;


import com.rock.camp.BasePlayer;

import com.rock.parameter.SystemParameter;

import com.rock.unit.behavior.BuildBehavior;

import com.rock.unit.factory.UnitFactory.UnitData;
import com.rock.unit.skill.SummonFriend;


public class PlayerUnit2 extends PlayerUnit{
	
	public PlayerUnit2(UnitData unitData, BasePlayer player) {
		super(unitData, player);
	}
	
	@Override
	public void build() {
		int target_x=SystemParameter.InterfaceW/2;
		int target_y=(int)(SystemParameter.InterfaceH-1.5*size_h);
		int build_x=target_x;
		int build_y=target_y+150;
		coordinate_x=build_x;
		coordinate_y=build_y;
		addBehavior(new BuildBehavior(build_x,build_y,target_x,target_y));
		defaultSkill=SummonFriend.skill;
	}

}
