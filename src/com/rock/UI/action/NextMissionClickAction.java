package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.component.BaseButton;
//进入下一关
public class NextMissionClickAction extends BaseClickAction{

	public NextMissionClickAction(BaseButton button) {
		super(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gameFrame.getGamePanel().exitCurrentActivity();
		gameFrame.loadNextMissionResource();
		gameFrame.requestFocus();
		gameFrame.getRunningParameter().getPlayerUnit().build();
	}

}
