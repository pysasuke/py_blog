package py.blog.base.util;

import java.util.Random;

public class RandomUtils {
	
	
	
	/**
	 * 产生0～max的随机整数 包括0 不包括max
	 * 
	 * @param max
	 *            随机数的上限
	 * @return
	 */
	public static int getRandom(int max) {
		return new Random().nextInt(max);
	}

	/**
	 * 产生 min～max的随机整数 包括 min 但不包括 max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		int r = getRandom(max - min);
		return r + min;
	}
	
	
	
	/**
	 * 产生0～max的随机整数 包括0 不包括max
	 * 
	 * @param max
	 *            随机数的上限
	 * @return
	 */
	public static long getRandomLong(long max) {
		long randNum  = (long)(Math.random()* max);// + minId;
		return randNum;
	}

	/**
	 * 产生 min～max的随机整数 包括 min 但不包括 max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getRandomLong(long min, long max) {
		long r = getRandomLong(max - min);
		return r + min;
	}
	
	
	/**
	 * 随机获取图片
	 * @param num
	 * @return
	 */
	public static long getSQLRandom(Long num){
		Long newNum  = getRandomLong(num);
		String numStr = newNum+"";
		if(numStr.length() < 8){
			return newNum;
		}
		int randLen = getRandom(8,numStr.length());
		return Long.valueOf(numStr.substring(0, randLen));
	}


	public static String getRomStr() {

		StringBuffer sb = new StringBuffer();

		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();

		char[] randBuffer = new char[8];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}

		sb.append(new String(randBuffer));

		return sb.toString();
	}
}
