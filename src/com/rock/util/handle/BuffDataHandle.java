package com.rock.util.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.rock.parameter.GameParameter;
import com.rock.unit.buff.BuffState;
import com.rock.unit.factory.BuffFactory;

//用于解析buff数据
public class BuffDataHandle extends BaseHandle{

	@Override
	public <T> T getHandleResult(String str) {
		File file = new File(GameParameter.Resources_Dir+str);
        BufferedReader reader = null;
        try {
            //以行为单位读取文件内容，一次读一整行
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            BuffState buffState=null;
            while ((tempString = reader.readLine()) != null) {
                //跳过注释和空行
            	if(tempString.startsWith("#")||tempString.equals(""))
            		continue;
            	if(tempString.startsWith("[buff]"))
            	{
            		if(buffState==null)
            			buffState=new BuffState();
            		else
            		{
            			BuffFactory.getBuffFactory().putBuffStateIntoBuffMap(buffState);
            			buffState=new BuffState();
            		}
            		continue;
            	}
            	String []arr=tempString.split("=");
            	String key=arr[0];
            	String value=arr[1];
            	switch(key)
            	{
            		case "name":
            			buffState.setName(value);
            			break;
            		case "duration":
            			buffState.setDuration(Integer.valueOf(value));
            			break;
            		case "damege":
            			buffState.setDamageGain(Double.valueOf(value));
            			break;
            		case "defence":
            			buffState.setDefenceGain(Double.valueOf(value));
            			break;
            		case "movement":
            			buffState.setMovementGain(Integer.valueOf(value));
            			break;
            		case "hp_recover":
            			buffState.setHP_Recovery_PS(Double.valueOf(value));
            			break;
            		case "mp_recover":
            			buffState.setMP_Recovery_PS(Double.valueOf(value));
            			break;
            		case "hp_heal":
            			buffState.setHP_CurGain(Double.valueOf(value));;
            			break;
            		case "mp_heal":
            			buffState.setMP_CurGain(Double.valueOf(value));;
            			break;
            		case "hp_extra":
            			buffState.setHP_MaxGain(Double.valueOf(value));
            			break;
            		case "mp_extra":
            			buffState.setMP_MaxGain(Double.valueOf(value));
            			break;
            		case "permanent":
            			buffState.setPermanent(Boolean.valueOf(value));
            			break;
            	}
            }
            if(buffState!=null)
            	BuffFactory.getBuffFactory().putBuffStateIntoBuffMap(buffState);
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
