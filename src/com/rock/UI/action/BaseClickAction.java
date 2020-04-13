package com.rock.UI.action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rock.UI.GameFrame;
import com.rock.UI.component.BaseButton;

//点击事件抽象类
abstract public class BaseClickAction implements ActionListener{
	protected  GameFrame gameFrame=GameFrame.getGameFrame();
	protected BaseButton button;
	public BaseClickAction(BaseButton button)
	{
		this.button=button;
	}
	public abstract void actionPerformed(ActionEvent e);
}
