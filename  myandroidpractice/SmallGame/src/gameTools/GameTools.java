package gameTools;

import edu.xiapei.Statics;

public class GameTools {
	public static String arrayToString(int[][] array){
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<Statics.MAPHIGHT;i++){
			for(int j=0;j<Statics.MAPWIDTH;j++){
				sb.append(String.valueOf(array[i][j]));
				sb.append(" ");
			}
		}
		return sb.toString().trim();
	}
	public static int[][] stringToArray(String s){
		String[] sarray=s.split(" ");
		int[][] array = new int[Statics.MAPHIGHT][Statics.MAPWIDTH];
		for(int i=0;i<Statics.MAPHIGHT;i++){
			for(int j=0;j<Statics.MAPWIDTH;j++){
				array[i][j]=Integer.parseInt(sarray[i*Statics.MAPHIGHT+j]);
			}
		}
		return array;
	}
	
}
