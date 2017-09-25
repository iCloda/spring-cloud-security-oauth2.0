package com.orieange.repo.extend.druid.msql.support.impl;

/**
 * Author: wangy
 */
class Strings {

	public static Boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	public static boolean equalsAny(String str1, String... str2) {
		if (isEmpty(str1) || str2 == null || str2.length <= 0) {
			return false;
		}
		for (final String searchString : str2) {
			if (equals(str1, searchString)) {
				return true;
			}
		}
		return false;
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	public static boolean startsWith(String str, String prefix) {
		return startsWith(str, prefix, false);
	}

	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return startsWith(str, prefix, true);
	}

	public static boolean startsWithAny(final String string, final String... searchStrings) {
		if (isEmpty(string) || searchStrings == null || searchStrings.length <= 0) {
			return false;
		}
		for (final String searchString : searchStrings) {
			if (startsWith(string, searchString)) {
				return true;
			}
		}
		return false;
	}

	private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
		if (str == null || prefix == null) {
			return (str == null && prefix == null);
		}
		if (prefix.length() > str.length()) {
			return false;
		}
		return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
	}

	public static boolean endsWithAny(final String string, final String... searchStrings) {
		if (isEmpty(string) || searchStrings == null || searchStrings.length <= 0) {
			return false;
		}
		for (final String searchString : searchStrings) {
			if (endsWith(string, searchString)) {
				return true;
			}
		}
		return false;
	}

	public static boolean endsWith(String str, String suffix) {
		return endsWith(str, suffix, false);
	}

	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return endsWith(str, suffix, true);
	}

	private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
		if (str == null || suffix == null) {
			return (str == null && suffix == null);
		}
		if (suffix.length() > str.length()) {
			return false;
		}
		int strOffset = str.length() - suffix.length();
		return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
	}

	public static StringBuffer replaceAll(StringBuffer sb, String oldStr, String newStr) {
		int i = sb.indexOf(oldStr);
		int oldLen = oldStr.length();
		int newLen = newStr.length();
		while (i > -1) {
			sb.delete(i, i + oldLen);
			sb.insert(i, newStr);
			i = sb.indexOf(oldStr, i + newLen);
		}
		return sb;
	}

	public static String substringBefore(final String str, final String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.isEmpty()) {
			return "";
		}
		final int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfter(final String str, final String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return "";
		}
		final int pos = str.indexOf(separator);
		if (pos == -1) {
			return "";
		}
		return str.substring(pos + separator.length());
	}

}
