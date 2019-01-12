
/*------------------------------------------------------------------------------------------------------------------*/
/* This is a subclass to implement interface Mesage.
/* a) This class keeps a reference logger of  MessageLogger.
/* b) This logger is used to call logMsg in class MessageLogger
/* c) The object is created from the Client file, like, logger = new FileLogger();
/*     Then logger is passed into THIS class TextMessage by  Message
/*                            msg = new EncryptedMessage(logger);
/* d) Agent names must be formed by English characters, and no other forms are allowed;
/*     Agent Code can be formed by English characters mixed with digital numbers. THe length
/*     of Agent code must be exactly 12
/*  The algorithm for both encrypting agent name and is that a-->z, b-->y, ...m-->n, y-->b,z-->a.
/*  Upper case letters are also shifted the same way. Numbers are shifted the following way:
/*          0-->9, 1-->8, 2-->7, 3-->6, 4-->5, 5-->4, 6-->3, 7-->2, 8-->1, 9-->0
/*-----------------------------------------------------------------------------------------------------------------*/
import java.util.*;
import java.io.*;
import java.lang.*;

public class EncryptedInfo3 extends AgentInfo {
	private MessageWriter writer;

	public EncryptedInfo3(MessageWriter l) {
		writer = l;
	}

	public void log(String lastNm, String firstNm, String code) {
		if (isValidAgentName(lastNm) == false || isValidAgentName(firstNm) == false) {
			System.out.println("Invalid agent name. Only English characters are allowed.");
			System.exit(1);
		}
		if (isValidCode(code) == false) {
			System.out.println("Invalid agent code. Only English characters and numbers are allowed.");
			System.exit(1);
		}

		String fName = encryptName(firstNm);
		String lName = encryptName(lastNm);

		String codeStr = encryptCode(code);
		writer.logMsg(lName, fName, codeStr);
	}

	// This method can be used to encrypt the first name or the last name
	private String encryptName(String msg) {
		System.out.println("\n Agent name: " + msg);
		char[] alphabet = getAlphabetArray();
		char[] chars = msg.toCharArray();

		for (int m = 0; m < chars.length; m++) {
			for (int n = 0; n < 26; n++) {
				if (chars[m] == alphabet[n]) {
					if (25 == n)
						chars[m] = alphabet[0];
					else
						chars[m] = alphabet[n + 1];
					break;
				} else if (chars[m] == Character.toUpperCase(alphabet[n])) {
					if (25 == n)
						chars[m] = Character.toUpperCase(alphabet[0]);
					else
						chars[m] = Character.toUpperCase(alphabet[n + 1]);
					break;
				}
			}
		}
		System.out.println("Encrypted agent name: " + new String(chars));
		return new String(chars);
	}

	// This method can be used to encrypt the agent code
	private String encryptCode(String code) {

		System.out.println("Agent code: " + code);
		char[] digitArr = getDigitArray();
		char[] codeChars = code.toCharArray();
		char[] alphabet = getAlphabetArray();
		int flag = 1;

		if (codeChars.length != 12) {
			flag = 0;
			System.out.println("Incorrect code length.");
		} else {
			for (int m = 0; m < codeChars.length; m++) {
				for (int n = 0; n < 26; n++) {
					if (codeChars[m] == alphabet[n]) {
						if (25 == n)
							codeChars[m] = alphabet[0];
						else
							codeChars[m] = alphabet[n + 1];
						break;
					} else if (codeChars[m] == Character.toUpperCase(alphabet[n])) {
						if (25 == n)
							codeChars[m] = Character.toUpperCase(alphabet[0]);
						else
							codeChars[m] = Character.toUpperCase(alphabet[n + 1]);
						break;
					}
				}
				for (int j = 0; j < 10; j++) {
					if (codeChars[m] == digitArr[j]) {
						if (9 == j)
							codeChars[m] = digitArr[0];
						else
							codeChars[m] = digitArr[j + 1];
						break;
					}
				}
			}
		}
		if (flag != 0) {
			System.out.println("Encrypted agent code: " + new String(codeChars));
		} else {
			for (int i = 0; i < codeChars.length; i++) {
				codeChars[i] = ' ';
			}
		}
		return new String(codeChars);
	}

	private char[] getAlphabetArray() {
		String str = "abcdefghijklmnopqrstuvwxyz";
		char[] chArray = str.toCharArray();
		return chArray;
	}

	private char[] getDigitArray() {
		String str = "0123456789";
		char[] chArray = str.toCharArray();
		return chArray;
	}
}
