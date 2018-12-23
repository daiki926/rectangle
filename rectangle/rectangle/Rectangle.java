//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

/**
 *Rectangleクラス
 *<PRE>
 *長方形のクラス
 *</PRE>
 *<OL>
 * <LI>Rectangle(int x, int y, int width, int height)
 * <LI>public int getX()
 * <LI>public int getY()
 * <LI>public int getWidth()
 * <LI>public int getHeight()
 * <LI>public String getColor()
 * <LI>public void setX(int x)
 * <LI>public void setY(int y)
 * <LI>public void setWidth(int width)
 * <LI>public void setHeight(int height)
 * <LI>public void setColor(String color)
 *</OL>
 *@author BP16090 村松大輝
 */



public class Rectangle{
    private int x = 0;
    private int y = 0;
    private int height = 0;
    private int width = 0;
    private String color;


    /**
     *長方形を作成するコンストラクタ
     *@param x x座標
     *@param y y座標
     *@param width 幅
     *@param height 高さ
     */    
    Rectangle(int x, int y, int width, int height, String color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
	this.color = color;
    }
    
    /**
     *x座標を返すgetterメソッド
     *@return x座標
     */
    public int getX(){
	return this.x;
    }
    
    /**
     *y座標を返すgetterメソッド
     *@return y座標
     */
    public int getY(){
	return this.y;
    }
    
    /**
     *幅を返すgetterメソッド
     *@return 幅
     */
    public int getWidth(){
	return this.width;
    }
    
    /**
     *高さを返すgetterメソッド
     *@return 高さ
     */
    public int getHeight(){
	return this.height;
    }

 /**
     *色を返すgetterメソッド
     *@return 色
     */
    public String getColor(){
	return this.color;
    }
    
    /**
    *x座標をセットするsetterメソッド
    *@param x x座標
    */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     *y座標をセットするsetterメソッド
     *@param y y座標
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     *幅をセットするsetterメソッド
     *@param width 幅
     */
    
    public void setWidth(int width){
        this.width = width;
    }
    
    
    /**
     *高さをセットするsetterメソッド
     *@param height 高さ
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    
    /**
     *色をセットするsetterメソッド
     *@param color 色
     */
    public void setColor(String color){
        this.color = color;
    }
}
