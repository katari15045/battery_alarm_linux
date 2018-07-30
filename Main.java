public class Main{
	private static int threshold = 41;

	public static void main(String[] args){
		int level = Battery.getLevel();
		if((!Battery.isCharging()) && level <= threshold){
			Home.display(level);
		}
	}
}
