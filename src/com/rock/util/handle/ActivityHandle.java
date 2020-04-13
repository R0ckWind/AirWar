package com.rock.util.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;

import com.rock.UI.GameActivity;
import com.rock.UI.action.BaseClickAction;
import com.rock.UI.component.BaseButton;
import com.rock.UI.component.BaseLabel;
import com.rock.UI.mouse.BaseMouse;
import com.rock.parameter.GameParameter;

//用于解析活动布局
public class ActivityHandle extends PropertiesHandle{
	
protected final  String ActionDir="com.rock.UI.action.";
protected final  String MouseDir="com.rock.UI.mouse.";
	
	@Override
	public <T> T getHandleResult(String str) {
		File file = new File(GameParameter.Resources_Dir+"layout/"+str);
        BufferedReader reader = null;
        try {
            //以行为单位读取文件内容，一次读一整行
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            GameActivity activity=new GameActivity();
            BaseButton button=null;
            BaseLabel label=null;
            while ((tempString = reader.readLine()) != null) {
                //跳过注释和空行
            	if(tempString.startsWith("#")||tempString.equals(""))
            		continue;
            	if(tempString.startsWith("[button]")||tempString.startsWith("[label]"))
            	{
            		if(button!=null)
            		{
            			activity.addComponent(button);
            			button=null;
            		}
            		if(label!=null)
            		{
            			activity.addComponent(label);
            			label=null;
            		} 		
            	}
            	if(tempString.startsWith("[button]"))
            	{
            		if(button==null)
            		{
            			button=new BaseButton();
            			label=null;
            		}
            		continue;
            	}
            	if(tempString.startsWith("[label]"))
            	{
            		if(label==null)
            		{
            			label=new BaseLabel();
            			button=null;
            		}
            		continue;
            	}
            	String []arr=tempString.split("=");
            	String key=arr[0];
            	String value=arr[1];
            	if(button!=null)
            	{
                	switch(key)
                	{
                		case "name":
                			button.setbName(value);
                			break;
                		case "text":
                			button.setbText(value);
                			break;
                		case "size":
                		{
                			String[] vals=value.split(":");
                			button.setSizeW(Integer.valueOf(vals[0]));
                			button.setSizeH(Integer.valueOf(vals[1]));
                			break;
                		}
                		case "point":
                		{
                			String[] vals=value.split(":");
                			button.setButtonX(Integer.valueOf(vals[0]));
                			button.setButtonY(Integer.valueOf(vals[1]));
                			break;
                		}
                		case "skin":
                			button.setButtonImg(new ImageIcon(GameParameter.Resources_Dir+value));
                			break;
                		case "impl":
                			button.setAction(getClickAction(button,value));
                			break;	
                	}
            	}
            	if(label!=null)
            	{
            		switch(key)
                	{
                		case "name":
                			label.setlName(value);
                			break;
                		case "text":
                			label.setlText(value);
                			break;
                		case "size":
                		{
                			String[] vals=value.split(":");
                			label.setSizeW(Integer.valueOf(vals[0]));
                			label.setSizeH(Integer.valueOf(vals[1]));
                			break;
                		}
                		case "point":
                		{
                			String[] vals=value.split(":");
                			label.setLabelX(Integer.valueOf(vals[0]));
                			label.setLabelY(Integer.valueOf(vals[1]));
                			break;
                		}
                		case "skin":
                			label.setLabelImg(new ImageIcon(GameParameter.Resources_Dir+value));
                			break;
                		case "font":
                			label.setFontSize(Integer.valueOf(value));
                			break;
                		case "impl":
                			label.setMouse(getMouse(label,value));
                			break;		
                	}
            	}
            	else 
            	{
                	switch(key)
                	{
                		case "sizeW":
                			activity.setSizeW(Integer.valueOf(value));
                			break;
                		case "sizeH":
                			activity.setSizeH(Integer.valueOf(value));
                			break;
                		case "background":
                			activity.setBackground(new ImageIcon(GameParameter.Resources_Dir+value));
                			break;
                	}
            	}

            }
            if(button!=null)
            	activity.addComponent(button);
            if(label!=null)
            	activity.addComponent(label);
            reader.close();
            return (T)activity;
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
	//根据反射创建点击事件对象
	private  BaseClickAction getClickAction(BaseButton button,String str)
	{
		Class<BaseClickAction> actionClass=null;
    	BaseClickAction action=null;
		try {
			actionClass=(Class<BaseClickAction>) Class.forName(ActionDir+str);
			Constructor<BaseClickAction> constructor=actionClass
					.getConstructor(BaseButton.class);
			action = constructor.newInstance(button);
			
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
		return action;
	}
	private  BaseMouse getMouse(BaseLabel label,String str)
	{
		Class<BaseMouse> mouseClass=null;
		BaseMouse mouse=null;
		try {
			mouseClass=(Class<BaseMouse>) Class.forName(MouseDir+str);
			Constructor<BaseMouse> constructor=mouseClass
					.getConstructor(BaseLabel.class);
			mouse = constructor.newInstance(label);

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
		return mouse;
	}
}
