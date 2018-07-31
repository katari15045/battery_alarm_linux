import java.lang.StringBuilder;

public class Main{
	private static String homePath = null;
	private static int threshold = 81;

	public static void main(String[] args){
		if(args[0].equals("install")){
			DirChooser.display();
		}else if(args[0].equals("check")){
			initHomePath();
			BatteryChecker.check();
		}
	}

	private static void initHomePath(){
		String homePathFileLocation = getHomePathFileLocation();	
		homePath = TextFile.read(homePathFileLocation);
	}

	public static void postInstall(){
		writeHomePathToFile();
		String cronCommand = getCronCommand();
		Terminal.appendToCrontab(cronCommand, homePath);
	}

	private static String getCronCommand(){
		StringBuilder sb = new StringBuilder();
		sb.append("* * * * * bash ");
		sb.append(homePath).append("/job.sh");
		return sb.toString();
	}

	private static void writeHomePathToFile(){
		String homePathFileLocation = getHomePathFileLocation();
		TextFile.write(homePathFileLocation, homePath);
	}

	private static String getHomePathFileLocation(){
		String userHome = Terminal.getUserHome();
		StringBuilder sb = new StringBuilder();
		sb.append(userHome);
		sb.append("/.Do_Not_Delete_homePath.txt");
		return sb.toString();
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
