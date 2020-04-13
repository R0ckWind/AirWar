package com.rock.parameter;

import java.util.ArrayList;
import java.util.List;

import com.rock.UI.GameFrame;
import com.rock.camp.BasePlayer;
import com.rock.camp.Computer;
import com.rock.camp.Player;
import com.rock.camp.BaseCamp;
import com.rock.run.CreateUnitThread;
import com.rock.stage.GameStage;
import com.rock.unit.BaseUnit;
import com.rock.util.FileHandleUtil;

/**
 * 运行参数
 * 是随着游戏进行而一直在变动的数据
 * 属性是非静态
 * 
 */
public final class RunningParameter {

	private boolean isUp;//是否向上
	private boolean isDown;//是否向下
	private boolean isLeft;//是否向左
	private boolean isRight;//是否向右
	private boolean isAttack;//是否攻击
	private int mission;//当前所在关卡
	private GameStage currentStage;//当前关卡数据
	
	private BaseUnit playerUnit;//玩家的战机
	private BasePlayer player;//玩家
	private BasePlayer computer;//电脑
	private List<BasePlayer> playerList;//玩家列表
	private volatile int gameState;//游戏状态
	
	
	public RunningParameter()
	{
		//初始化
		init();
		//设置玩家
		player=new Player("玩家1",null,3);
		computer=new Computer("电脑");
		
		//设置阵营
		player.setCamp(new BaseCamp("玩家阵营", null));
		computer.setCamp(new BaseCamp("电脑阵营", null));
		
		//添入列表
		this.playerList=new ArrayList<>();
		playerList.add(player);
		playerList.add(computer);
	}
	//初始化参数
	public void init()
	{
		setFalse();
		this.mission=0;
		this.gameState=GameParameter.GameState_Menu;
	}
	//读取下一关的数据
	public GameStage loadNextMission()
	{
		//如果已经是最后一关,就将游戏状态改为结束。
		if(mission>=SystemParameter.StageFile.length)
		{
			return null;
		}
		//根据文件名读取数据
		currentStage=FileHandleUtil.handleFile
				(SystemParameter.StageFile[mission++], 
						GameStage.handle);
		return currentStage;	
	}
	//清楚所有单位
	public void clearAllUnit()
	{
		int size=playerList.size();
		//清除所有单位
		for(int i=0;i<size;i++)
		{
			playerList.get(i).getPlayerUnitList().clear();
		}
	}
	
	//清除除了玩家战机之外的所有单位。
	public void clearUnitExpectPlayerUnit()
	{
		clearAllUnit();
		player.addUnitToUnitList(playerUnit);
	}
	//重新开始
	public void restart()
	{
		init();
		int size=playerList.size();
		//清除所有单位
		for(int i=0;i<size;i++)
		{
			playerList.get(i).getPlayerUnitList().clear();
		}
		setFalse();//将按键全部重置为false
		player.setScore(0);//积分清空
	}
	
	//返回x轴的运动方向(-1为左，0为横向静止，+1为右)
	public int getDirectionX()
	{
		int result=0;
		if(isLeft)
			result--;
		if(isRight)
			result++;
		return result;
	}
	//返回y轴的运动方向(-1为上，0为纵向静止，+1为下)
	public int getDirectionY()
	{
		int result=0;
		if(isUp)
			result--;
		if(isDown)
			result++;
		return result;
	}
	public void setFalse()
	{
		isUp=false;
		isDown=false;
		isLeft=false;
		isRight=false;
		isAttack=false;
	}
	
	public boolean isUp() {
		return isUp;
	}
	public void setUp(boolean up) {
		isUp = up;
	}
	public boolean isDown() {
		return isDown;
	}
	public void setDown(boolean down) {
		isDown = down;
	}
	public boolean isLeft() {
		return isLeft;
	}
	public void setLeft(boolean left) {
		isLeft = left;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean right) {
		isRight = right;
	}
	public boolean isAttack() {
		return isAttack;
	}
	public void setAttack(boolean attack) {
		isAttack = attack;
	}
	public GameStage getCurrentStage()
	{
		return currentStage;
	}
	
	
	public BaseUnit getPlayerUnit() {
		return playerUnit;
	}
	public void setPlayerUnit(BaseUnit playerUnit) {
		this.playerUnit = playerUnit;
	}
	public BasePlayer getPlayer() {
		return player;
	}

	public void setPlayer(BasePlayer player) {
		this.player = player;
	}

	public BasePlayer getComputer() {
		return computer;
	}

	public void setComputer(BasePlayer computer) {
		this.computer = computer;
	}
	
	public List<BasePlayer> getPlayerList() {
		return playerList;
	}

	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	
}
