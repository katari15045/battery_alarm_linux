import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.io.PrintWriter;

public class TextFile{
	public static String read(String fileName){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try{
			File file = new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			while(true){
				line = br.readLine();
				if(line == null){
					br.close();
					return sb.toString();
				}
				sb.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void write(String file, String data){
		PrintWriter pr = null;
		try{
			pr = new PrintWriter(file);
			pr.print(data);
			pr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
