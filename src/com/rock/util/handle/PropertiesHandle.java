package com.rock.util.handle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.rock.parameter.GameParameter;

//返回pro
public class PropertiesHandle extends BaseHandle{
	protected Properties getProperties(String url)
	{
		Properties ps=new Properties();
        
		try{
			FileInputStream in=new FileInputStream(GameParameter.Resources_Dir+url);
			BufferedReader bf=new BufferedReader(new InputStreamReader(in,"utf-8"));
			ps.load(bf);
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	public <T> T getHandleResult(String str) {
		return (T) getProperties(str);
	}
}
