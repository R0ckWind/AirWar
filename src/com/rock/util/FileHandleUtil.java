package com.rock.util;

import com.rock.util.handle.BaseHandle;
import com.rock.util.handle.PropertiesHandle;;

//文件处理工具
public class FileHandleUtil {
	public static BaseHandle propertiesHandle=new PropertiesHandle();
	public static <T>T handleFile(String str,BaseHandle handle)
	{
		return handle.<T>getHandleResult(str);
	}
	public static <T>T handleFile(String str)
	{
		return propertiesHandle.<T>getHandleResult(str);
	}
}
