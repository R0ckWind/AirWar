package com.rock.UI.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.rock.UI.action.BaseClickAction;

public class BaseButton extends JButton{
	/**
	 * 按钮
	 */
	private String bName;//按钮名称
	private String bText;//按钮中央文字
	private int sizeW;//按钮宽度
	private int sizeH;//按钮高度
	private int buttonX;//按钮x坐标
	private int buttonY;//按钮y坐标
	private ImageIcon buttonImg;//按钮图片
	private BaseClickAction action;//点击事件
	public BaseButton(){
		super();
		this.bName="";
		this.bText = "";
		this.sizeW = 10;
		this.sizeH = 10;
		this.buttonX = 0;
		this.buttonY = 0;
		this.buttonImg = null;
		this.action = null;
	}
	public void init()
	{
		this.setText(bText);
		this.setBounds(buttonX, buttonY, sizeW, sizeH);
		this.setIcon(buttonImg);
		this.addActionListener(action);
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbText() {
		return bText;
	}
	public void setbText(String bText) {
		this.bText = bText;
		this.setText(bText);
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
	public int getButtonX() {
		return buttonX;
	}
	public void setButtonX(int buttonX) {
		this.buttonX = buttonX;
	}
	public int getButtonY() {
		return buttonY;
	}
	public void setButtonY(int buttonY) {
		this.buttonY = buttonY;
	}

	
	public ImageIcon getButtonImg() {
		return buttonImg;
	}
	public void setButtonImg(ImageIcon buttonImg) {
		this.buttonImg = buttonImg;
	}
	public void setAction(BaseClickAction action) {
		this.action = action;
	}
	
}
