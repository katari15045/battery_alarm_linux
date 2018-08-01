import java.lang.StringBuilder;

public class Main{
	private static String homePath = null;
	private static int threshold = 41;

	public static void main(String[] args){
		if(args.length == 0 || args[0].equals("install")){
			if(Installer.isInstalled()){
				restoreThreshold();
				Home.display();
			}else{
				DirChooser.display();
			}
		}else if(args[0].equals("check")){
			initHomePath();
			restoreThreshold();
			BatteryChecker.check();
		}
	}

	public static void restoreThreshold(){
		StringBuilder sb = new StringBuilder();
		sb.append(homePath);
		sb.append("/threshold.txt");
		threshold = Integer.valueOf(TextFile.read(sb.toString()));
	}

	public static void initHomePath(){
		String homePathFileLocation = DirChooserHandler.getHomePathFileLocation();	
		homePath = TextFile.read(homePathFileLocation);
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
