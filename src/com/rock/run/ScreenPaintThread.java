package com.rock.run;

import com.rock.UI.GameFrame;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.parameter.SystemParameter;

public class ScreenPaintThread extends Thread {
	/**
	 * 循环绘画（常驻内存）
	 */
	private GameFrame gameFrame;

	public ScreenPaintThread(GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
	}

	@Override
	public void run() {
		RunningParameter runningParameter = gameFrame.getRunningParameter();
		while (true) {
			if (runningParameter.getGameState() == GameParameter.GameState_Endding)
				break;
			if (runningParameter.getGameState() == GameParameter.GameState_Running) {
				try {
					//根据fps值来决定休眠时间
					Thread.sleep(SystemParameter.FPS_Sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 重画当前界面
				gameFrame.getGamePanel().getCurrentActivity().repaint();
			}

		}
	}
}
