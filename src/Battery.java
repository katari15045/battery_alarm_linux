import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Battery{
	private static String levelCommand = "cat /sys/class/power_supply/BAT0/capacity";
	private static String statusCommand = "cat /sys/class/power_supply/BAT0/status";
	private static int curLevel = -1;
		
	private static String exec(String command){
		Process process = null;
		BufferedReader bufferedReader = null;
		String currentLine = null;
		StringBuilder output = new StringBuilder();
		try{
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			currentLine = bufferedReader.readLine();
			while( currentLine != null ){
				output.append(currentLine);
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return output.toString();
	}

	public static int getLevel(){
		String strLevel = exec(levelCommand);
		return Integer.valueOf(strLevel);
	}

	public static boolean isCharging(){
		String status = exec(statusCommand);
		if(status.equals("Charging")){
			return true;
		}
		return false;
	}
}
