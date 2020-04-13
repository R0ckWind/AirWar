package com.rock.unit.behavior;

import com.rock.unit.BaseUnit;

//单位普遍会执行的行为
public class CommonBehavior extends BaseBehavior{

	private CommonBehavior(){}
	public static CommonBehavior behavior=new CommonBehavior();
	@Override
	public BaseBehavior action(BaseUnit unit) {
		// TODO Auto-generated method stub
		unit.move();
		unit.attack();
		return null;
	}

}
