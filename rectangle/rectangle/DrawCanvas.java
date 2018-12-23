//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.event.*;
import java.util.ArrayList;


/**
 *DrawCanvasクラス
 *<PRE>
 *マウスからとキーから情報を受け取り、ボード上に長方形を描くクラス
 *</PRE>
 *<OL>
 * <LI>DrawCanvas(RectangleEditor app,Command command,int BOARDWIDTH,int BOARDHEIGHT)
 * <LI>public void mousePressed(MouseEvent evt)
 * <LI>public void mouseReleased(MouseEvent evt)
 * <LI>public void mouseClicked(MouseEvent evt)
 * <LI>public void mouseDragged(MouseEvent evt)
 * <LI>public void keyPressed(KeyEvent evt)
 * <LI>public void keyReleased(KeyEvent evt)
 * <LI>public void paint(Graphics g) 
 *</OL>
 *@author BP16090 村松大輝
 */

class DrawCanvas extends Canvas implements MouseListener,MouseMotionListener,KeyListener{
    
    private int BOARDWIDTH;
    private int BOARDHEIGHT;
    private int pressPointX;
    private int pressPointY;
    private int beforePointX;
    private int beforePointY;
    private int numberR1;
    private int numberR2;
    private ArrayList<Rectangle> list;
    private Rectangle rectangle;
    private boolean keyPressed;
    private int rectangleNumber;
    private Board board;
    private InputPanel input;
    private Command command;
    private RectangleEditor parent;
    
    
    /**
     *初期化をするメソッド
     *@param app RectangleEditorのインスタンス
     *@param command Commandのインスタンス
     *@param BOARDWIDTH ボードの幅
     *@param BOARDHEIGHT ボードの高さ
     */

    DrawCanvas(RectangleEditor app,Command command,int BOARDWIDTH,int BOARDHEIGHT){
        this.parent = app;
        this.command = command;
	this.keyPressed = false;
	this.rectangleNumber = 0;
        this.numberR1 = 0;
        this.numberR2 = 0;
	
	this.BOARDWIDTH = BOARDWIDTH;
        this.BOARDHEIGHT = BOARDHEIGHT;
	
        setSize(this.BOARDWIDTH,this.BOARDHEIGHT);
	setBackground(Color.lightGray);
       
        this.board = new Board(this.BOARDWIDTH,this.BOARDHEIGHT);
        this.command.setBoard(board);

	addMouseListener(this);
	addMouseMotionListener(this);
	addKeyListener(this);

    }

    /**
     * コンポーネント上でマウスボタンが押されると呼び出されるメソッド
     */

 public void mousePressed(MouseEvent evt){
        this.pressPointX = evt.getX();
        this.pressPointY = evt.getY();
     this.beforePointX = this.pressPointX;
     this.beforePointY = this.pressPointY;

	this.board = this.command.getBoard();

     if (evt.isControlDown() == true || evt.isShiftDown() == true){
	    this.rectangleNumber = this.board.specifyRectangle(this.pressPointX,this.pressPointY);
         if(this.board.isExist(this.rectangleNumber)){
            this.rectangle = this.board.getRectangle(this.rectangleNumber);
         }
     }
     else if(evt.isMetaDown() == true){
         this.rectangleNumber = this.board.specifyRectangle(this.pressPointX,this.pressPointY);
         this.command.beforeDelete(this.rectangleNumber);
         repaint();
         this.parent.repaint();
     }
 }

    
    /**
     * コンポーネント上でマウスボタンが離されると呼び出されるメソッド
     */
    
    public void mouseReleased(MouseEvent evt){
        int releasePointX = evt.getX();
        int releasePointY = evt.getY();
        
        if(keyPressed == false){
        int width = Math.abs(releasePointX - this.pressPointX);
        int height = Math.abs(releasePointY - this.pressPointY);            
	this.command.beforeCreate(Math.min(this.pressPointX,releasePointX),Math.min(this.pressPointY,releasePointY),width,height,this.parent.getInput().getColor());
            repaint();
            this.parent.repaint();
        }
    }
    
    public void mouseEntered(MouseEvent evt){
    }
    public void mouseExited(MouseEvent evt){
    }
    
    /**
     *コンポーネント上でマウスボタンをクリック (押してから離す) したときに呼び出されるメソッド
     */
    
    public void mouseClicked(MouseEvent evt){
        this.board = this.command.getBoard();
        
        int clickPointX = evt.getX();
        int clickPointY = evt.getY();
    
        if(evt.isAltDown() == true){
            if(this.numberR1 == 0){
            this.numberR1 = this.board.specifyRectangle(clickPointX,clickPointY);
            }
            else if(this.numberR2 == 0){
                this.numberR2 = this.board.specifyRectangle(clickPointX,clickPointY);
            }
            
            if(this.numberR1 != 0 && this.numberR2 != 0){
                this.command.beforeIntersect(this.numberR1,this.numberR2);
                repaint();
                this.parent.repaint();
                this.numberR1 = 0;
                this.numberR2 = 0;
            }
        }
    }
   
   
   /**
    *コンポーネント上でマウスボタンがドラッグされると呼び出されるメソッド
    */
    
    public void mouseDragged(MouseEvent evt){
        double pointX = evt.getX();
        double pointY = evt.getY();
        
	if (evt.isControlDown() == true){
	int x0 = (int)pointX - this.beforePointX;
	int y0 = (int)pointY - this.beforePointY;
	this.command.beforeMove(this.rectangleNumber,x0,y0);
        this.beforePointX = (int)pointX;
        this.beforePointY = (int)pointY;
	repaint();
        this.parent.repaint();
	}

    else if (evt.isShiftDown() == true){
        if(this.board.isExist(this.rectangleNumber)){
        double mx = (pointX - (double)this.rectangle.getX()) / this.rectangle.getWidth();
        double my = (pointY - (double)this.rectangle.getY()) / this.rectangle.getHeight();
        this.command.beforeScale(this.rectangleNumber,mx,my);
        repaint();
        this.parent.repaint();
    }
    }
    }

  public void mouseMoved(MouseEvent evt){
  }
    
    /**
     *キーが押されたときに呼び出されるメソッド
     */
  public void keyPressed(KeyEvent evt){
      this.keyPressed = true;
  }

    /**
     *キーが離されたときに呼び出さるメソッド
     */
  public void keyReleased(KeyEvent evt){
      this.keyPressed = false;
  }

  public void keyTyped(KeyEvent evt){
  }

   

/**
 *ボード上の長方形を描画するメソッド
 */
    public void paint(Graphics g){
        this.board = this.command.getBoard();
        ArrayList<Rectangle> list = this.board.getList();
        for (int i=0; i<list.size(); i++) {
            int width = list.get(i).getWidth();
            int height = list.get(i).getHeight();
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            String color = list.get(i).getColor();

            Color rectangleColor = null;
	    
            if(color.equals("red"))rectangleColor = Color.red;
            else if(color.equals("blue"))rectangleColor = Color.blue;
            else if(color.equals("yellow"))rectangleColor = Color.yellow;
            else if(color.equals("gray"))rectangleColor = Color.gray;
            else if(color.equals("green"))rectangleColor = Color.green;
            else if(color.equals("orange"))rectangleColor = Color.orange;
            else if(color.equals("magenta"))rectangleColor = Color.magenta;
            else if(color.equals("cyan"))rectangleColor = Color.cyan;
            
            g.setColor(rectangleColor);
            
            g.fillRect(x,y,width,height);
        }
    }
  
}


