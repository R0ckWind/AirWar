package com.rock.run;

import com.rock.UI.GameFrame;
import com.rock.parameter.GameParameter;
import com.rock.stage.GameStage;

/**
 * 加载文件中的关卡数据并存入stage中。
 * 加载完成之后将进入运行状态
 *
 */
public class LoadResourceThread extends Thread{
	private GameFrame gameFrame;
	public LoadResourceThread(GameFrame gameFrame)
	{
		this.gameFrame=gameFrame;
		//初始化时将游戏状态设置为load状态
		gameFrame.getRunningParameter()
		.setGameState(GameParameter.GameState_Load);
		//重画当前界面(将进入loading画面)
		gameFrame.getGamePanel().getCurrentActivity().repaint();
	}
	public void run()
	{	
		//延时1秒模拟耗时读取
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//加载下一关的资源
		GameStage stage=gameFrame.getRunningParameter().loadNextMission();
		
		if(stage!=null)
		{
			//设置当前关卡的背景图片
			gameFrame.getGamePanel().
			getCurrentActivity().setBackground(stage.getBackground());	
			//开启创建单位线程
			new CreateUnitThread(gameFrame.getRunningParameter()).start();
			//修改当前游戏状态
			gameFrame.getRunningParameter()
				.setGameState(GameParameter.GameState_Running);
			//重画界面
			gameFrame.getGamePanel().getCurrentActivity().repaint();
		}
		else 
		{
			//玩家胜利
			gameFrame.getRunningParameter().getPlayer().gameVictory();
			//没有下一关了
			gameFrame.getRunningParameter()
			.setGameState(GameParameter.GameState_Endding);
			//回到主页面
			gameFrame.getGamePanel().backToMenu();
		}
	}
}
