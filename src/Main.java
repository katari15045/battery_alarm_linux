public class Main{
	public static String homePath = null;
	public static int threshold = 81;

	public static void main(String[] args){
		if(args[0].equals("install")){
			DirChooser.display();
		}else if(args[0].equals("check")){
			BatteryChecker.check();
		}
	}
	
	public static int getThreshold(){
		return threshold;
	}

	public static void setThreshold(int threshold){
		Main.threshold = threshold;
	}

	public static String getHomePath(){
		return homePath;
	}
	
	public static void setHomePath(String homePath){
		Main.homePath = homePath;
	}
}
