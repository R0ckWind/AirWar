package com.rock.util;

import com.rock.UI.GameFrame;
import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.unit.BaseUnit;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public class DrawUnitUtil {
	/**
	 * 绘画工具
	 */
	private static RunningParameter runingParameter
				=GameFrame.getGameFrame().getRunningParameter();
	//画出单个单位
	public static void show(BaseUnit unit,Graphics g)
	{
		int x=unit.getCoordinate_x()-unit.getSize_w()/2;
		int y=unit.getCoordinate_y()-unit.getSize_h()/2;
		g.drawImage(unit.getCurrentSkin().getImage(), 
				x,y,//显示界面左上点
				unit.getSize_w(),unit.getSize_h(),
				null);
		if(unit.isBlood())
			BloodExhibitUtil.exhibitBlood(unit, g);
	}
	//画出playerList中的所有玩家的单位
	public static void showUnitList(Graphics g,List<BasePlayer> playerList)
	{
		if(runingParameter.getGameState()==GameParameter.GameState_Running)
		for(int i=0;i<playerList.size();i++)
		{
			List<BaseUnit> unitList=playerList.get(i).getPlayerUnitList();
			for(int j=0;j<unitList.size();j++)
			{
				DrawUnitUtil.show(unitList.get(j), g);
			}
		}
	}
}
