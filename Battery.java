import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Battery{
	private static String command = "cat /sys/class/power_supply/BAT0/capacity";
	private static int curLevel = -1;
		
	public static int computeLevel(){
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
		curLevel = Integer.valueOf(output.toString());
		return curLevel;
	}

	public static int level(){
		return curLevel;
	}
}
