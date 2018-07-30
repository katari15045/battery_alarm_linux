import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
	private static String command = "cat /sys/class/power_supply/BAT0/capacity";
	private static int curLevel = -1; 
	private static int threshold = 41;

	public static void main(String[] args){
		String output = executeCommand();
		curLevel = Integer.valueOf(output);
		System.out.println(curLevel);
	}

	

	private static String executeCommand(){
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

}
