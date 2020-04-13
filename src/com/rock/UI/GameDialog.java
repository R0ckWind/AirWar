package com.rock.UI;


import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.rock.parameter.SystemParameter;
/**
 * 弹出窗口，目前只用于设置按键时弹出提示
 * 所以没有提供很好的扩展接口
 */
public class GameDialog extends JDialog{

	public GameDialog(String title,String content)
	{
		super(GameFrame.getGameFrame(),title);
		setSize(300, 200);
		JLabel jl=new JLabel(content);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setFont(new Font(SystemParameter.Font, Font.BOLD, 20));
		this.add(jl);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	public void setKeyListener(KeyListener listener)
	{
		this.addKeyListener(listener);
	}
	public void showDialog()
	{	
		this.setModal(true);//是否屏蔽其他窗口	
		this.setLocationRelativeTo(null);//居中显示
		this.setVisible(true);
		
	}

}
