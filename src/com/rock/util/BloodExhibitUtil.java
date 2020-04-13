package com.rock.util;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.rock.parameter.GameParameter;
import com.rock.unit.BaseUnit;

//显血工具
public class BloodExhibitUtil {
	private final static String BloodDir=System.getProperty("user.dir")+"/resources/img/blood";
	private static ImageIcon bloodHead=new ImageIcon(BloodDir+"/bloodHead.png");
	private static ImageIcon bloodEmpty=new ImageIcon(BloodDir+"/bloodEmpty.png");
	private static ImageIcon bloodFull=new ImageIcon(BloodDir+"/bloodFull.png");
	private static int imageWidth=bloodEmpty.getIconWidth();
	private static int imageHeight=bloodEmpty.getIconHeight();
	private static int headWidth=bloodHead.getIconWidth();
	private static int headHeight=bloodHead.getIconHeight();
	public static void exhibitBlood(BaseUnit unit,Graphics g)
	{
		double HP_percentage=unit.getHP_CUR()/unit.getHP_MAX();
		if(unit.getState()<=GameParameter.UnitState_Alive)
		{
			
			int blood_height=GameParameter.Blood_Height;//血条高度
			int total_length=unit.getSize_w();//血条长度

			//剩余血量长度
			int full_length=(int)(unit.getSize_w()*HP_percentage);		
			//剩余血量左上角坐标
			int full_left_x=unit.getCoordinate_x()-unit.getSize_w()/2;
			int full_left_y=unit.getCoordinate_y()-unit.getSize_h()/2-blood_height;
			//剩余血量右下角坐标
			int full_right_x=full_left_x+full_length;
			int full_right_y=full_left_y+blood_height;
			
			//失去血量长度
			int empty_length=total_length-full_length;		
			//失去血量左上角坐标
			int empty_left_x=full_right_x;
			int empty_left_y=full_left_y;
			//失去血量右下角坐标
			int empty_right_x=empty_left_x+empty_length;
			int empty_right_y=full_right_y;
			
			//图片分割线
			int img_mid_line=(int)(imageWidth*HP_percentage);
			
			//血条左边
			g.drawImage(bloodHead.getImage(), 
					full_left_x-headWidth,full_left_y,
					full_left_x,full_right_y,
					0,0,
					headWidth,headHeight,
					null);
			//剩余血量部分
			g.drawImage(bloodFull.getImage(), 
					full_left_x,full_left_y,
					full_right_x,full_right_y,
					0,0,
					img_mid_line,imageHeight,
					null);
			//失去血量部分
			g.drawImage(bloodEmpty.getImage(), 
					empty_left_x,empty_left_y,
					empty_right_x,empty_right_y,
					img_mid_line,0,
					imageWidth,imageHeight,
					null);
			//血条右边
			g.drawImage(bloodHead.getImage(), 
					empty_right_x,full_left_y,
					empty_right_x+headWidth,full_right_y,
					0,0,
					headWidth,headHeight,
					null);
		}
	}
}
