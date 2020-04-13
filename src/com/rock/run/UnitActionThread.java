package com.rock.run;

import java.util.Iterator;
import java.util.List;

import com.rock.UI.GameFrame;
import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.unit.BaseUnit;
import com.rock.util.CollideJudgeUtil;

/**
 * 单位循环行动，包括attack、move、等（常驻内存）
 *
 */

public class UnitActionThread extends Thread{
	private GameFrame gameFrame;
	
	public UnitActionThread(GameFrame gameFrame)
	{
		super();
		this.gameFrame=gameFrame;
	}
	public void run()
	{
		RunningParameter runningParameter=gameFrame.getRunningParameter();
		
		while(true)
		{
			if(runningParameter.getGameState()==GameParameter.GameState_Endding)
				break;
			//如果游戏不再运行状态，跳过执行
			if(runningParameter.getGameState()
					!=GameParameter.GameState_Running)
				continue;
			
			try {
				Thread.sleep(GameParameter.UnitAction_TimeUnit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//获取所有玩家
			List<BasePlayer> playerList=runningParameter.getPlayerList();
			int size=playerList.size();
			
			//对每个玩家的每一个单位进行遍历操作
			for(int i=0;i<size;i++)
			{
				List<BaseUnit> unitList=playerList.get(i).getPlayerUnitList();
				int usize=unitList.size();
				for(int j=0;j<usize;j++)
				{
					unitList.get(j).action();
				}
			}
			//对不同阵营的单位进行全连接，并且计算碰撞
			for(int i=0;i<size;i++)
			{
				BasePlayer player1=playerList.get(i);
				for(int j=i+1;j<size;j++)
				{
					BasePlayer player2=playerList.get(j);
					//如果是队友就跳过
					if(!player1.isEnemyWith(player2))
						continue;
					List<BaseUnit> unitList1=player1.getPlayerUnitList();
					List<BaseUnit> unitList2=player2.getPlayerUnitList();
					int size1=unitList1.size();
					int size2=unitList2.size();
					for(int i0=0;i0<size1;i0++)
					{
						BaseUnit unit1=unitList1.get(i0);
						for(int j0=0;j0<size2;j0++)
						{
							BaseUnit unit2=unitList2.get(j0);
							if(CollideJudgeUtil.isCollide(unit1, unit2))
							{
								unit1.beHitBy(unit2);
								unit2.beHitBy(unit1);
							}
						}
					}
				}
			}
			//清除死亡单位
			for(int i=0;i<size;i++)
			{
				List<BaseUnit> unitList=playerList.get(i).getPlayerUnitList();
				Iterator<BaseUnit> unitIterator=unitList.iterator();
				while(unitIterator.hasNext())
				{
					BaseUnit unit=unitIterator.next();	
					if(unit.getState()==GameParameter.UnitState_Dead)
						unitIterator.remove();
					else if(unit.getState()==GameParameter.UnitState_Dying)
						unit.dying();
				}
			}
		}
	}
}
