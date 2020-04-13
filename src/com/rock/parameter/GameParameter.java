package com.rock.parameter;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.rock.util.FileHandleUtil;

/**
 * 游戏设定
 * 只存储常量
 * 理论上是不应该被修改的
 * 唯一修改的方式是在游戏开始前修改代码。
 * */

public final class GameParameter {
	
	//游戏状态
	public static final int GameState_Menu=1;//菜单
	public static final int GameState_Set=2;//设置
	public static final int GameState_Load=3;//加载中
	public static final int GameState_Running=4;//运行中
	public static final int GameState_Pause=5;//暂停中
	public static final int GameState_Finish=6;//结束
	public static final int GameState_Endding=7;//关闭
	//单位状态
	public static final int UnitState_Build=1;//生成中
	public static final int UnitState_Alive=2;//存活
	public static final int UnitState_Dying=3;//正在死亡
	public static final int UnitState_Dead=4;//已经死亡
	
	
	public static final int UnitBuild_TimeUnit=500;//单位出现间隔
	public static final int UnitAction_TimeUnit=20;//单位行动间隔
	public static final int UnitBeHit_UnitTime=3;//单位被击中后变色时间间隔
	public static final int UnitHit_SaveTime=100;//最后击中者保留时间间隔
	public static final int AttackSpeedupper=1000/UnitAction_TimeUnit;//单位攻速上限

	
	public static final int Dying_Animation_Icon_Size=8;//爆炸贴图数量
	public static final int Dying_Animation_Frames_Last=2;//单位死亡动画播放时长
	
	
	public static final int Blood_Height=10;//血条高度
	
	public static final double []GameLevel_Multiple={1,1.5,2.5,5,8,12};//难度系数
	
	//项目路径
	public static final  String Resources_Dir=System.getProperty("user.dir")+"/resources/";
	
	//载入界面
	public static final  ImageIcon LoaddingIMG=new ImageIcon
			(Resources_Dir+"/img/loadding.png");
	//爆炸效果
	public static final ImageIcon []Dying_Skin_Icon=new ImageIcon[Dying_Animation_Icon_Size];

	static{
		for(int i=0;i<Dying_Animation_Icon_Size;i++)
		{
			Dying_Skin_Icon[i]=new ImageIcon
					(Resources_Dir+"/img/boom/boom"+(i+1)+".png");
		}
	}
}
