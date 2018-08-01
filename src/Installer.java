import java.io.File;
import java.lang.StringBuilder;

public class Installer{

	public static boolean isInstalled(){
		if(!isBaseFilePresent()){
			return false;
		}
		if(!isCronJobPresent()){
			return false;
		}
		if(!jarCumShPresent()){
			return false;
		}
		return true;
	}

	private static boolean jarCumShPresent(){
		StringBuilder sb = new StringBuilder();
		sb.append(Main.getHomePath());
		sb.append("/Battery_Alarm.jar");
		File jarFile = new File(sb.toString());
		if(!jarFile.exists()){
			return false;
		}
		sb = new StringBuilder();
		sb.append(Main.getHomePath());
		sb.append("/job.sh");
		File shFile = new File(sb.toString());
		if(!shFile.exists()){
			return false;
		}
		return true;
	}

	private static boolean isCronJobPresent(){
		Main.initHomePath();
		String cronCommand = Main.getCronCommand();
		String cronOutput = Terminal.exec("crontab -l");
		if(cronOutput.contains("\n" + cronCommand) || cronOutput.substring(0, cronCommand.length()).equals(cronCommand)){
			return true;
		}
		return false;
	}

	public static boolean isBaseFilePresent(){
		StringBuilder sb = new StringBuilder();
		sb.append("cat ");
		sb.append(Main.getHomePathFileLocation());
		String output = Terminal.exec(sb.toString());
		if(output.length() == 0){
			return false;
		}
		return true;
	}
}
