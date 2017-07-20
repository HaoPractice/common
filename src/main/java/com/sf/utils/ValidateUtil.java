package com.sf.utils;

import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	private static final EmailValidator emailValidator = new EmailValidator();
	
	public static boolean isEmailAddr(String value) {
		return emailValidator.isValid(value);
	}
	
	private static class EmailValidator {

		private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
		private static final String DOMAIN = ATOM+"+(\\."+ATOM+"+)*";
		private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
		private static final int MAX_LOCAL_PART_LENGTH = 64;
		private static final int MAX_DOMAIN_PART_LENGTH = 255;
		private final Pattern localPattern = Pattern.compile(DOMAIN, 2);
		private final Pattern domainPattern = Pattern.compile(
				DOMAIN+"|"+IP_DOMAIN,
				2);

		public boolean isValid(CharSequence value) {
			if ((value == null) || (value.length() == 0)) {
				return true;
			}

			String[] emailParts = value.toString().split("@", 3);
			if (emailParts.length != 2) {
				return false;
			}

			if ((emailParts[0].endsWith(".")) || (emailParts[1].endsWith("."))) {
				return false;
			}

			if (!(matchPart(emailParts[0], this.localPattern, MAX_LOCAL_PART_LENGTH))) {
				return false;
			}

			return matchPart(emailParts[1], this.domainPattern, MAX_DOMAIN_PART_LENGTH);
		}

		private boolean matchPart(String part, Pattern pattern, int maxLength) {
			String asciiString;
			try {
				asciiString = toAscii(part);
			} catch (IllegalArgumentException e) {
				return false;
			}
			if (asciiString.length() > maxLength) {
				return false;
			}

			Matcher matcher = pattern.matcher(asciiString);
			return matcher.matches();
		}

		private String toAscii(String unicodeString) throws IllegalArgumentException {
			String asciiString = "";
			int start = 0;
			int end = (unicodeString.length() <= 63) ? unicodeString.length() : 63;
			while (true) {
				asciiString = asciiString + IDN.toASCII(unicodeString.substring(start, end));
				if (end == unicodeString.length()) {
					break;
				}
				start = end;
				end = (start + 63 > unicodeString.length()) ? unicodeString.length() : start + 63;
			}

			return asciiString;
		}
		
	}
}
