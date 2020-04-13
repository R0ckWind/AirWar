package com.rock.UI.mouse;

import java.awt.event.MouseEvent;

import com.rock.UI.GameActivity;
import com.rock.UI.component.BaseLabel;
import com.rock.camp.BasePlayer;
import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;
import com.rock.util.FileHandleUtil;

//选择战机
public class SelectPlaneMouse extends BaseMouse{

	public SelectPlaneMouse(BaseLabel label) {
		super(label);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//弹出select布局
		GameActivity fightActivity=FileHandleUtil.handleFile("fightActivity.txt", GameActivity.handle);
		fightActivity.init();
		gameFrame.getGamePanel().goTop(fightActivity);
		//加载关卡资源
		gameFrame.loadNextMissionResource();
		gameFrame.requestFocus();
		//新建并添加玩家单位
		String plane=label.getlName();
		BasePlayer player=gameFrame.getRunningParameter().getPlayer();
		BaseUnit unit=UnitFactory.getUnitFacory().createBattleUnit(plane,player);
		gameFrame.getRunningParameter().setPlayerUnit(unit);
		player.addUnitToUnitList(unit);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
