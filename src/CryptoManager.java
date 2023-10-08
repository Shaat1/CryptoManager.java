import javafx.scene.control.skin.CellSkinBase;

import java.nio.charset.CharsetEncoder;
import java.util.Random;

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple “substitution cipher” where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	static boolean isAscii;
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 *
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds(String plainText) {
		boolean val = true;
		for (int i = 0; i < plainText.length(); i++) {
			plainText.charAt(i);
			if (plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE) continue;
			else {
				val = false;
			}
		}
		return val;
	}


	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it
	 *
	 * @param plainText an uppercase string to be encrypted.
	 * @param key       an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		if (!isStringInBounds(plainText)) {
			return "The selected string is not in bounds, Try again.";
		}

		String encryptedString = "";

		//for loop to encrypt each letter
		for (int i = 0; i < plainText.length(); i++) {
			int ch = plainText.charAt(i);

			//for loop to encrypt the letter by shifting and wrap up if necessary
			for (int j = key; j > 0; j--) {
				if (ch >= UPPER_RANGE)
					ch = LOWER_RANGE;
				else
					++ch;

			}
			char valueLetter = (char) ch;

			encryptedString += valueLetter;
		}
		return encryptedString;

	}

	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 *
	 * @param plainText  an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption(String plainText, String bellasoStr) {
		int offSet = 0;
		String updated = "";
		String encryptedText = "";

		for (int j = 0; j < plainText.length(); j++) {
			updated = updated + plainText.charAt(j);
			offSet++;
			if (offSet > bellasoStr.length() - 1) {
				offSet = 0;
			}
		}
		int newCode;
		for (int i = 0; i < plainText.length(); i++) {
			int remainder = (i % bellasoStr.length());
			newCode = (int) plainText.charAt(i) + bellasoStr.charAt(remainder);
			while (newCode > UPPER_RANGE) {
				newCode = newCode - UPPER_RANGE;
				newCode = newCode + LOWER_RANGE;
				newCode--;
			}

			encryptedText += (char) newCode;

		}

		return encryptedText;
	}


	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 *
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key           an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption(String encryptedText, int key) {
		String val = "";
		int ch = 0;
		for (int i = 0; i < encryptedText.length(); i++) {
			ch = encryptedText.charAt(i);

			for (int j = key; j > 0; j--) {
				if (ch <= LOWER_RANGE) {
					ch = UPPER_RANGE;
				} else
					ch--;
			}
			char valLetter = (char) ch;
			val = val + valLetter;

		}
		return val;
	}

	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 *
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr    an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		String Text = "";
		char ch;
		int remainder;
		for (int i = 0; i < encryptedText.length(); i++) {
			remainder = (i % bellasoStr.length());
			ch = (char) (encryptedText.charAt(i) - bellasoStr.charAt(remainder));
			while (ch < LOWER_RANGE) {
				ch += RANGE;
			}
			while (ch > UPPER_RANGE) {
				ch -= RANGE;
			}
			Text += String.valueOf(ch);


		}
		return Text;

	}
}
