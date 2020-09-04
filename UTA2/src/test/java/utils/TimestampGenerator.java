package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampGenerator
{
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	public static String currentTimestamp()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}
}
