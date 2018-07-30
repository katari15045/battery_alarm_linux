public class Main{
	private static int threshold = 41;

	public static void main(String[] args){
		int curBat = Battery.computeLevel();
		if(curBat <= threshold){
			Home.display();
		}
	}
}
