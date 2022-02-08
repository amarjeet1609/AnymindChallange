package jp.co.anymind.walletapp.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class WalletUtil {

	public static Timestamp toTimeStamp(String datetime) {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(datetime);
		return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
	}
	
	public static String toOffsetDatetime(Timestamp timestamp) {
		long millisSinceEpoch = timestamp.getTime();
		Instant instant = Instant.ofEpochMilli(millisSinceEpoch);
		OffsetDateTime dt = OffsetDateTime.ofInstant(instant, ZoneId.of("UTC"));
		System.out.println(dt.toString());
		return dt.toString().replace("Z", ":00+00:00");
	}
	
	public static Timestamp offsetDatetimeToTimestamp(OffsetDateTime offsetDateTime) {
		return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
	}
}
