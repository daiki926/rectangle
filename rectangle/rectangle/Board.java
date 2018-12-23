//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.util.ArrayList;

/**
 *Boardクラス
 *<PRE>
 *各操作と、Boardへの配置をします。
 *</PRE>
 *<OL>
 * <LI>Board(int boardWidth,int boardHeight)
 * <LI>public String getMessage()
 * <LI>public void create(int x,int y,int width,int height,String color) throws IllegalArgumentException
 * <LI>public void move(int moveNumber,int x0,int y0) throws IllegalArgumentException,IndexOutOfBoundsException
 * <LI>public void scale(int scaleNumber,double mx,double my)
 * <LI>public void delete(int deleteNumber) throws IndexOutOfBoundsException
 * <LI>public void deleteAll()
 * <LI>public void intersect(int numberR1,int numberR2) throws IllegalArgumentException
 * <LI>public void displayBoard()
 * <LI>public int boardCount()
 * <LI>public boolean isExist(int number)
 * <LI>public boolean hasRectangle(int x,int y,int width,int height)
 * <LI>public boolean isWithinBoard(int x,int y,int width,int height)
 * <LI>public boolean isWithinBoard(int x,int y,int width,int height)
 * <LI>public ArrayList<Rectangle> getList()
 * <LI>public Rectangle getRectangle(int rectangleNumber)
 * <LI>public int specifyRectangle(int x,int y)
 *</OL>
 *@author BP16090 村松大輝
 */


public class Board {
    private final int LOWER_LIMIT_BOARD_X = 0;
    private final int LOWER_LIMIT_BOARD_Y = 0;
    private final int LOWER_LIMIT_WIDTH = 0;
    private final int LOWER_LIMIT_HEIGHT = 0;
    
    private int boardWidth = 0;
    private int boardHeight = 0;
    private String color;
    private int rectangleNumber = 0;
    private String message;
    private ArrayList<Rectangle> list = new ArrayList<Rectangle>();
    private ArrayList<Integer> idList = new ArrayList<Integer>();

