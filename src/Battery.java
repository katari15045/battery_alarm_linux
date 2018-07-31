public class Battery{
	private static String levelCommand = "cat /sys/class/power_supply/BAT0/capacity";
	private static String statusCommand = "cat /sys/class/power_supply/BAT0/status";
	private static int curLevel = -1;
		
	public static int getLevel(){
		String strLevel = Terminal.exec(levelCommand);
		return Integer.valueOf(strLevel);
	}

	public static boolean isCharging(){
		String status = Terminal.exec(statusCommand);
		if(status.equals("Charging")){
			return true;
		}
		return false;
	}
}
