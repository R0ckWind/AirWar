package com.rock.unit.buff;

public class BuffState implements Cloneable{
	/**
	 * 状态效果
	 */
	private String name;
	private int duration;//持续时间
	private double damageGain;//攻击增益
	private double defenceGain;//护甲增益
	private int movementGain;//移速增益
	private double HP_Recovery_PS;//血量回升/s
	private double MP_Recovery_PS;//蓝量回复/s
	private double HP_CurGain;//血量瞬间恢复
	private double MP_CurGain;//蓝量瞬间恢复
	private double HP_MaxGain;//血量上限增益
	private double MP_MaxGain;//蓝量上限增益
	private boolean permanent;//是否是永久增益

	public BuffState()
	{
		this.name="";
		this.duration=0;
		this.damageGain=0;
		this.defenceGain=0;
		this.movementGain=0;
		this.HP_Recovery_PS=0;
		this.MP_Recovery_PS=0;
		this.HP_CurGain=0;
		this.MP_CurGain=0;
		this.HP_MaxGain=0;
		this.MP_MaxGain=0;
		this.permanent=false;
	}
	public BuffState cloneSelf()
	{
		BuffState buff=new BuffState();
		buff.setName(this.name);
		buff.setDuration(this.duration);
		buff.setDamageGain(this.damageGain);
		buff.setDefenceGain(this.defenceGain);
		buff.setMovementGain(this.movementGain);
		buff.setHP_Recovery_PS(this.HP_Recovery_PS);
		buff.setMP_Recovery_PS(this.MP_Recovery_PS);
		buff.setHP_CurGain(this.HP_CurGain);
		buff.setMP_CurGain(this.MP_CurGain);
		buff.setHP_MaxGain(this.HP_MaxGain);
		buff.setMP_MaxGain(this.MP_MaxGain);
		buff.setPermanent(this.permanent);
		
		return buff;
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getDamageGain() {
		return damageGain;
	}
	public void setDamageGain(double damageGain) {
		this.damageGain = damageGain;
	}
	public double getDefenceGain() {
		return defenceGain;
	}
	public void setDefenceGain(double defenceGain) {
		this.defenceGain = defenceGain;
	}
	public int getMovementGain() {
		return movementGain;
	}
	public void setMovementGain(int movementGain) {
		this.movementGain = movementGain;
	} 
	public double getHP_Recovery_PS() {
		return HP_Recovery_PS;
	}
	public void setHP_Recovery_PS(double hP_Recovery_PS) {
		HP_Recovery_PS = hP_Recovery_PS;
	}
	public double getMP_Recovery_PS() {
		return MP_Recovery_PS;
	}
	public void setMP_Recovery_PS(double mP_Recovery_PS) {
		MP_Recovery_PS = mP_Recovery_PS;
	}
	
	public double getHP_CurGain() {
		return HP_CurGain;
	}
	public void setHP_CurGain(double hP_CurGain) {
		HP_CurGain = hP_CurGain;
	}
	public double getMP_CurGain() {
		return MP_CurGain;
	}
	public void setMP_CurGain(double mP_CurGain) {
		MP_CurGain = mP_CurGain;
	}
	public double getHP_MaxGain() {
		return HP_MaxGain;
	}
	public void setHP_MaxGain(double hP_MaxGain) {
		HP_MaxGain = hP_MaxGain;
	}
	public double getMP_MaxGain() {
		return MP_MaxGain;
	}
	public void setMP_MaxGain(double mP_MaxGain) {
		MP_MaxGain = mP_MaxGain;
	}
	public boolean isPermanent() {
		return permanent;
	}
	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
	
}
