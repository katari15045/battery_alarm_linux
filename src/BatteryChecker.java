public class BatteryChecker{
	private static String alreadyWarnedFile = Main.getHomePath() + "/already_warned.txt";
		
	public static void check(){
		int level = Battery.getLevel();
		boolean isCharging = Battery.isCharging();
		boolean alreadyWarned = alreadyWarned();
		int threshold = Main.getThreshold();
		if(level <= threshold && (!isCharging) && (!alreadyWarned)){
			Alarm.display(level);
			if(!alreadyWarned){
				TextFile.write(alreadyWarnedFile, "1");
			}
		}
		if(isCharging){
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
