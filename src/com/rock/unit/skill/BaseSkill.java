package com.rock.unit.skill;

import com.rock.unit.BaseUnit;

//抽象技能
public abstract class BaseSkill {
	protected int manaCost;
	public BaseSkill()
	{
		setCost();
	}
	public abstract void setCost();
	public abstract double excute(BaseUnit executor,BaseUnit target);
}
