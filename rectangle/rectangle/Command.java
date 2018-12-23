//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.util.ArrayList;


/**
 *Commandクラス
 *<PRE>
 *操作を選ばせ、入力をさせて、Boardクラスに渡します。
 *</PRE>
 *<OL>
 * <LI>public void setBoard(Board board)
 * <LI>public Board getBoard()
 * <LI>public ArrayList<String> getMessageList()
 * <LI>public void clearMessageList()
 * <LI>
 
 
 
 * <LI>public void beforeCreate(int x,int y,int width,int height,String color)
 * <LI>public void beforeMove(int moveNumber,int x0,int y0)
 * <LI>public void beforeScale(int scaleNumber,double mx,double my)
 * <LI>public void beforeDelete(int deleteNumber)
 * <LI>public void beforeDeleteAll()
 * <LI>public void beforeIntersect(int numberR1,int numberR2)
 *</OL>
 *@author BP16090 村松大輝
 */


public class Command {
    private Input input = new Input();
    private Board board;
    private ArrayList<String> messageList = new ArrayList<String>();
    private final int UPPER_LIMIT_RECTANGLE = 10;
    private final int LOWER_LIMIT_RECTANGLE = 0;
    private final int INTERSECT_NEEDS_RECTANGLE = 2;
    
    
    
    /**
     *boardをセットするメソッド
     *@param board ボード
     */
    public void setBoard(Board board){
    this.board = board;
}
    /**
     *boardをゲットするメソッド
     *@return ボード
     */
    public Board getBoard(){
        return this.board;
    }
    
    /**
     *エラーメッセージリストをゲットするメソット
     *@return エラーメッセージリスト
     */
    public ArrayList<String> getMessageList(){
        return this.messageList;
    }
    
/**
 *メッセージリストを空にするメソッド
 */
    public void clearMessageList(){
	this.messageList.clear();
    }
    
    
  
    /**
     *条件を満たした場合のみcreateメソッドに引数を渡す
     *@param x x座標
     *@param y y座標
     *@param width 幅
     *@param height 高さ
     *@param color 色
     */
   

    public void beforeCreate(int x,int y,int width,int height,String color){
        if(this.board.boardCount() < UPPER_LIMIT_RECTANGLE){
            try{
                this.board.create(x,y,width,height,color);
            }
            catch(IllegalArgumentException e){
                this.messageList.add(this.board.getMessage());
                //System.out.println(e);
            }
        }
        else{
            this.messageList.add(">>ボード上の長方形の数が上限を超えています<<");
            
        }
    }
    
    /**
     *条件を満たした場合のみmoveメソッドに引数を渡す
     *@param moveNumber 対象の長方形の番号
     *@param x0 移動するx方向への距離
     *@param y0 移動するy方向への距離
     */
    public void beforeMove(int moveNumber,int x0,int y0){
          if(this.board.boardCount() > LOWER_LIMIT_RECTANGLE){
                  try{
                  this.board.move(moveNumber,x0,y0);
                  }
                  catch(IllegalArgumentException e){
                      this.messageList.add(this.board.getMessage());
                      //System.out.println(e);
                  }
                  catch(IndexOutOfBoundsException e){
                      this.messageList.add(this.board.getMessage());
                      //System.out.println(e);
                  }
          }
              else{
                  this.messageList.add(">>ボード上に長方形が存在しません<<");
              }
    }
    
  /**
   *条件を満たした場合のみscaleメソッドに引数を渡す
   *@param scaleNumber 対象の長方形の番号
   *@param mx 拡大・縮小する幅の倍率
   *@param my 拡大・縮小する高さの倍率
   */
    public void beforeScale(int scaleNumber,double mx,double my){
        if(this.board.boardCount() > LOWER_LIMIT_RECTANGLE){
            try{
                this.board.scale(scaleNumber,mx,my);
            }
            catch(IllegalArgumentException e){
                this.messageList.add(this.board.getMessage());
                //System.out.println(e);
            }
            catch(IndexOutOfBoundsException e){
                this.messageList.add(this.board.getMessage());
                //System.out.println(e);
            }
        }
        else{
            this.messageList.add(">>ボード上に長方形が存在しません<<");
            
        }
    }
    
    /**
     *条件を満たした場合のみdeleteメソッドに引数を渡す
     *@param deleteNumber 対象の長方形の番号
     */

    public void beforeDelete(int deleteNumber){
        if(this.board.boardCount() > LOWER_LIMIT_RECTANGLE){
            
            try{
                this.board.delete(deleteNumber);
            }
            catch(IndexOutOfBoundsException e){
                this.messageList.add(this.board.getMessage());
                //System.out.println(e);
            }
        }
        else{
            this.messageList.add(">>ボード上に長方形が存在しません<<");
            
        }
    }

    
    /**
     *条件を満たした場合のみdeleteAllメソッドに引数を渡すメソッド
     */
    public void beforeDeleteAll(){
	if(this.board.boardCount() > LOWER_LIMIT_RECTANGLE){
	    this.board.deleteAll();
	}
	else {
        this.messageList.add(">>ボード上に長方形が存在しません<<");
        
    }
    }
   
    /**
     *条件を満たした場合のみintersectメソッドに引数を渡す
     *@param numberR1 一つ目の長方形番号
     *@param numberR2 二つ目の長方形番号
     */
    public void beforeIntersect(int numberR1,int numberR2){
        if(this.board.boardCount() < INTERSECT_NEEDS_RECTANGLE ){
            this.messageList.add(">>ボード上に長方形が２つ以上存在しません<<");
            
        }
        else if(this.board.boardCount() >= UPPER_LIMIT_RECTANGLE ){
            this.messageList.add(">>ボード上の長方形の数が上限を超えています<<");
            
        }
        else if(!this.board.isExist(numberR1) || !this.board.isExist(numberR2)){
            this.messageList.add(">>存在しない長方形を選択しています<<");
        }
                //else if(!board.isExist(numberR2))System.out.println("二つ目の長方形が存在しません");
        else if(numberR1 == numberR2){
            this.messageList.add(">>同じ長方形を選択しています<<");
            
        }
                else{
            
            try{
                this.board.intersect(numberR1,numberR2);
            }
            catch(IllegalArgumentException e){
                this.messageList.add(this.board.getMessage());
                //System.out.println(e);
            }
                }
        }
    
}




