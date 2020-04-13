package com.rock.unit.behavior;

import com.rock.unit.BaseUnit;

public abstract class BaseBehavior {
	public BaseBehavior next;
	public BaseBehavior()
	{
		this.next=null;
	}
	abstract public BaseBehavior action(BaseUnit unit);
}
