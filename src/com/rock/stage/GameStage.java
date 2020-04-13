package com.rock.stage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;

import com.rock.UI.GameFrame;
import com.rock.parameter.GameParameter;
import com.rock.parameter.RunningParameter;
import com.rock.stage.GameWave.UnitType;
import com.rock.unit.BaseUnit;
import com.rock.unit.BattleUnit;
import com.rock.unit.factory.UnitFactory;
import com.rock.util.handle.BaseHandle;
import com.rock.util.handle.StageHandle;

public class GameStage {
	/**
	 * 关卡信息，负责控制出怪。
	 * name会显示在右上角
	 * currentTime会递减，0就会出怪并且进入下一波的倒数
	 * nextWave是下一步要生成的怪
	 * currentUnit记录下存活的单位(不包括子弹和道具)
	 */
	private String name;
	private ImageIcon background;
	private int currentTime;
	private Queue<GameWave> waveQueue;
	private GameWave nextWave;
	private List<BaseUnit> currentUnit;
	private String reward;
	private boolean finish;
	
	public static BaseHandle handle=new StageHandle();
	
	public GameStage()
	{
		background=null;
		currentTime=0;
		currentUnit=new LinkedList<>();
		waveQueue=new LinkedList<>();
		nextWave=null;
		finish=false;
	}
	
	public boolean waveFlow()
	{
		//删掉已经死掉的单位。用迭代器
		Iterator<BaseUnit> iterator=currentUnit.iterator();
		while(iterator.hasNext())
		{
			BaseUnit unit=iterator.next();
			if(unit.getState()==GameParameter.UnitState_Dying
					||unit.getState()==GameParameter.UnitState_Dead)
				iterator.remove();
		}
		
		//如果下一波是空的，意味着它已经被加载过了
		if(nextWave==null)
		{
			//如果下一波是空的并且队列里面没有wave，意味着当前关卡结束
			if(waveQueue.isEmpty())
			{
				if(currentUnit.isEmpty())
				{
					this.finish=true;
					return false;
				}
				return true;
			}
			//从队列里取数据
			nextWave=waveQueue.poll();
			currentTime=nextWave.getTime();
		}
		//如果不需要清完就出怪
		if(nextWave.isClear()==false)
		{
			//到时间就出怪
			if(--currentTime<0)
			{
				loadWave(nextWave);
				nextWave=null;
			}
		}
		else //否则
		{
			//如果单位已被清完
			if(currentUnit.size()<=0)
			{
				//开始倒数，倒数完出怪
				if(--currentTime<0)
				{
					loadWave(nextWave);
					nextWave=null;
				}
			}
		}
		return true;
	}
	//加载当前wave
	private void loadWave(GameWave currentWave)
	{

		List<UnitType> list=currentWave.getUnits();
		int size=list.size();
		//按照单位类型以及数量加到currentUnit这个list里面
		for(int i=0;i<size;i++)
		{
			UnitType type=list.get(i);
			for(int num=type.getNum();num>0;num--)
			{
				RunningParameter rP=GameFrame.getGameFrame().getRunningParameter();
				BaseUnit unit=UnitFactory.getUnitFacory().createBattleUnit(type.getType(),rP.getComputer());
				//如果单位是战斗单位，才会被添加，排除道具
				if(unit instanceof BattleUnit)
					currentUnit.add(unit);
				//不管是不是道具都要被加到玩家单位队列里面
				rP.getComputer().addUnitToUnitList(unit);
			}
			
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ImageIcon getBackground() {
		return background;
	}

	public void setBackground(ImageIcon background) {
		this.background = background;
	}

	public int getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	public Queue<GameWave> getWaveQueue() {
		return waveQueue;
	}
	public void setWaveQueue(Queue<GameWave> waveQueue) {
		this.waveQueue = waveQueue;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	

	public boolean isFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "GameStage [name=" + name + ", currentTime=" + currentTime + ", waveQueue=" + waveQueue
				+ ", currentUnit=" + currentUnit + "]";
	}
	
	
	
}
