//情報実験Ⅰ　2018/05/14　BP16090 村松大輝

package EIEV3;

/**
 *ColoredRectangleクラス
 *<PRE>
 *重なり部分の色をマージして色を決定するクラス
 *</PRE>
 *<OL>
 * <LI>public String margeColor(String colorR1,String colorR2)
 *</OL>
 *@author BP16090 村松大輝
 */




public class ColoredRectangle{
    
    public String margeColor(String colorR1,String colorR2){
	String color;
        
	if(colorR1.equals(colorR2))color = "gray";

	else if(colorR1.equals("yellow") && colorR2.equals("blue") || colorR1.equals("blue") && colorR2.equals("yellow"))color = "green";

	else if(colorR1.equals("red") && colorR2.equals("yellow") || colorR1.equals("yellow") && colorR2.equals("red"))color = "orange";

	else if(colorR1.equals("red") && colorR2.equals("blue") || colorR1.equals("blue") && colorR2.equals("red"))color = "magenta";

	else color = "cyan";

	return color;
    }
}
