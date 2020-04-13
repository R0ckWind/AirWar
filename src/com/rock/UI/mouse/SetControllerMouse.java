package com.rock.UI.mouse;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Map;

import com.rock.UI.GameActivity;
import com.rock.UI.GameDialog;
import com.rock.UI.component.BaseLabel;
import com.rock.parameter.SystemParameter;
import com.rock.parameter.SystemParameter.KeybordAction;

//设置按钮事件
public class SetControllerMouse extends BaseMouse{
	public SetControllerMouse(BaseLabel label)
	{
		super(label);
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		String parm=label.getlName();
		GameDialog dialog=new GameDialog("提示", "请输入按键: "+parm);
		KeyListener listener=new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				//新按键
				char press=e.getKeyChar();
				KeybordAction keybord=SystemParameter.keyCtrMap.get(parm);
				//旧按键
				char oldSet=keybord.getTrigger();
				keybord.setTrigger(press);
				if(press!=oldSet)
				{
					GameActivity currentActivity=gameFrame.getGamePanel().getCurrentActivity();
					Map<String,BaseLabel> labelMap=currentActivity.getLabelMap();
					//判断这个键有没有被用过
					if(SystemParameter.keyUseMap.containsKey(press))
					{
						KeybordAction replace=SystemParameter.keyUseMap.get(press);
						replace.setTrigger(' ');
						labelMap.get(replace.getEffct()).setlText("");
					}
					SystemParameter.keyUseMap.remove(oldSet);
					SystemParameter.keyUseMap.put(press, keybord);	
					labelMap.get(keybord.getEffct()).setlText((press+"").toUpperCase());
					currentActivity.repaint();
				}
				//关闭窗口
				dialog.dispose();
			}
			public void keyPressed(KeyEvent e) {}
		};
		dialog.setKeyListener(listener);
		dialog.showDialog();
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
