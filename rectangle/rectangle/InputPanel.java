//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Label;
import java.awt.Button;
import java.awt.Color;
import java.util.ArrayList;

/**
 *InputPanelクラス
 *<PRE>
 *色の指定と、deleteAllをできるパネルを配置します。
 *</PRE>
 *<OL>
 * <LI>InputPanel(RectangleEditor app,DrawCanvas draw,Command command)
 * <LI>public void itemStateChanged(ItemEvent e)
 * <LI>public String getColor()
 * <LI>public void actionPerformed(ActionEvent evt)
 *</OL>
 *@author BP16090 村松大輝
 */


class InputPanel extends Panel implements ActionListener,ItemListener{
    private RectangleEditor parent;
    private DrawCanvas draw;
    private Command command;
    private Checkbox chx1,chx2,chx3,chx4;
    private boolean b1,b2,b3,b4;
    private Button deleteAllButton;
    private String color;
    
    /**
     *初期化をするメソッド
     *@param app RectangleEditorのインスタンス
     *@param draw DrawCanvasのインスタンス
     *@param command Commandのインスタンス
     */
    
    InputPanel(RectangleEditor app,DrawCanvas draw,Command command){
        this.parent = app;
        this.draw = draw;
        this.command = command;
	this.color = "red";
        
        CheckboxGroup cbg = new CheckboxGroup();
        chx1 = new Checkbox("red",cbg,true);
        chx1.addItemListener(this);
        add(chx1);
        
        chx2 = new Checkbox("blue",cbg,false);
        chx2.addItemListener(this);
        add(chx2);
        
        chx3 = new Checkbox("yellow",cbg,false);
        chx3.addItemListener(this);
        add(chx3);
        
        chx4 = new Checkbox("gray",cbg,false);
        chx4.addItemListener(this);
        add(chx4);
        
        this.deleteAllButton = new Button("deleteAll");
        this.add(deleteAllButton);
        deleteAllButton.addActionListener(this);
        
    }
    
    
   /**
    *選択された色をセットするメソッド
    */
    public void itemStateChanged(ItemEvent e){
        if(e.getItemSelectable() == chx1) this.color = "red";
        if(e.getItemSelectable() == chx2) this.color = "blue";
        if(e.getItemSelectable() == chx3) this.color = "yellow";
        if(e.getItemSelectable() == chx4) this.color = "gray";
}

    /**
     *色をゲットするメソッド
     *@return 色
     */
    public String getColor(){
	return this.color;
    }
    
    /**
     *deleteAllボタンを押された場合の処理をするメソッド
     */
    public void actionPerformed(ActionEvent evt) {
        Button button = (Button)evt.getSource();
        if(button == this.deleteAllButton){
            this.command.beforeDeleteAll();
        }
		this.draw.repaint();
		this.parent.repaint();
    }
}
