package com.rock.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.BaseUnit;

/**
 *由于给玩家提供必要的战斗信息
 *其中包括了
 *	玩家战机的生命值
 *	玩家战机的魔法值(超级炸弹数量)
 *	玩家的分数
 *	当前关卡信息
 */
public class GameHud {
	
	private String imgDir=GameParameter.Resources_Dir+"/img";
	private RunningParameter runningParameter;
	private int gameWidth;
	private int gameHeight;
	private PlayerBloodExhibit bloodExhibit;//显血工具
	private ImageIcon superBoom=new ImageIcon(imgDir+"/item/boom.png");
	public GameHud(RunningParameter runningParameter)
	{
		this.runningParameter=runningParameter;
		this.gameWidth=SystemParameter.InterfaceW;
		this.gameHeight=SystemParameter.InterfaceH;
		this.bloodExhibit=new PlayerBloodExhibit();
	}
	public void paint(Graphics g)
	{
		//游戏运行时，绘制血量、蓝量、分数和关卡信息	
		if(runningParameter.getGameState()==GameParameter.GameState_Running)
		{
			drawHP(g,30,gameHeight-80);
			drawMP(g,50,gameHeight-120,30);
			drawScore(g,gameWidth-100, 50,22);
			drawStage(g,30,50,22);
		}
	}
	//绘制血量
	private void drawHP(Graphics g,int x,int y)
	{
		bloodExhibit.exhibitBlood(g,x,y,120,40,-10);
	}
	//绘制蓝量
	private void drawMP(Graphics g,int x,int y,int size)
	{
		int boomNum=(int)runningParameter.getPlayerUnit().getMP_CUR();
		if(boomNum<=3)
		for(int i=0;i<boomNum;i++)
		{
			g.drawImage(superBoom.getImage(), 
					x+size*i,y,
					size,size,
					null);
		}
		else 
		{
			Font aFont=new Font(SystemParameter.Font,Font.BOLD,(int)(0.8*size));
			g.setFont(aFont);
			g.setColor(Color.WHITE);
			g.drawImage(superBoom.getImage(), 
					x,y,
					size,size,
					null);
			g.drawString("X"+boomNum, x+size, y+(int)(0.8*size));
		}
			
		
	}
	//绘制分数
	private void drawScore(Graphics g,int x,int y,int size)
	{
		Font aFont=new Font(SystemParameter.Font,Font.BOLD,size);
		g.setFont(aFont);
		g.setColor(Color.WHITE);
		g.drawString(runningParameter.getCurrentStage().getName(), x, y);
	}
	//绘制关卡信息
	private void drawStage(Graphics g,int x,int y,int size)
	{
		Font aFont=new Font(SystemParameter.Font,Font.BOLD,size);
		g.setFont(aFont);
		g.setColor(Color.WHITE);
		g.drawString("分数:"+runningParameter.getPlayer().getScore(), x, y);
	}
	//玩家显血类
	private class PlayerBloodExhibit
	{
		private ImageIcon bloodHeadLeft=new ImageIcon(imgDir+"/blood/playBloodHead.png");
		private ImageIcon bloodHeadRight=new ImageIcon(imgDir+"/blood/bloodHead.png");
		private ImageIcon bloodEmpty=new ImageIcon(imgDir+"/blood/bloodEmpty.png");
		private ImageIcon bloodFull=new ImageIcon(imgDir+"/blood/playBloodFull.png");
		private int imageWidth=bloodEmpty.getIconWidth();
		private int imageHeight=bloodEmpty.getIconHeight();
		private int left_headWidth=bloodHeadLeft.getIconWidth();
		private int left_headHeight=bloodHeadLeft.getIconHeight();
		private int right_headWidth=bloodHeadRight.getIconWidth();
		private int right_headHeight=bloodHeadRight.getIconHeight();
		public void exhibitBlood(Graphics g,int x,int y,int length,int height,int offset)
		{
			if(runningParameter.getPlayerUnit()==null)
				return;
			BaseUnit unit=runningParameter.getPlayerUnit();
			double HP_percentage;

			
			HP_percentage=unit.getHP_CUR()/unit.getHP_MAX();	
			int total_length=length;//总长度
			int blood_height=height;//血条高度
			//血条左边的左上角坐标
			int left_head_left_x=x;
			int left_head_left_y=y;
			//血条左边的右下角坐标
			int left_head_right_x=x+blood_height;
			int left_head_right_y=y+blood_height;
			
			//剩余血量的长度
			int full_length=(int)(total_length*HP_percentage);
			
			//剩余血量左上角坐标
			int full_left_x=left_head_right_x+offset;
			int full_left_y=y;

			//剩余血量右下角坐标
			int full_right_x=full_left_x+full_length;
			int full_right_y=full_left_y+blood_height;
			
			//失去血量的长度
			int empty_length=total_length-full_length;

			//失去血量的左上角坐标
			int empty_left_x=full_right_x;
			int empty_left_y=full_left_y;
			
			//失去血量的右下角坐标
			int empty_right_x=empty_left_x+empty_length;
			int empty_right_y=full_right_y;
			
			//图片剪切线
			int img_mid_line=(int)(imageWidth*HP_percentage);
			
			//剩余血量部分
			g.drawImage(bloodFull.getImage(), 
					full_left_x,full_left_y,//显示界面左上点
					full_right_x,full_right_y,//显示界面右上点
					0,0,
					img_mid_line,imageHeight,
					null);
			//失去血量部分
			g.drawImage(bloodEmpty.getImage(), 
					empty_left_x,empty_left_y,//显示界面左上点
					empty_right_x,empty_right_y,
					img_mid_line,0,
					imageWidth,imageHeight,
					null);
			//血条左边
			g.drawImage(bloodHeadLeft.getImage(), 
					left_head_left_x,left_head_left_y,//显示界面左上点
					left_head_right_x,left_head_right_y,//显示界面右上点
					0,0,
					left_headWidth,left_headHeight,
					//Color.BLUE,
					null);
			//血条右边
			g.drawImage(bloodHeadRight.getImage(), 
					empty_right_x,empty_left_y,//显示界面左上点
					empty_right_x+right_headWidth,empty_right_y,//显示界面右上点
					0,0,
					right_headWidth,right_headHeight,
					null);
		}
	}
}
