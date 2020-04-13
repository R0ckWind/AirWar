package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.component.BaseButton;
import com.rock.parameter.GameParameter;

//退出游戏
public class ExitClickAction extends BaseClickAction{

	public ExitClickAction(BaseButton button) {
		super(button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gameFrame.getRunningParameter().setGameState(GameParameter.GameState_Endding);
		gameFrame.getGamePanel().removeAll();
		gameFrame.removeAll();
		gameFrame.dispose();
		System.out.println("游戏关闭");
		System.exit(0);
		
	}

}