    private ColoredRectangle coloredRectangle = new ColoredRectangle();

    
    /**
     *ボードの幅と高さをセットするコンストラクタ
     */
     Board(int boardWidth,int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    /**
     *エラーメッセージをゲットするメソッド
     *@return エラーメッセージ
     */
    public String getMessage(){
        return this.message;
    }
    
    /**
     *長方形を作成するメソッド
     *@param x x座標
     *@param y y座標
     *@param width 幅
     *@param height 高さ
     *@param color 色
     *@throws IllegalArgumentException 不正な引数、または不適切な引数をメソッドに渡しました
     */
    public void create(int x,int y,int width,int height,String color) throws IllegalArgumentException
    {
        
        if(isRectangle(width,height)==false){
            this.message = ">>長方形が作成できません<<";
            throw new IllegalArgumentException (">>長方形が作成できません<<");
        }
        
        else if(hasRectangle(x,y,width,height)){
            this.message = ">>重複する長方形が存在します<<";
            throw new IllegalArgumentException (">>重複する長方形が存在します<<");
        }
                
                else if(isWithinBoard(x,y,width,height)==false){
                    this.message = ">>長方形がボード内に作成できません<<";
                    throw new IllegalArgumentException (">>長方形がボード内に作成できません<<");
                }
                else{
        Rectangle rectangle = new Rectangle(x,y,width,height,color);
        list.add(rectangle);
        rectangleNumber++;
        idList.add(rectangleNumber);
                }
    }
    
    
    /**
     *長方形を移動するメソッド
     *@param moveNumber 対象の長方形
     *@param x0 移動するx方向への距離
     *@param y0 移動するy方向への距離
     *@throws IllegalArgumentException 不正な引数、または不適切な引数をメソッドに渡しました
     *@throws IndexOutOfBoundsException ボード上に指定された番号の長方形が存在しません
     */
    
    public void move(int moveNumber,int x0,int y0) throws IllegalArgumentException,IndexOutOfBoundsException {
	Rectangle rectangle = list.get(idList.indexOf(moveNumber));
        
                int newX = rectangle.getX() + x0;
                int newY =rectangle.getY() + y0;
        
        if(isExist(moveNumber) == false){
            this.message = ">>ボード上に指定された長方形が存在しません<<";
            throw new IndexOutOfBoundsException (">>ボード上に指定された長方形が存在しません<<");
        }
        
        else if(hasRectangle(newX,newY,rectangle.getWidth(),rectangle.getHeight()) && x0 != 0 && y0 != 0){
            this.message = ">>重複する長方形が存在します<<";
            throw new IllegalArgumentException (">>重複する長方形が存在します<<");
        }
        
        else if(isWithinBoard(newX,newY,rectangle.getWidth(),rectangle.getHeight())==false){
            this.message = ">>長方形がボード内に作成できません<<";
            throw new IllegalArgumentException (">>長方形がボード内に作成できません<<");
        }
        
        else{
                rectangle.setX(newX);
                rectangle.setY(newY);
            list.set(idList.indexOf(moveNumber),rectangle);
        }
    }
    
    /**
     *長方形を拡大・縮小するメソッド
     *@param scaleNumber 対象の長方形番号
     *@param mx 拡大・縮小する幅の倍率
     *@param my 拡大・縮小する高さの倍率
     *@throws IndexOutOfBoundsException ボード上に指定された番号の長方形が存在しません
     *@throws IllegalArgumentException 不正な引数、または不適切な引数をメソッドに渡しました
     */
    
    public void scale(int scaleNumber,double mx,double my) throws IndexOutOfBoundsException,IllegalArgumentException{
        
	Rectangle rectangle = list.get(idList.indexOf(scaleNumber));
                
                int newWidth = (int)Math.round((double)rectangle.getWidth() * mx);
                int newHeight = (int)Math.round((double)rectangle.getHeight() * my);
        
        if(isExist(scaleNumber) == false){
            this.message = ">>ボード上に指定された長方形が存在しません<<";
            throw new IndexOutOfBoundsException (">>ボード上に指定された長方形が存在しません<<");
        }
        
        else if(isRectangle(newWidth,newHeight)==false){
            this.message = ">>長方形が作成できません<<";
            throw new IllegalArgumentException (">>長方形が作成できません<<");
        }
        
        else if(hasRectangle(rectangle.getX(),rectangle.getY(),newWidth,newHeight) && mx != 1 && my != 1){
            this.message = ">>重複する長方形が存在します<<";
            throw new IllegalArgumentException (">>重複する長方形が存在します<<");
        }
        
        else if(isWithinBoard(rectangle.getX(),rectangle.getY(),newWidth,newHeight)==false){
            this.message = ">>長方形がボード内に作成できません<<";
            throw new IllegalArgumentException (">>長方形がボード内に作成できません<<");
        }
        
        else{
                rectangle.setWidth(newWidth);
                rectangle.setHeight(newHeight);
                list.set(idList.indexOf(scaleNumber),rectangle);

        }
    }

    
  
    /**
     *指定された長方形を削除するメソッド
     *@param deleteNumber 対象の長方形番号
     *@throws IndexOutOfBoundsException ボード上に指定された番号の長方形が存在しません
     */
    public void delete(int deleteNumber) throws IndexOutOfBoundsException {

        if(isExist(deleteNumber) == false){
            this.message = ">>ボード上に指定された長方形が存在しません<<";
            throw new IndexOutOfBoundsException (">>ボード上に指定された長方形が存在しません<<");
        }
        else{
	list.remove(idList.indexOf(deleteNumber));
	idList.remove(idList.indexOf(deleteNumber));
        }
    }

    /**
     *ボード上の長方形を全て削除するメソッド
     */
    public void deleteAll(){
	      list.clear();
	      idList.clear();
	      rectangleNumber = 0;
      }
    
    
    /**
     *二つの長方形の重なり部分を抽出し、新たな長方形として配置するメソッド
     *@param numberR1 一つ目の長方形番号
     *@param numberR2 二つ目の長方形番号
     *@throws IllegalArgumentException 不正な引数、または不適切な引数をメソッドに渡しました
     */
    public void intersect(int numberR1,int numberR2) throws IllegalArgumentException {
	int widthR1 = 0;
	int heightR1 = 0;
	int xR1 = 0;
	int yR1 = 0;
	String colorR1 = null;
	int widthR2 = 0;
	int heightR2 = 0;
	int xR2 = 0;
	int yR2 = 0;
	String colorR2 = null;

	Rectangle r1 = list.get(idList.indexOf(numberR1));
	Rectangle r2 = list.get(idList.indexOf(numberR2));

		widthR1 = r1.getWidth();
		heightR1 = r1.getHeight();
		xR1 = r1.getX() ;
		yR1 = r1.getY() ;
        colorR1 = r1.getColor();

		widthR2 = r2.getWidth();
		heightR2 = r2.getHeight() ;
		xR2 = r2.getX() ;
		yR2 = r2.getY();
		colorR2 = r2.getColor();
	

    int sx = Math.max(xR1, xR2);
    int sy = Math.max(yR1, yR2);
    int ex = Math.min(xR1 + widthR1, xR2 + widthR2);
    int ey = Math.min(yR1 + heightR1, yR2 + heightR2);
    
    int newWidth = ex - sx;
    int newHeight = ey - sy;
    if (newWidth > 0 && newHeight > 0) {
	String newColor = coloredRectangle.margeColor(colorR1,colorR2);
        if(hasRectangle(sx,sy,newWidth,newHeight)){
            this.message = ">>重複する長方形が存在します<<";
            throw new IllegalArgumentException (">>重複する長方形が存在します<<");
        }
        
	create(sx,sy,newWidth,newHeight,newColor);
    }
    else{
        this.message = ">>長方形が重なっていません<<";
        throw new IllegalArgumentException (">>長方形が重なっていません<<");
    }
    }



    
   
    
    /**
     *ボード上に配置されている長方形の数を返すメソッド
    *@return 長方形の数
     */
    public int boardCount(){
        return list.size();
    }
    
    /**
     *引数として渡された長方形番号がボード上に存在するかどうかを返すメソッド
     *@param number 対象の長方形番号
     *@return 長方形番号の存在の有無
     */
    public boolean isExist(int number){
        return idList.contains(number);
    }
   
    /**
     *引数として渡された長方形と同じ長方形がボード上に存在するかどうかを返すメソッド
     *@param x x座標
     *@param y y座標
     *@param width 幅
     *@param height 高さ
     *@return 重複する長方形の有無
     *
     */
    public boolean hasRectangle(int x,int y,int width,int height){
	for (int i=0; i<this.list.size(); i++) {
	    if(
	    width == list.get(i).getWidth() &&
	    height == list.get(i).getHeight() &&
	    x == list.get(i).getX() &&
	    y == list.get(i).getY()
	       )return true;
	}
	    return false;
	}

    /**
     *長方形がボードからはみ出さないか判定するメソッド
     *@param x x座標
     *@param y y座標
     *@param width 幅
     *@param height 高さ
     *@return 長方形がはみ出しの有無
     *
     */
    public boolean isWithinBoard(int x,int y,int width,int height){
	if(x<LOWER_LIMIT_BOARD_X || y<LOWER_LIMIT_BOARD_Y || x+width > this.boardWidth || y+height > this.boardHeight)return false;
	else return true;
}
                
                /**
                 *長方形を作成できるか判定するメソッド
                 *@param width 幅
                 *@param height 高さ
                 *@return 長方形であるかないか
                 */
                public boolean isRectangle(int width,int height){
            if( width <= LOWER_LIMIT_WIDTH || height <=LOWER_LIMIT_HEIGHT )return false;
            else return true;
        }
    
    /**
     *長方形のリストをゲットするメソッド
     *@return 長方形のリスト
     */
    public ArrayList<Rectangle> getList(){
        return list;
    }
    
    /**
     *指定された番号の長方形をゲットするメソッド
     *@param rectangleNumber 長方形の番号
     *@return 指定された番号の長方形
     */
    public Rectangle getRectangle(int rectangleNumber){
        return list.get(idList.indexOf(rectangleNumber));
    }
    
    /**
     *引数として渡された座標に該当する長方形番号を返すメソッド
     *該当する長方形がなかった場合は-1を返す
     *@param x x座標
     *@param y y座標
     *@return 該当する長方形番号
     */
    public int specifyRectangle(int x,int y){
	int rectangleNumber = -1;             //長方形が存在しない場合に-1を返す
	for (int i=0; i<this.list.size(); i++) {
	    if(list.get(i).getX() <= x && x <= list.get(i).getX() + list.get(i).getWidth() && list.get(i).getY() <= y && y <= list.get(i).getY() + list.get(i).getHeight())rectangleNumber = this.idList.get(i);
	}
	return rectangleNumber;
        
    }
    
}
