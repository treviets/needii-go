package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	static String source="1029384756";
	static String target="6058493721";

	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
		return dtf.format(localDate);
	}

	public static String getDatetimeFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public static boolean createFolder(String path) {
		File theDir = new File(path);
		if (!theDir.exists()) {
			boolean result = false;

			try {
				System.out.println("Begin make dir: " + path);
				theDir.setWritable(true, false);
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				result = false;
				se.printStackTrace();
			}
			return result;
		} else {
			return true;
		}
	}

	public static void zip(List<String> fileList, String output) {
		try {

			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(output);

			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : fileList) {

				File srcFile = new File(file);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the start of the entry
				// data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();

			}

			// close the ZipOutputStream
			zos.close();

		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
	}

	public static String readHtmlFromFile(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
			if(buf != null) {
				buf.close();
			}
			return sb.toString();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
			return "";
		}
	}

	public static String stringVietnameseMoneyFormatWithFloat(float amount) {
		if (amount <= 1000) {
			return String.format("%s vnđ", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			String str_price = String.format("%s vnđ", resp);
			return str_price;
		} catch (Exception e) {
			return "0vnđ";
		}

	}
	
	public static String createRandomString(int length) {
		String stringRanger = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    return new SecureRandom()
	            .ints(length, 0, stringRanger.length())
	            .mapToObj(stringRanger::charAt)
	            .map(Object::toString)
	            .collect(Collectors.joining());
	}
	
	public static String createRandomNumber(int length) {
		String stringRanger = "0123456789";
	    return new SecureRandom()
	            .ints(length, 0, stringRanger.length())
	            .mapToObj(stringRanger::charAt)
	            .map(Object::toString)
	            .collect(Collectors.joining());
	}
	
	public static String encodeShaPassword(String password) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		return hashedPassword;
	}
	
	public static String decodeShaPassword(String hashPassword) {
		return "";
	}
	
	public static String convertStringToSlug(String str) {
		String slug = "";
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace(); 
			slug = "";
		}
		return slug;
	}
	
	public static String removeVietnameseFromString(String str) {
		String slug = "";
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace(); 
			slug = "";
		}
		return slug;
	}
	
	public static String decodeJWTToken(String token){
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        return body;        
    }
	
	public static String removeAllNoneNumberic(String s) {
		if(s == null) {
			return "";
		} else {
			return s.replaceAll("[^\\d.]", "");
		}
		
	}
	
	public static String saveUploadedFile(MultipartFile image, String resourcePath, String filePath) {
		String imageUrl = "";
		if (!image.isEmpty()) {
			try {
				createFolder(resourcePath + filePath);
				byte[] bytes = image.getBytes();
				filePath = filePath + Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
				String fPath = resourcePath + filePath;
				Path path = Paths.get(fPath);
				Files.write(path, bytes);
				imageUrl = filePath;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return imageUrl;
	}
	
	public static String encryptOrderId(long orderId) {
		String s = String.format("%012d", orderId);
		char[] result= new char[s.length()];
	    for (int i=0;i<s.length();i++) {
	        char c=s.charAt(i);
	        int index=source.indexOf(c);
	        result[i]=target.charAt(index);
	    }

	    return new String(result);
	}
	
	public static long decryptOrderId(String s) {
		char[] result= new char[s.length()];
	    for (int i=0;i<s.length();i++) {
	        char c=s.charAt(i);
	        int index=target.indexOf(c);
	        result[i]=source.charAt(index);
	    }

	    return Long.parseLong((new String(result)));
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
	    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static List<Integer> stringToIntegerList(String string){
		List<Integer> integerList = new LinkedList<>();
		String[] stringList = string.split(",");
		for(String s : stringList) integerList.add(Integer.valueOf(s));
		return integerList;
	}
	
	public static List<String> stringSplitToList(String string, String regex){
		return new ArrayList<String>(Arrays.asList(string.split(regex)));
	}

	public static String amountToCurrencyString(float amountTotal, String currency) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + currency;
	}
	
	public static String amountToCurrencyString(float amountTotal) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + "đ";
	}
	
	public static String getDateFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	
	public static String getDateFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public static String getDatetimeFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
}
