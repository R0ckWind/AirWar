package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.component.BaseButton;

public class BackToMenuClickAction extends BaseClickAction{

	//返回菜单
	public BackToMenuClickAction(BaseButton button) {
		super(button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.getGamePanel().backToMenu();
		
	}

}
