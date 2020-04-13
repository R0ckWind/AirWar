package com.rock.util;

import com.rock.unit.BaseUnit;

//碰撞判断工具
public class CollideJudgeUtil {
	public static boolean isCollide(BaseUnit unit1,BaseUnit unit2)
	{
		int region1_x_start=unit1.getCoordinate_x()-unit1.getSize_w()/2;
		int region1_x_end=region1_x_start+unit1.getSize_w();
		int region1_y_start=unit1.getCoordinate_y()-unit1.getSize_h()/2;
		int region1_y_end=region1_y_start+unit1.getSize_h();
		int region2_x_start=unit2.getCoordinate_x()-unit2.getSize_w()/2;
		int region2_x_end=region2_x_start+unit2.getSize_w();
		int region2_y_start=unit2.getCoordinate_y()-unit2.getSize_h()/2;
		int region2_y_end=region2_y_start+unit2.getSize_h();
		boolean b1=(region1_x_end>=region2_x_start&&region1_x_start<=region2_x_end)
				||(region2_x_end>=region1_x_start&&region2_x_start<=region1_x_end);
		boolean b2=(region1_y_end>=region2_y_start&&region1_y_start<=region2_y_end)
				||(region2_y_end>=region1_y_start&&region2_y_start<=region1_y_end);
		return b1&b2;
	}

}
