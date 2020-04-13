package com.rock.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;
import javax.swing.JPanel;

import com.rock.UI.component.BaseLabel;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.util.FileHandleUtil;
import com.rock.util.handle.ActivityHandle;

public class GamePanel extends JPanel{
	/**
	 * 主界面画板
	 * 含有活动栈
	 * 可以通过它获取到当前正在运行的layout
	 */
	//活动栈
	private Stack<GameActivity> activityStack=new Stack<GameActivity>();
	private GameActivity pauseActivity;//暂停界面
	private GameActivity countActivity;//结算界面
	private GameActivity overActivity;//失败界面
	//创建新的活动栈并将它放在栈顶
	public void goTop(GameActivity activity)
	{
		if(!activityStack.isEmpty())
			activityStack.peek().setVisible(false);
		activity.setVisible(true);
		activityStack.push(activity);
		this.add(activity);
	}
	//获得栈顶活动
	public GameActivity getCurrentActivity()
	{
		if(!activityStack.isEmpty())
			return activityStack.peek();
		return null;
	}
	
	//将当前activity弹出
	public void exitCurrentActivity()
	{
		if(!activityStack.isEmpty())
			activityStack.pop().setVisible(false);
		if(!activityStack.isEmpty())
			activityStack.peek().setVisible(true);
		else 
		{
			System.out.println("EXIT");
		}
	}
	
	//返回到第一个页面
	public void backToMenu()
	{
		int size=activityStack.size();
		while(size-->1)
		{
			activityStack.pop().setVisible(false);
		}
		activityStack.peek().setVisible(true);
		GameFrame.getGameFrame().getRunningParameter().restart();
	}
	//初始化函数
	public void init()
	{
		this.setLayout(null);
		GameActivity mainActivity=FileHandleUtil.handleFile("mainActivity.txt",GameActivity.handle);
		mainActivity.init();
		mainActivity.getButtonMap().get("难度设置").setbText("难度："+
				SystemParameter.GameLevel[SystemParameter.CurrentLevel]);
		pauseActivity=FileHandleUtil.handleFile("pauseActivity.txt",GameActivity.handle);
		pauseActivity.init();
		countActivity=FileHandleUtil.handleFile("countActivity.txt",GameActivity.handle);
		countActivity.init();
		overActivity=FileHandleUtil.handleFile("overActivity.txt",GameActivity.handle);
		overActivity.init();
		//添加按esc就返回到游戏的事件
		pauseActivity.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE)
				{
					GameFrame.getGameFrame().getGamePanel().exitCurrentActivity();
					GameFrame.getGameFrame().getRunningParameter()
						.setGameState(GameParameter.GameState_Running);
					GameFrame.getGameFrame().requestFocus();
				}			
			}
		});
		this.goTop(mainActivity);
	}
	//进入暂停界面
	public void pauseGame()
	{
		this.goTop(pauseActivity);
		pauseActivity.requestFocus();
	}
	//进入结算界面
	public void goCount(int score)
	{
		BaseLabel label=countActivity.getLabelMap().get("分数");
		label.setlText("分数："+score);
		this.goTop(countActivity);
		countActivity.requestFocus();
	}
	//进入失败界面
	public void goOver(int score)
	{
		BaseLabel label=overActivity.getLabelMap().get("分数");
		label.setlText("分数："+score);
		this.goTop(overActivity);
		overActivity.requestFocus();
	}
}
