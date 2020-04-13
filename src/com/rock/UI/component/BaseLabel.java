package com.rock.UI.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.rock.UI.mouse.BaseMouse;
import com.rock.parameter.SystemParameter;

public class BaseLabel extends JLabel{
	/**
	 * 标签
	 */
	private String lName;//标签名称
	private String lText;//标签中央文字
	private int sizeW;//按钮宽度
	private int sizeH;//按钮高度
	private int labelX;//按钮x坐标
	private int labelY;//按钮y坐标z
	private ImageIcon labelImg;//图片
	private int fontSize;//字体大小
	private BaseMouse mouse;//鼠标事件
	
	public BaseLabel(){
		super();
		this.lName="";
		this.lText = "";
		this.sizeW = 10;
		this.sizeH = 10;
		this.labelX = 0;
		this.labelY = 0;
		this.labelImg = null;
		this.fontSize=18;
	}
	public void init()
	{
		this.setText(lText);
		this.setFont(new Font(SystemParameter.Font, Font.BOLD, fontSize));
		this.setOpaque(true);
		this.setBackground(Color.white);
		this.setBounds(labelX, labelY, sizeW, sizeH);
		this.setIcon(labelImg);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.addMouseListener(mouse);
	}
	
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getlText() {
		return lText;
	}
	public void setlText(String lText) {
		this.lText = lText;
		this.setText(lText);
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
	public int getLabelX() {
		return labelX;
	}
	public void setLabelX(int labelX) {
		this.labelX = labelX;
	}
	public int getLabelY() {
		return labelY;
	}
	public void setLabelY(int labelY) {
		this.labelY = labelY;
	}
	
	
	public ImageIcon getLabelImg() {
		return labelImg;
	}
	public void setLabelImg(ImageIcon labelImg) {
		this.labelImg = labelImg;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public BaseMouse getMouse() {
		return mouse;
	}
	public void setMouse(BaseMouse mouse) {
		this.mouse = mouse;
	}
	
	
}
