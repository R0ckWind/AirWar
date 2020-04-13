package com.rock.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.parameter.SystemParameter;
import com.rock.run.CreateUnitThread;
import com.rock.run.ScreenPaintThread;
import com.rock.run.UnitActionThread;
import com.rock.unit.buff.BuffState;
import com.rock.unit.factory.BuffFactory;
import com.rock.run.LoadResourceThread;


/**
 * 游戏主程序。单例模式。
 * 包含
 * runningParameter：用于记录运行时状态
 * gamePanel：用于控制活动栈，以及给活动提供主画板
 * gameHud：在游戏中给玩家显示必要的战斗信息
 */
public class GameFrame extends JFrame{

	//单例模式
	private static GameFrame gameFrame;
	//运行时数据
	private RunningParameter runningParameter;
	//画板
	private GamePanel gamePanel;
	//hud
	private GameHud gameHud;
	
	private GameFrame(){}
	public void init()
	{
		gamePanel=new GamePanel();
		runningParameter=new RunningParameter();
		//设置界面大小
		this.setSize(SystemParameter.InterfaceW,SystemParameter.InterfaceH);
		this.setLocationRelativeTo(null);//居中显示
		
		gamePanel.init();
		this.add(gamePanel);
		this.setResizable(false);//禁止调整大小
		this.setUndecorated(true);//取消窗体的默认的样式
		this.setVisible(true);

		gameHud=new GameHud(runningParameter);
	}
	//为窗体添加监听
	public void setListener(){
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			//按键松开
			@Override
			public void keyReleased(KeyEvent e) {
				if(runningParameter.getGameState()
						==GameParameter.GameState_Running)
				{
					char release=e.getKeyChar();  
					if(release==SystemParameter.UpKey)
						runningParameter.setUp(false);
					if(release==SystemParameter.DownKey)
						runningParameter.setDown(false);
					if(release==SystemParameter.LeftKey)
						runningParameter.setLeft(false);
					if(release==SystemParameter.RightKey)
						runningParameter.setRight(false);
					if(release==SystemParameter.AttackKey)
						runningParameter.setAttack(false);
					if(release==SystemParameter.SAtackKey)
						runningParameter.getPlayerUnit().specialAttack();
				}
			}
			//按键按下
			@Override
			public void keyPressed(KeyEvent e) {
				if(runningParameter.getGameState()
						==GameParameter.GameState_Running)
				{
					char presse=e.getKeyChar();  
					if(presse==SystemParameter.UpKey)
						runningParameter.setUp(true);
					if(presse==SystemParameter.DownKey)
						runningParameter.setDown(true);
					if(presse==SystemParameter.LeftKey)
						runningParameter.setLeft(true);
					if(presse==SystemParameter.RightKey)
						runningParameter.setRight(true);
					if(presse==SystemParameter.AttackKey)
						runningParameter.setAttack(true);
					if(presse==SystemParameter.SAtackKey)
						;//可能什么都不做
					if(presse==KeyEvent.VK_ESCAPE)
						pause();
				}
			}
		});
	}
	//开启线程
	public void start()
	{	
		init();
		//开启界面重画线程
		new ScreenPaintThread(this).start();
		//开启单位行动线程
		new UnitActionThread(this).start();
		//设置监听器
		this.setListener();
		//聚焦
		this.requestFocus();
	}
	//暂停游戏
	public void pause()
	{
		this.runningParameter.setFalse();//把按键设置为false
		this.runningParameter.setGameState(GameParameter.GameState_Pause);
		this.gamePanel.pauseGame();//进入暂停界面
	}
	
	//进入结算界面
	public void count()
	{
		this.runningParameter.setFalse();//把按键设置为false
		this.runningParameter.clearUnitExpectPlayerUnit();//清除单位
		
		//奖励玩家战机通关buff
		String buffStr=this.runningParameter.getCurrentStage().getReward();
		BuffState buff=BuffFactory.getBuffFactory().createBuffState(buffStr);
		this.runningParameter.getPlayerUnit().addBuff(buff);
		
		this.runningParameter.setGameState(GameParameter.GameState_Pause);
		//进入结算界面
		this.gamePanel.goCount(runningParameter.getPlayer().getScore());
	}
	
	//进入游戏结束界面
	public void gameOver()
	{
		this.runningParameter.setFalse();//把按键设置为false
		this.runningParameter.setGameState(GameParameter.GameState_Finish);
		//进入游戏结束界面
		this.gamePanel.goOver(runningParameter.getPlayer().getScore());
	}
	
	//开启一个线程来加载资源，并且进入load界面
	public void loadNextMissionResource()
	{
		new LoadResourceThread(this).start();
	}
	
	
	public static GameFrame getGameFrame()
	{
		if(gameFrame == null){
            synchronized (GameFrame.class){
                if(gameFrame == null){
                	gameFrame = new GameFrame();
                }
            }
        }
		return gameFrame;
	}
	
	
	
	
	
	public RunningParameter getRunningParameter() {
		return runningParameter;
	}
	public void setRunningParameter(RunningParameter runningParameter) {
		this.runningParameter = runningParameter;
	}
	
	
	public GameHud getGameHud() {
		return gameHud;
	}
	public void setGameHud(GameHud gameHud) {
		this.gameHud = gameHud;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	

}
