package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.component.BaseButton;
import com.rock.parameter.GameParameter;

//继续游戏
public class ContinueClickAction extends BaseClickAction{

	public ContinueClickAction(BaseButton button) {
		super(button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gameFrame.getGamePanel().exitCurrentActivity();
		gameFrame.getRunningParameter()
			.setGameState(GameParameter.GameState_Running);
		gameFrame.requestFocus();
	}

}
