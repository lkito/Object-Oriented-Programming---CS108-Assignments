import java.security.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Cracker {
	private static final String DIGEST_TYPE = "SHA";
	private MessageDigest md;

	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();	
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/*
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}
	
	// possible test values:
	// a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
	// fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
	// a! 34800e15707fae815d7c90d49de44aca97e2d759
	// xyz 66b27417d37e024c46526c2f6d358a754fc552f3


	public Cracker(String passWord){
		try {
			md = MessageDigest.getInstance(DIGEST_TYPE);
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		}
		md.update(passWord.getBytes());
		byte[] digestRes = md.digest();
		System.out.println(hexToString(digestRes));
	}

	public Cracker(String passHex, int passLength, int numThreads){
		CountDownLatch signal = new CountDownLatch(numThreads);
		int singleThreadChars = CHARS.length / numThreads;
		Worker[] workers = new Worker[numThreads];
		for(int i = 0; i < numThreads; i++){
			int startInd = i * singleThreadChars;
			int endInd = (i + 1) * singleThreadChars - 1;
			if(i == numThreads - 1) endInd = CHARS.length - 1;
			workers[i] = new Worker(passHex, passLength, startInd, endInd, signal);
		}
		for(int i = 0; i < numThreads; i++){
			workers[i].start();
		}
		try {
			signal.await();
		} catch (InterruptedException ex) {};
		for(int i = 0; i < numThreads; i++){
			List<String> res = workers[i].getResult();
			for(int k = 0; k < res.size(); k++) {
                System.out.println(res.get(k));
            }
		}
        System.out.println("All Done!");
	}

	private class Worker extends Thread {
		private MessageDigest workerMD;
		private CountDownLatch signal;
		private byte[] passHex;
		private int passLength, startInd, endInd;
		private List<String> result;

		public Worker(String passHex, int passLength, int startInd, int endInd, CountDownLatch signal){
			this.passHex = hexToArray(passHex);
			this.passLength = passLength;
			this.startInd = startInd;
			this.endInd = endInd;
			this.result = new ArrayList<>();
			this.signal = signal;
			try {
				workerMD = MessageDigest.getInstance(DIGEST_TYPE);
			} catch (NoSuchAlgorithmException nae) {
				nae.printStackTrace();
			}
		}

		private void recGenerate(String str){
            workerMD.update(str.getBytes());
            if(Arrays.equals(passHex, workerMD.digest())){
                result.add(str);
            }
			if(str.length() == passLength){
				return;
			}
			for (int i = 0; i < CHARS.length; i++){
				recGenerate(str + CHARS[i]);
			}
		}

		public List<String> getResult() {
			return result;
		}

		public void run() {
			for (int i = startInd; i <= endInd; i++){
				recGenerate("" + CHARS[i]);
			}
			signal.countDown();
		}

	}

	public static void main(String[] args){
		if(args.length == 1) {
			Cracker crack = new Cracker(args[0]);
		} else {
			Cracker crack = new Cracker(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		}
	}

}
