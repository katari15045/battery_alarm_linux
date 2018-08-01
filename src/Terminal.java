import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

public class Terminal{
	public static String exec(String command){
		boolean isFirst = true;
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
				if(!isFirst){
					output.append("\n");
				}else{
					isFirst = false;
				}
				output.append(currentLine);
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return output.toString();
	}

	public static void appendToCrontab(String newData, String homePath){
		StringBuilder sb = new StringBuilder();
		sb.append("(crontab -l ; echo '");
		sb.append(newData);
		sb.append("') | crontab -");
		String cronAppendCommand = sb.toString();
		sb = new StringBuilder();
		sb.append(homePath);
		sb.append("/cronAppend.sh");
		String file = sb.toString();
		TextFile.write(file, cronAppendCommand);
		exec("bash " + file);
	}

	public static String getUsername(){
		return exec("whoami");
	}

	public static String getUserHome(){
		StringBuilder sb = new StringBuilder();
		sb.append("/home/");
		sb.append(getUsername());
		return sb.toString();
	}

}
