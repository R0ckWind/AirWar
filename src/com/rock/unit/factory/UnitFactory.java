package com.rock.unit.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.unit.BaseUnit;
import com.rock.unit.BattleUnit;
import com.rock.unit.FireUnit;
import com.rock.unit.ItemUnit;

public class UnitFactory {
	/**
	 * 单位创建工厂
	 */
	private long createID=0;//单位id，每创建一个单位，id+1，没有取余，所以存在越界风险。
	private static final String unitStr="com.rock.unit.";
	public static class UnitData
	{
		private String name;
		private String impl;//实现类
		private String fire;
		private String[] dieDrop;
		private double damege;
		private double aSpeed;
		private double critChance;
		private double defence;
		private int movement;
		private double HP;
		private double MP;
		private ImageIcon commonSkin;
		private ImageIcon transformSkin;
		private ImageIcon beHitSkin;
		private int size_w;
		private int size_h;
		private boolean hitable;
		private boolean blood;
		private boolean permeable;
	
		public UnitData()
		{
			this.name="";
			this.impl="";
			this.fire="";
			this.dieDrop=null;
			this.damege=0;
			this.aSpeed=1;
			this.critChance=0;
			this.defence=0;
			this.movement=1;
			this.HP=1;
			this.MP=0;
			this.commonSkin=null;
			this.transformSkin=null;
			this.beHitSkin=null;
			this.size_w=10;
			this.size_h=10;
			this.hitable=true;
			this.blood=false;
			this.permeable=false;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImpl() {
			return impl;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}

		public String getFire() {
			return fire;
		}

		public void setFire(String fire) {
			this.fire = fire;
		}
	
		public String [] getDieDrop() {
			return dieDrop;
		}

		public void setDieDrop(String []dieDrop) {
			this.dieDrop = dieDrop;
		}

		public double getDamege() {
			return damege;
		}

		public void setDamege(double damege) {
			this.damege = damege;
		}

		public double getaSpeed() {
			return aSpeed;
		}
		
		public void setaSpeed(double aSpeed) {
			if(aSpeed>GameParameter.AttackSpeedupper)
				this.aSpeed=GameParameter.AttackSpeedupper;
			else if(aSpeed<=0)
				this.aSpeed=1;//不能让它除0
			else 
				this.aSpeed = aSpeed;
		}

		public double getCritChance() {
			return critChance;
		}

		public void setCritChance(double critChance) {
			if(critChance>1)
				critChance=1;
			this.critChance = critChance;
		}

		
		public double getDefence() {
			return defence;
		}

		public void setDefence(double defence) {
			this.defence = defence;
		}

		public int getMovement() {
			return movement;
		}

		public void setMovement(int movement) {
			this.movement = movement;
		}

		public double getHP() {
			return HP;
		}

		public void setHP(double hP) {
			HP = hP;
		}

		public double getMP() {
			return MP;
		}

		public void setMP(double mP) {
			MP = mP;
		}

		public ImageIcon getCommonSkin() {
			return commonSkin;
		}

		public void setCommonSkin(ImageIcon commonSkin) {
			this.commonSkin = commonSkin;
		}

		public ImageIcon getTransformSkin() {
			return transformSkin;
		}

		public void setTransformSkin(ImageIcon transfromSkin) {
			this.transformSkin = transfromSkin;
		}

		public ImageIcon getBeHitSkin() {
			return beHitSkin;
		}

		public void setBeHitSkin(ImageIcon beHitSkin) {
			this.beHitSkin = beHitSkin;
		}

		public int getSize_w() {
			return size_w;
		}

		public void setSize_w(int size_w) {
			this.size_w = size_w;
		}

		public int getSize_h() {
			return size_h;
		}

		public void setSize_h(int size_h) {
			this.size_h = size_h;
		}

		public boolean isHitable() {
			return hitable;
		}

		public void setHitable(boolean hitable) {
			this.hitable = hitable;
		}
		
		public boolean isBlood() {
			return blood;
		}

		public void setBlood(boolean blood) {
			this.blood = blood;
		}

		public boolean isPermeable() {
			return permeable;
		}

		public void setPermeable(boolean permeable) {
			this.permeable = permeable;
		}
	
	}
	//用于记录单位数据
	private Map<String,UnitData> unitDataMap;
	private static volatile UnitFactory unitFactory;
	private UnitFactory(){
		unitDataMap=new HashMap<String, UnitFactory.UnitData>();
	};
	public static UnitFactory getUnitFacory()
	{
		if(unitFactory==null)
		{
			synchronized(UnitFactory.class)
			{
				if(unitFactory==null)
				{
					unitFactory=new UnitFactory();
				}
			}
		}
		return unitFactory;
	}
	//创建战斗单位
	public BattleUnit createBattleUnit(String type,BasePlayer player)
	{
		try {
			UnitData data=unitDataMap.get(type);
			Class implClass=Class.forName(unitStr+data.getImpl());
			Constructor constructor=implClass.getConstructor(UnitData.class,BasePlayer.class);
			BattleUnit newUnit=(BattleUnit) constructor.newInstance(data,player);
			newUnit.setUnitID(nextCreateID());
			return newUnit;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//创建子弹
	public FireUnit createFireForUnit(BaseUnit unit,boolean critHit,int angle)
	{
		try {
			UnitData data=unitDataMap.get(unit.getFireUnit());
			Class implClass=Class.forName(unitStr+data.getImpl());
			Constructor constructor=implClass.getConstructor(UnitData.class,BaseUnit.class,boolean.class,int.class);
			FireUnit newUnit=(FireUnit) constructor.newInstance(data,unit,critHit,angle);
			newUnit.setUnitID(nextCreateID());
			return newUnit;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//创建战斗单位
		public ItemUnit createItemUnit(String type,BasePlayer player,int x,int y,int w,int h)
		{
			try {
				UnitData data=unitDataMap.get(type);
				Class implClass=Class.forName(unitStr+data.getImpl());
				Constructor constructor=
						implClass.getConstructor(UnitData.class,BasePlayer.class,int.class,int.class,int.class,int.class);
				ItemUnit newUnit=(ItemUnit) constructor.newInstance(data,player,x,y,w,h);
				newUnit.setUnitID(nextCreateID());
				return newUnit;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
	public void putUnitIntoUnitDataMap(UnitData unitData) {
		unitDataMap.put(unitData.getName(), unitData);
	}
	public long nextCreateID()
	{
		long id=createID;
		synchronized(UnitFactory.class)
		{
			createID++;
		}
		return id;
	}
	
}
