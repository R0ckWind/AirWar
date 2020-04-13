package com.rock.camp;

import java.util.LinkedList;
import java.util.List;

import com.rock.unit.BaseUnit;
import com.rock.unit.factory.UnitFactory;

public abstract class BasePlayer {
	/**
	 * 一个玩家所属一个阵营。
	 * 相同阵营的玩家互为友军
	 * 不同阵营的玩家互为敌军
	 * 就飞机大战而言，玩家有两个
	 * 一个为控制者，一个为电脑
	 * 两方分处两个不同的阵营
	 */
	private String name;//玩家名称
	private String color;//玩家颜色(未使用)
	private int score;//分数
	private int life;//命数(未使用)
	private int state;//玩家状态【存活、死亡、失败、胜利】(未使用)
	private BaseCamp camp;//所属阵营
	private List<BaseUnit> playerUnitList;//玩家拥有的单位
	
	
	public BasePlayer(String name, String color, int score,int life) {
		super();
		this.name = name;
		this.color = color;
		this.score = score;
		this.life=life;
		this.playerUnitList=new LinkedList<>();
	}
	
	//损失生命
	abstract public void loseLife();
	//游戏失败
	abstract public void gameOver();
	//游戏胜利
	abstract public void gameVictory();
	
	//判断该玩家是不是敌人
	public boolean isEnemyWith(BasePlayer player)
	{
		//阵营为null的认为是中立单位
		if(this.camp==null||player.getCamp()==null)
			return false;
		if(this.camp==player.getCamp())
			return false;
		return true;
	}
	
	//添加单位到列表中
	public void addUnitToUnitList(BaseUnit unit)
	{
		playerUnitList.add(unit);
	}
	//将所有单位添加到列表中
	public void addAllToUnitList(List<BaseUnit> list) {
		playerUnitList.addAll(list);
	}
	
	//增加积分
	public void increaseScore(int increment)
	{
		this.score+=increment;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public BaseCamp getCamp() {
		return camp;
	}

	public void setCamp(BaseCamp camp) {
		this.camp = camp;
	}
	public List<BaseUnit> getPlayerUnitList() {
		return playerUnitList;
	}

	
}
