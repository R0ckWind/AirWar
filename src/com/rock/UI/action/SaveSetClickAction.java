package com.rock.UI.action;

import java.awt.event.ActionEvent;

import com.rock.UI.GameFrame;
import com.rock.UI.component.BaseButton;
import com.rock.parameter.SystemParameter;
import com.rock.util.FileHandleUtil;
import com.rock.util.handle.SaveFileHandle;

public class SaveSetClickAction extends BaseClickAction{
	
	//返回并保存设置
	public SaveSetClickAction(BaseButton button) {
		super(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//刷新控制映射
		String content=SystemParameter.refreshCtr();
		//将结果保存到文件
		FileHandleUtil.handleFile("controller.pro", new SaveFileHandle(content));
		//退出当前layout
		gameFrame.getGamePanel().exitCurrentActivity();
	}
	
}
