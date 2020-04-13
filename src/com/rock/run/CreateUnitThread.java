package com.rock.run;

import java.util.List;

import com.rock.UI.GameFrame;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.stage.GameStage;
import com.rock.unit.BaseUnit;
import com.rock.unit.ItemUnit;
import com.rock.unit.behavior.RunToPlayer;


/**
 * 该线程用于对定时生成敌人和道具。
 * 进入关卡时开启
 */
public class CreateUnitThread extends Thread{

	private RunningParameter runningParameter;
	
	public CreateUnitThread(RunningParameter runningParameter)
	{
		super();
		this.runningParameter=runningParameter;
	}
	@Override
	public void run() {
		GameStage stage=runningParameter.getCurrentStage();//获取当前关卡信息
		while(!stage.isFinish())
		{
			//游戏关闭，直接结束
			if(runningParameter.getGameState()==GameParameter.GameState_Endding)
				return;
			//角色死亡，跳出
			if(runningParameter.getPlayerUnit().getState()>=GameParameter.UnitState_Dying)
				break;
			//如果游戏在运行状态，就不断让stage流动。
			if(runningParameter.getGameState()==GameParameter.GameState_Running)
			{
				try {
					Thread.sleep(GameParameter.UnitBuild_TimeUnit);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//如果stage里的怪已经被消灭，返回false
				stage.waveFlow();
			}
		}
		//关卡结束，进入结算界面
		gameCount();
		
	}
	private void gameCount()
	{
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(runningParameter.getPlayerUnit().getState()==GameParameter.UnitState_Alive)
			GameFrame.getGameFrame().count();//可进入下一关
		else if(runningParameter.getPlayerUnit().getState()>=GameParameter.UnitState_Dying)
			GameFrame.getGameFrame().gameOver();//玩家死亡，游戏结束
	}
}
