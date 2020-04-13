package com.rock.parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.rock.util.FileHandleUtil;
import com.rock.util.handle.BuffDataHandle;
import com.rock.util.handle.UnitDataHandle;


/**
 * 系统数据
 * 是通过配置文件读取出来的
 * 可通过修改配置文件或者通过设置选项来变更
 */
public class SystemParameter {

	public static final String[] GameLevel;//游戏难度对应名称
	public static final String Font;//字体
	public static final int InterfaceW;//游戏整体宽度
	public static final int InterfaceH;//游戏整体高度
	public static final int FPS_Sleep;//每隔多久重画一次，决定游戏帧数
	public static final String UnitDataURL;//单位数据的存放位置
	public static final String BuffDataURL;//buff数据的存放位置
	public static final String[]  StageFile;//用来存放关卡的读取文件名
	
	/*已经用了Map了还设置以下值主要是为了方便判断*/
	public static char UpKey;//上
	public static char DownKey;//下
	public static char LeftKey;//左
	public static char RightKey;//右
	public static char AttackKey;//攻击
	public static char SAtackKey;//超级攻击
	
	public static  Map<String,KeybordAction>keyCtrMap;
	public static Map<Character, KeybordAction> keyUseMap;
	public static int CurrentLevel=0;//游戏难度，默认为0
	static{//静态代码块，在类加载的时候就执行。
		System.out.println("系统参数初始化...");
		//读取基本参数
		Properties ps=FileHandleUtil.handleFile("config.pro");
		GameLevel=ps.getProperty("GameLevel").trim().split(":");
		Font=ps.getProperty("Font").trim();
		InterfaceW=Integer.parseInt(ps.getProperty("InterfaceW").trim());
		InterfaceH=Integer.parseInt(ps.getProperty("InterfaceH").trim());
		FPS_Sleep=1000/Integer.parseInt(ps.getProperty("FPS").trim());
		UnitDataURL=ps.getProperty("UnitDataURL").trim();
		BuffDataURL=ps.getProperty("BuffDataURL").trim();
		StageFile=ps.getProperty("Stage").trim().split(":");
		//读取控制信息
		ps=FileHandleUtil.handleFile("controller.pro");
		char up=ps.getProperty("UpKey").trim().toCharArray()[0];
		char down=ps.getProperty("DownKey").trim().toCharArray()[0];
		char left=ps.getProperty("LeftKey").trim().toCharArray()[0];
		char right=ps.getProperty("RightKey").trim().toCharArray()[0];
		char attack=ps.getProperty("AttackKey").trim().toCharArray()[0];
		char satack=ps.getProperty("SAtackKey").trim().toCharArray()[0];
		//将控制信息装入map
		keyCtrMap=new HashMap<>();
		keyCtrMap.put("UpKey", new KeybordAction("UpKey",up));
		keyCtrMap.put("DownKey", new KeybordAction("DownKey",down));
		keyCtrMap.put("LeftKey", new KeybordAction("LeftKey",left));
		keyCtrMap.put("RightKey", new KeybordAction("RightKey",right));
		keyCtrMap.put("AttackKey", new KeybordAction("AttackKey",attack));
		keyCtrMap.put("SAtackKey", new KeybordAction("SAtackKey",satack));
		//设置已用按键信息
		keyUseMap=new HashMap<>();
		keyUseMap.put(up, keyCtrMap.get("UpKey"));
		keyUseMap.put(down,keyCtrMap.get("DownKey"));
		keyUseMap.put(left, keyCtrMap.get("LeftKey"));
		keyUseMap.put(right, keyCtrMap.get("RightKey"));
		keyUseMap.put(attack,keyCtrMap.get("AttackKey"));
		keyUseMap.put(satack, keyCtrMap.get("SAtackKey"));
		//给全局按键变量赋值
		refreshCtr();
		FileHandleUtil.handleFile(UnitDataURL,new UnitDataHandle());
		FileHandleUtil.handleFile(BuffDataURL,new BuffDataHandle());
		System.out.println("参数初始化完成");
	} 
	//更新控制器映射，返回的字符串用于保存文件
	public static String refreshCtr()
	{
		UpKey=keyCtrMap.get("UpKey").getTrigger();
		DownKey=keyCtrMap.get("DownKey").getTrigger();
		LeftKey=keyCtrMap.get("LeftKey").getTrigger();
		RightKey=keyCtrMap.get("RightKey").getTrigger();
		AttackKey=keyCtrMap.get("AttackKey").getTrigger();
		SAtackKey=keyCtrMap.get("SAtackKey").getTrigger();
		StringBuilder buffer=new StringBuilder("");
		buffer.append("UpKey="+UpKey+"\r\n");
		buffer.append("DownKey="+DownKey+"\r\n");
		buffer.append("LeftKey="+LeftKey+"\r\n");
		buffer.append("RightKey="+RightKey+"\r\n");
		buffer.append("AttackKey="+AttackKey+"\r\n");
		buffer.append("SAtackKey="+SAtackKey+"\r\n");
		return buffer.toString();
	}
	//内部类——按键控制类
	public static class KeybordAction
	{
		
		private String effct;//按键作用
		private char trigger;//触发键位
		
		public KeybordAction(String effct, char trigger) {
			this.effct = effct;
			this.trigger = trigger;
		}
		public String getEffct() {
			return effct;
		}
		public void setEffct(String effct) {
			this.effct = effct;
		}
		public char getTrigger() {
			return trigger;
		}
		public void setTrigger(char trigger) {
			this.trigger = trigger;
		}
		
	}
	
}
