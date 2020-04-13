package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.GameActivity;
import com.rock.UI.component.BaseButton;
import com.rock.camp.BasePlayer;
import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;
import com.rock.util.FileHandleUtil;


public class StartClickAction extends BaseClickAction{
	
	//开始游戏
	public StartClickAction(BaseButton button) {
		super(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		//进入选择战机界面
		GameActivity selectActivity=FileHandleUtil.handleFile("selectActivity.txt", GameActivity.handle);
		selectActivity.init();
		gameFrame.getGamePanel().goTop(selectActivity);
	}
}
