package com.rock.stage;

import java.util.ArrayList;
import java.util.List;


public class GameWave {
	/**
	 * 波信息，一个关卡中含有多波。每一波怪物死亡之后会产生道具奖励
	 * isClear表示是否上一波清完之后才出怪
	 */
	private int time;//过了time时间之后才会刷怪
	private boolean isClear;//是否上一波清完才会刷怪
	private List<UnitType> units;//单位类型
	
	public GameWave()
	{
		time=0;
		isClear=true;
		units=new ArrayList<>();
	}

	public void putUnitIntoList(UnitType unitType)
	{
		units.add(unitType);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isClear() {
		return isClear;
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}

	public List<UnitType> getUnits() {
		return units;
	}



	@Override
	public String toString() {
		return "GameWave [time=" + time + ", isClear=" + isClear + ", units=" + units + "]";
	}

	//单位类型内部类
	public class UnitType
	{
		private String type;
		private int num;
		public UnitType(String type, int num) {
			this.type = type;
			this.num = num;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		@Override
		public String toString() {
			return "UnitType [type=" + type + ", num=" + num + "]";
		}
		
	}
}
