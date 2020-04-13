package com.rock.util.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.rock.parameter.GameParameter;
import com.rock.stage.GameStage;
import com.rock.stage.GameWave;

//解析关卡数据
public class StageHandle extends BaseHandle{

	@Override
	public <T> T getHandleResult(String str) {
		File file = new File(GameParameter.Resources_Dir+"/stage/"+str);
        BufferedReader reader = null;
        try {
            //以行为单位读取文件内容，一次读一整行
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            GameStage gameStage=new GameStage();
            GameWave gameWave=null;
            while ((tempString = reader.readLine()) != null) {
                //跳过注释和空行
            	if(tempString.startsWith("#")||tempString.equals(""))
            		continue;
            	if(tempString.startsWith("[wave]")||tempString.startsWith("[boss]")||tempString.startsWith("[gift]"))
            	{
            		if(gameWave==null)
            		{
            			gameWave=new GameWave();
            		}
            		else 
            		{
            			gameStage.getWaveQueue().add(gameWave);
            			gameWave=new GameWave();
            		}
            		continue;
            	}
            	String []arr=tempString.split("=");
            	String key=arr[0];
            	String value=arr[1];
            	switch(key)
            	{
            		case "name":
            			gameStage.setName(value);
            			break;
            		case "background":
            			gameStage.setBackground(new ImageIcon(GameParameter.Resources_Dir+value));
            			break;
            		case "reward":
            			gameStage.setReward(value);
            			break;
            		case "time":
            			gameWave.setTime(Integer.valueOf(value));
            			break;
            		case "isClear":
            			gameWave.setClear(Boolean.valueOf(value));
            			break;
            		case "units":
            			String []parts=value.split(":");
            			for(int i=0;i<parts.length;i++)
            			{
            				String []un=parts[i].split("\\*");
            				String unit=un[0];
            				int num=Integer.valueOf(un[1]);
            				gameWave.putUnitIntoList(gameWave.new UnitType(unit,num));
            			}
            			break;
            	}
            }
            if(gameWave!=null)
            	gameStage.getWaveQueue().add(gameWave);
            return (T)gameStage;
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
