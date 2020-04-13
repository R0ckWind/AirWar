package com.rock.unit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import javax.swing.ImageIcon;

import com.rock.camp.BasePlayer;
import com.rock.parameter.GameParameter;
import com.rock.parameter.SystemParameter;
import com.rock.unit.skill.BaseSkill;
import com.rock.unit.behavior.BaseBehavior;
import com.rock.unit.behavior.CommonBehavior;
import com.rock.unit.buff.BuffList;
import com.rock.unit.buff.BuffState;
import com.rock.unit.factory.DieDrop;
import com.rock.util.ClockCounter;

/**
 * 基本单位类
 * 每一个单位所属一个玩家
 */
abstract public class BaseUnit {

	protected long unitID;
	protected String name;//单位名称
	protected int level;//等级
	protected double EXP;//经验
	protected double damege;//攻击力
	protected double aSpeed;//攻击速度
	protected double critChance;//暴击率
	protected double defence;//护甲
	protected int movement;//移速
	protected double HP_MAX;//血量上限
	protected double MP_MAX;//蓝量上限
	protected double HP_CUR;//当前血量
	protected double MP_CUR;//当前蓝量
	protected double HP_Healling_Rate;//生命恢复速度/秒
	protected double MP_Recover_Rate;//法力恢复速度/秒
	
	protected BaseSkill defaultSkill;//默认技能
	
	protected ImageIcon currentSkin;//单位贴图-当前
	protected ImageIcon commonSkin;//单位贴图-正常
	protected ImageIcon transformSkin;//单位贴图-变形
	protected ImageIcon beHitSkin;//单位贴图-被攻击
	protected int size_w;//贴图宽度
	protected int size_h;//贴图高度
	
	protected int coordinate_x;//x坐标
	protected int coordinate_y;//y坐标
	
	protected int borderX;
	protected int borderY;
	
	protected int state;//单位状态(存活、正在死亡、已死亡)
	protected boolean hitable;//是否可被攻击
	protected boolean blood;//是否显血
	
	protected BuffList buffList;//buff列表
	protected BuffList deBuffList;//debuff列表
	
	protected String fireUnit;//子弹
	protected DieDrop dieDrop;//死亡掉落
	protected BasePlayer player;//所属玩家
	
	protected ClockCounter attackClock;//攻击间隔计时器
	protected ClockCounter beHitClock;//被击中变色计时器
	
	protected Map<Long,Integer> hitMap;//击中单位map
	protected Queue<BaseBehavior> behaviorQueue;//行为队列
	protected BaseBehavior currentBehavior;//当前行为
	
	public BaseUnit()
	{
		hitMap=new HashMap<>();
		attackClock=null;//攻击间隔计时器需自行定义
		beHitClock=new ClockCounter(GameParameter.UnitBeHit_UnitTime);
		behaviorQueue=new LinkedList<>();
		dieDrop=null;
		currentBehavior=null;
		
	}
	abstract public void build();//单位创建
	abstract public void attack();//攻击，无目标，返回造成的伤害值。
	abstract public double attack(BaseUnit target);//普通攻击，带目标，返回造成的伤害值。
	
	public void specialAttack()//默认特殊攻击
	{
		defaultSkill.excute(this,null);
	}
	
	public void specialAttack(BaseSkill skill)//指定特殊攻击
	{
		skill.excute(this,null);
	}
	public double specialAttack(BaseSkill skill,BaseUnit target)//指定特殊攻击，带目标
	{
		return skill.excute(this,target);
		
	}

	//通用动作，每次循环调用
	public void action()
	{
		if(currentBehavior==null)
		{
			if(!behaviorQueue.isEmpty())
			{
				//获取行为列表中的行为
				currentBehavior=behaviorQueue.poll();		
			}
			else 
			{
				//否则只进行普通行为
				currentBehavior=CommonBehavior.behavior;
			}
		}
		//执行行为
		currentBehavior=currentBehavior.action(this);
	}
	abstract public void move();//移动
	abstract public void beHit();//被攻击时
	abstract public void beHitBy(BaseUnit unit);//被攻击时
	abstract public void dying();//正在死亡时
	abstract public void die();//完全死亡后
	abstract public void rewardKiller(BaseUnit unit);//击杀者奖励
	
