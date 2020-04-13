package com.rock.camp;

//阵营
public class BaseCamp {
	/**
	 * 阵营中的成员互为友军，无法对对方造成伤害(触发碰撞)。
	 * 不同阵营者为敌军，可以造成伤害(触发碰撞)。
	 * 因为道具拾取也要触发碰撞，所以道具属于敌军。
	 */
	private String name;//阵营名称
	private String color;//阵营颜色(未使用)
	private int state;//阵营状态【胜利、失败】(未使用)
	//private List<BasePlayer> member;//阵营成员
	
	
	public BaseCamp(String name, String color) {
		super();
		this.name = name;
		this.color = color;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
}
