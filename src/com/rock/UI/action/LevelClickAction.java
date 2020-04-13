package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.component.BaseButton;
import com.rock.parameter.SystemParameter;

//调整难度
public class LevelClickAction extends BaseClickAction{
	
	public LevelClickAction(BaseButton button) {
		super(button);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SystemParameter.CurrentLevel=(SystemParameter.CurrentLevel+1)%3;
		button.setbText("难度："+
				SystemParameter.GameLevel[SystemParameter.CurrentLevel]);
	}

}