	//添加行为
	public void addBehavior(BaseBehavior behavior)
	{
		behaviorQueue.add(behavior);
	}
	//打断所有正在进行的动作
	public void cutBehavior()
	{
		currentBehavior=null;
		behaviorQueue.clear();
	}
	//更新map中单位的有效时间，如果value=0，剔除
	public void mapRefresh()
	{
		Iterator<Entry<Long, Integer>> iterator = hitMap.entrySet().iterator();
		while(iterator.hasNext()){
		    Entry<Long, Integer> entry = iterator.next();
		    entry.setValue(entry.getValue()-1);
		    if(entry.getValue()<0)
		    {
		    	iterator.remove();;
		    }
		}
	}
	//添加新的命中单位
	public void addHit(BaseUnit hitUnit)
	{
		long id=hitUnit.getUnitID();
		hitMap.put(id, GameParameter.UnitHit_SaveTime);
	}
	
	public long getUnitID() {
		return unitID;
	}

	public void setUnitID(long unitID) {
		this.unitID = unitID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getEXP() {
		return EXP;
	}
	public void setEXP(double eXP) {
		EXP = eXP;
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
		this.aSpeed = aSpeed;
	}

	
	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
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
	public double getHP_MAX() {
		return HP_MAX;
	}
	public void setHP_MAX(double hP_MAX) {
		HP_MAX = hP_MAX;
	}
	public double getMP_MAX() {
		return MP_MAX;
	}
	public void setMP_MAX(double mP_MAX) {
		MP_MAX = mP_MAX;
	}
	public double getHP_CUR() {
		return HP_CUR;
	}
	
	public void gainHeal(double health)
	{
		HP_CUR+=health;
		if(HP_CUR>HP_MAX)
			HP_CUR=HP_MAX;
		if(HP_CUR<0)
			HP_CUR=0;
	}
	public void receivedInjury(double health)
	{
		gainHeal(-health);
	}
	public double getMP_CUR() {
		return MP_CUR;
	}
	
	public void recoveryMana(double mana) {

		MP_CUR+=mana;
		if(MP_CUR>MP_MAX)
			MP_CUR=MP_MAX;
		if(MP_CUR<0)
			MP_CUR=0;
	}
	public void costMana(double mana) {

		recoveryMana(-mana);
	}
	public ImageIcon getCurrentSkin() {
		return currentSkin;
	}
	public void setCurrentSkin(ImageIcon skin) {
		this.currentSkin = skin;
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
	public int getCoordinate_x() {
		return coordinate_x;
	}
	public void setCoordinate_x(int coordinate_x) {
		this.coordinate_x = coordinate_x;
	}
	public int getCoordinate_y() {
		return coordinate_y;
	}
	public void setCoordinate_y(int coordinate_y) {
		this.coordinate_y = coordinate_y;
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

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void addBuff(BuffState buff)
	{
		//有时效的buff功能需要添加额外的属性。暂时不做
		gainHeal(buff.getHP_CurGain());
		recoveryMana(buff.getMP_CurGain());
		
	}
	
	
	public BuffList getDeBuffList() {
		return deBuffList;
	}
	public void setDeBuffList(BuffList deBuffList) {
		this.deBuffList = deBuffList;
	}
	public String getFireUnit() {
		return fireUnit;
	}
	public void setFireUnit(String fireUnit) {
		this.fireUnit = fireUnit;
	}
	
	


	public BasePlayer getPlayer() {
		return player;
	}

	public void setPlayer(BasePlayer player) {
		this.player = player;
	}

	
}
