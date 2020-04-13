package com.rock.run;

import com.rock.UI.GameFrame;

/**
 *游戏主线程
 *执行main方法
 *
 */
public class GameMainThread {

	public static void main(String[] args) {
		//运行游戏
		GameFrame game=GameFrame.getGameFrame();
		game.start();
	}

}
