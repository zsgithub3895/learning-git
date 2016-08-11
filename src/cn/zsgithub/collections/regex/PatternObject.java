package cn.zsgithub.collections.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternObject {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter pattern: ");
		String patternString = in.nextLine();//((1?[0-9]):([0-5][0-9]))[ap]m
		Pattern pattern = Pattern.compile(patternString);
		while (true) {
			System.out.println("Enter string to matcher: ");
			String input = in.nextLine();//11:59am
			if (null == input || input.equals(""))
				return;
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				System.out.println("Matcher:" + matcher.group(0));
				int g = matcher.groupCount();//
				System.out.println("群组大小:" + g);
				if (g > 0) {
					for (int i = 0; i < input.length(); i++) {
						for (int j = 1; j <= g; j++)
							if (i == matcher.start(j) && i == matcher.end(j))
								System.out.println("()");
						for (int j = 1; j <= g; j++)
							if (i == matcher.start(j) && i != matcher.end(j))
								System.out.print("(");
						System.out.print(input.charAt(i));
						for (int j = 1; j <= g; j++)
							if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j))
								System.out.print(")");
					}

				}
			}
		}
	}
}