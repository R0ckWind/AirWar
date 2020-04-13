package com.rock.util.handle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.rock.parameter.GameParameter;

//保存文件
public class SaveFileHandle extends BaseHandle{

	private String content;
	public SaveFileHandle(String content) {
		super();
		this.content=content;
	}
	@Override
	public <T> T getHandleResult(String str) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(GameParameter.Resources_Dir+str));
	    	out.write(content);
	    	out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
