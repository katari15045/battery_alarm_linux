1. Make sure you have all ".class files, script.sh" at "/home/saketh/Documents/cron_jobs/battery_alarm/" with the below commands -
	cd /home/saketh/Documents/github/battery_alarm_linux
	javac Main.java
	cp *.class /home/saketh/Documents/cron_jobs/battery_alarm/
	cp script.sh /home/saketh/Documents/cron_jobs/battery_alarm/
2. Open Linux Terminal and type - 
	crontab -e
3. Add the below line at the end of the file -
	* * * * * bash /home/saketh/Documents/cron_jobs/battery_alarm/script.sh
4. Save and close the file.
