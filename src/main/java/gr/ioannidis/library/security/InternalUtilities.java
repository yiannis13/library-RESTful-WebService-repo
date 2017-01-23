package gr.ioannidis.library.security;

import java.util.Date;

class InternalUtilities {

	static boolean hasExpired(Date date) {
		if (date == null) {
			return true;
		}
		Date now = new Date();
		return date.before(now);
	}
}
