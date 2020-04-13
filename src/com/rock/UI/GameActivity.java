package com.rock.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.rock.UI.component.BaseButton;
import com.rock.UI.component.BaseLabel;
import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.unit.BaseUnit;
import com.rock.util.DrawUnitUtil;
import com.rock.util.handle.ActivityHandle;
import com.rock.util.handle.BaseHandle;


/**
 * 活动
 * 含有：buttonMap、labelMap
 * 用于遍历显示
 * 并给监听器提供直接可控制接口。
 * 
 */
public class GameActivity extends JPanel{
	
	private ImageIcon background;//背景
	private int sizeW;
	private int sizeH;
	private Map<String,BaseButton> buttonMap;
	private Map<String,BaseLabel> labelMap;
	
	public static BaseHandle handle=new ActivityHandle();
	
	public GameActivity()
	{
		this.background=null;
		this.buttonMap=new HashMap<>();
		this.labelMap=new HashMap<>();
	}
	public void init()
	{
		this.setLayout(null);
		this.setSize(sizeW, sizeH);
		for (BaseButton button : buttonMap.values()) 
		{
			button.init();
			this.add(button);
		}
		for (BaseLabel label : labelMap.values()) 
		{
			label.init();
			this.add(label);
		}
	}

	public void paint(Graphics g) 
	{  
		super.paint(g);
		RunningParameter runningPram=GameFrame.getGameFrame().getRunningParameter();
		//如果加载中，就绘制加载画面
		if(runningPram.getGameState()==GameParameter.GameState_Load)
			g.drawImage(GameParameter.LoaddingIMG.getImage(), 0, 0, this.getWidth(), this.getHeight(),null);  
		else 
		{
			if(background!=null)
			//绘制背景
			g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(),null);  
			for (BaseButton button : buttonMap.values()) 
			{
				button.repaint();//绘制按钮
			}
			for (BaseLabel label : labelMap.values()) 
			{
				label.repaint();//绘制标签
			}
			//绘制单位
			DrawUnitUtil.showUnitList(g,runningPram.getPlayerList());
			//绘制HUD
			GameFrame.getGameFrame().getGameHud().paint(g);
		}
		
	}  
	//添加按钮
	public void addComponent(BaseButton button)
	{
		buttonMap.put(button.getbName(),button);
	}
	//添加标签
	public void addComponent(BaseLabel label)
	{
		labelMap.put(label.getlName(),label);
	}
	
	public void setBackground(ImageIcon background) {
		this.background = background;
	}
	public int getSizeW() {
		return sizeW;
	}
	public void setSizeW(int sizeW) {
		this.sizeW = sizeW;
	}
	public int getSizeH() {
		return sizeH;
	}
	public void setSizeH(int sizeH) {
		this.sizeH = sizeH;
	}
	public Map<String, BaseButton> getButtonMap() {
		return buttonMap;
	}
	public Map<String, BaseLabel> getLabelMap() {
		return labelMap;
	}
	
	
	
}
