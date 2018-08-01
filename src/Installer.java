import java.lang.StringBuilder;

public class Installer{

	public static boolean isInstalled(){
		if(!isBaseFilePresent()){
			return false;
		}
		if(!isCronJobPresent()){
			return false;
		}
		return true;
	}

	private static boolean isCronJobPresent(){
		return true;
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
