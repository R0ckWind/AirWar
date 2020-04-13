package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.GameActivity;
import com.rock.UI.component.BaseButton;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.util.FileHandleUtil;

public class SetClickAction extends BaseClickAction{
	
	//弹出设置界面
	public SetClickAction(BaseButton button) {
		super(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameActivity setActivity=FileHandleUtil.handleFile("setActivity.txt",GameActivity.handle);
		setActivity.init();
		//更新按键的显示
		setActivity.getLabelMap().get("UpKey").setlText((SystemParameter.UpKey+"").toUpperCase());
		setActivity.getLabelMap().get("DownKey").setlText((SystemParameter.DownKey+"").toUpperCase());
		setActivity.getLabelMap().get("LeftKey").setlText((SystemParameter.LeftKey+"").toUpperCase());
		setActivity.getLabelMap().get("RightKey").setlText((SystemParameter.RightKey+"").toUpperCase());
		setActivity.getLabelMap().get("AttackKey").setlText((SystemParameter.AttackKey+"").toUpperCase());
		setActivity.getLabelMap().get("SAtackKey").setlText((SystemParameter.SAtackKey+"").toUpperCase());
		gameFrame.getRunningParameter().setGameState(GameParameter.GameState_Set);
		gameFrame.getGamePanel().goTop(setActivity);
		gameFrame.getGamePanel().repaint();
	}
	
}
