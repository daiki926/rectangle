//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.Color;
/**
 *RectangleEditorクラス
 *<PRE>
 *アプレットを実装します
 *</PRE>
 *<OL>
 * <LI>public void init()
 * <LI>public InputPanel getInput()
 * <LI>public void paint(Graphics g)
 *</OL>
 *@author BP16090 村松大輝
 */


public class RectangleEditor extends Applet {
    private InputPanel input;
    private DrawCanvas draw;
    private Command command;
    private ArrayList<String> messageList;
    
    private int APPLETWIDTH;
    private int APPLETHEIGHT;
    private int BOARDWIDTH;
    private int BOARDHEIGHT;
    private final int SPACE = 100;
    private final int SIDE_SPACE = 50;
    private final int PANEL_SIZE = 70;
    private final int TEXT_SPACE = 25;
    
    
    /**
     *初期化をするメソッド
     *DrawCanvasクラス、InputPanelクラスと仕事を分担させる
     */
    
    public void init() {
        APPLETWIDTH = getSize().width;
        APPLETHEIGHT = getSize().height;

	BOARDWIDTH = APPLETWIDTH - SPACE;
	BOARDHEIGHT = APPLETHEIGHT - SPACE;
        command = new Command();
	draw = new DrawCanvas(this,command,BOARDWIDTH,BOARDHEIGHT);
        input = new InputPanel(this,draw,command);
        add(input,BorderLayout.NORTH);
       add(draw,BorderLayout.CENTER);
    }

    /**
     *InputPanelのインスタンスをゲットするメソッド
     *@return InputPanelのインスタンス
     */
    public InputPanel getInput(){
	return input;
    }
    
    /**
     *
     *エラーメッセージを描画するメソッド
     */
        public void paint(Graphics g){
            g.setFont(new Font("SansSerif",Font.BOLD,22));
            g.setColor(Color.pink);
            this.messageList = this.command.getMessageList();
            
            for (int i=0; i<this.messageList.size(); i++) {
                if(this.messageList.get(i) != null)
                   g.drawString(this.messageList.get(i),SIDE_SPACE,BOARDHEIGHT + PANEL_SIZE + i * TEXT_SPACE);
            }
            this.command.clearMessageList();
        }
}








