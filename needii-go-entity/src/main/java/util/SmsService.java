package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

import util.Constants;

public class SmsService {

	public static final String API_URL = "http://api.speedsms.vn/index.php";
	
	public static void sendVerifyMessage(String toPhoneNumber, String verifyCode) {
		try {
			String message = String.format("%s", verifyCode);
			SmsService.send(toPhoneNumber, message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void send(String toPhoneNumber, String content) {
		try {
			String json = String.format("{\"to\": [\"%s\"], \"content\": \"%s\", \"sms_type\": 2, \"brandname\":\"\"}", toPhoneNumber, content);
			URL url = new URL(API_URL + "/sms/send"); 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			String userCredentials = Constants.SMS_API_KEY + ":x";
			String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userCredentials.getBytes());
			conn.setRequestProperty ("Authorization", basicAuth);
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(json);
			wr.flush();
			wr.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine = "";
			StringBuffer buffer = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();
			//return buffer.toString();	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}