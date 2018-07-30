public class Main{
	private static int threshold = 41;
	public static String homePath = "/home/saketh/Documents/cron_jobs/battery_alarm";
	private static String alreadyWarnedFile = homePath + "/already_warned.txt";

	public static void main(String[] args){
		int level = Battery.getLevel();
		boolean isCharging = Battery.isCharging();
		boolean alreadyWarned = alreadyWarned();
		if(level <= threshold && (!isCharging) && (!alreadyWarned)){
			Home.display(level);
			if(!alreadyWarned){
				TextFile.write(alreadyWarnedFile, "1");
			}
		}
		if(level > threshold){
			TextFile.write(alreadyWarnedFile, "0");
		}
	}

	private static boolean alreadyWarned(){
		String strData = TextFile.read(alreadyWarnedFile);
		if(strData.isEmpty()){
			return false;
		}
		int intData = Integer.valueOf(strData);
		if(intData == 0){
			return false;
		}
		return true;
	}
}
