package com.rock.UI.mouse;

import java.awt.event.MouseListener;

import com.rock.UI.GameFrame;
import com.rock.UI.component.BaseLabel;

//鼠标监听器
public abstract class BaseMouse implements MouseListener{
	protected  GameFrame gameFrame=GameFrame.getGameFrame();
	protected BaseLabel label;
	public BaseMouse(BaseLabel label)
	{
		this.label=label;
	}

}
