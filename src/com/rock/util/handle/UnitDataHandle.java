package com.rock.util.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.rock.parameter.GameParameter;
import com.rock.unit.factory.UnitFactory;
import com.rock.unit.factory.UnitFactory.UnitData;

//解析单位数据
public class UnitDataHandle extends BaseHandle{

	@Override
	public <T> T getHandleResult(String str) {
		File file = new File(GameParameter.Resources_Dir+str);
        BufferedReader reader = null;
        try {
            //以行为单位读取文件内容，一次读一整行
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            UnitData unitData=null;
            while ((tempString = reader.readLine()) != null) {
                //跳过注释和空行
            	if(tempString.startsWith("#")||tempString.equals(""))
            		continue;
            	if(tempString.startsWith("[unit]")||tempString.startsWith("[item]"))
            	{
            		if(unitData==null)
            			unitData=new UnitData();
            		else
            		{
            			UnitFactory.getUnitFacory().putUnitIntoUnitDataMap(unitData);
            			unitData=new UnitData();
            		}
            		continue;
            	}
            	String []arr=tempString.split("=");
            	String key=arr[0];
            	String value=arr[1];
            	switch(key)
            	{
            		case "name":
            			unitData.setName(value);
            			break;
            		case "impl":
            			unitData.setImpl(value);
            			break;
            		case "fire":
            			unitData.setFire(value);
            			break;
            		case "drop":
            			unitData.setDieDrop(value.split(":"));
            			break;
            		case "damage":
            			unitData.setDamege(Double.valueOf(value));
            			break;
            		case "aSpeed":
            			unitData.setaSpeed(Double.valueOf(value));
            			break;
            		case "cirtChance":
            			unitData.setCritChance(Double.valueOf(value));
            			break;
            		case "defence":
            			unitData.setDefence(Double.valueOf(value));
            			break;
            		case "movement":
            			unitData.setMovement(Integer.valueOf(value));
            			break;
            		case "HP":
            			unitData.setHP(Double.valueOf(value));
            			break;
            		case "MP":
            			unitData.setMP(Double.valueOf(value));
            			break;
            		case "skin":
            			unitData.setCommonSkin(new ImageIcon(GameParameter.Resources_Dir+value));
            			unitData.setTransformSkin(new ImageIcon(GameParameter.Resources_Dir+value+"_trans"));
            			unitData.setBeHitSkin(new ImageIcon(GameParameter.Resources_Dir+value+"_hit"));
            			break;
            		case "size_w":
            			unitData.setSize_w(Integer.valueOf(value));
            			break;
            		case "size_h":
            			unitData.setSize_h(Integer.valueOf(value));
            			break;
            		case "hitable":
            			unitData.setHitable(Boolean.valueOf(value));
            			break;
            		case "blood":
            			unitData.setBlood(Boolean.valueOf(value));
            			break;
            		case "permeable":
            			unitData.setPermeable(Boolean.valueOf(value));
            			break;
            	}
            }
            if(unitData!=null)
            	UnitFactory.getUnitFacory().putUnitIntoUnitDataMap(unitData);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return null;
	}
	
}
