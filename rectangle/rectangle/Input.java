//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

import java.io.*;

/**
 * キーボードからの入力を行うためのクラス
 */
public class Input{
    private String prompt;
    private final String ERROR_INPUT_DOUBLE = "入力値は実数ではありません。";
    private final String ERROR_INPUT = "エラー：入力に誤りがあります。";
    private void setPrompt(String prompt){
	this.prompt = prompt;
    }
    private String input() throws IOException{
	String line;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	line = reader.readLine();
	return line;
    }
    /**
     * 実数の入力
     * 引数で与えられたメッセージを出力後、キーボードから入力された文字列を実数として返
     すメソッドである。入力された文字列が実数として解釈できない場合には、再入力を促す。
     このメソッドを利用して、キーボードから実数を入力する。
     * @param prompt :入力を促すメッセージ
     * @return Double入力
     * @exception Exception : 入力値が実数として解釈できない場合の例外を処理する
     */
    public double inputDouble(String prompt){
	this.setPrompt(prompt);
	System.out.println(this.prompt);
	try{
	    String value = this.input();
	    double n = Double.parseDouble(value);
	    return n;
	} catch (Exception s){
	    System.out.println(ERROR_INPUT_DOUBLE);
	    return this.inputDouble(prompt);
	}
    }
    /**
     * 文字列の入力
     * 引数で与えられたメッセージを出力後、キーボードから入力された文字列を返すメソッド
     である。入力における例外は発生した場合には、再入力を促す。
     * @param prompt :入力を促すメッセージ
     * @return 文字入力
     * @exception Exception : 入力が失敗した場合の例外を処理する
     */
    public String inputString(String prompt){
	this.setPrompt(prompt);
	System.out.println(this.prompt);
	try{
	    String n = this.input();
	    return n;
	} catch (Exception s){
	    System.out.println(ERROR_INPUT);
	    return this.inputString(prompt);
	}
    }

    /**
     * 実数(int型)の入力
     * 引数で与えられたメッセージを出力後、キーボードから入力された文字列を実数として返
     すメソッドである。入力された文字列が実数として解釈できない場合には、再入力を促す。
     このメソッドを利用して、キーボードから実数を入力する。
     * @param prompt :入力を促すメッセージ
     * @return Integer入力
     * @exception Exception : 入力値が実数として解釈できない場合の例外を処理する
     */
    public int inputInteger(String prompt){
        this.setPrompt(prompt);
        System.out.println(this.prompt);
        try{
            String value = this.input();
            int n = Integer.parseInt(value);
            return n;
        } catch (Exception s){
            System.out.println(ERROR_INPUT);
            return this.inputInteger(prompt);
        }
    }
}

