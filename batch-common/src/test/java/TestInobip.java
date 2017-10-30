import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestInobip {

	public TestInobip() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnirestException {
		HttpResponse<String> response = Unirest
				.post("http://api.infobip.com/sms/1/advanced")
				.header("authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
				.header("accept", "application/json")
				.header("content-type", "application/json")
				.body("{\r\n\t\"bulkId\":\"BULK-ID-123-xyz\",\r\n\t\"messages\":[\r\n\t\t{\r\n\t\t\t\"from\":\"InfoSMS\",\r\n\t\t\t\"destinations\":[\r\n\t\t\t\t{\r\n\t\t\t\t\t\"to\":\"41793026727\",\r\n\t\t\t\t\t\"messageId\":\"MESSAGE-ID-123-xyz\"\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"to\":\"41793026731\"\r\n\t\t\t\t}\r\n\t\t\t],\r\n\t\t\t\"text\":\"Artık Ulusal Dil Tanımlayıcısı ile Türkçe karakterli smslerinizi rahatlıkla iletebilirsiniz.\",\r\n\t\t\t\"flash\":false,\r\n\t\t\t\"language\":{\r\n\t\t\t\t\"languageCode\":\"TR\"\r\n\t\t\t},\r\n\t\t\t\"transliteration\":\"TURKISH\",\r\n\t\t\t\"intermediateReport\":true,\r\n\t\t\t\"notifyUrl\":\"http://www.example.com/sms/advanced\",\r\n\t\t\t\"notifyContentType\":\"application/json\",\r\n\t\t\t\"callbackData\":\"DLR callback data\",\r\n\t\t\t\"validityPeriod\": 720\r\n\t\t},\r\n\t\t{\r\n\t\t\t\"from\":\"41793026700\",\r\n\t\t\t\"destinations\":[\r\n\t\t\t\t{\r\n\t\t\t\t\t\"to\":\"41793026785\"\r\n\t\t\t\t}\r\n\t\t\t],\r\n\t\t\t\"text\":\"A long time ago, in a galaxy far, far away... It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.\",\r\n\t\t\t\"sendAt\":\"2015-07-07T17:00:00.000+01:00\",\r\n\t\t\t\"deliveryTimeWindow\": {\r\n\t\t\t\t\"from\": {\r\n\t\t\t\t\t\"hour\": 6,\r\n\t\t\t\t\t\"minute\": 0\r\n\t\t\t\t},\r\n\t\t\t\t\"to\": {\r\n\t\t\t\t\t\"hour\": 15,\r\n\t\t\t\t\t\"minute\": 30\r\n\t\t\t\t},\r\n\t\t\t\t\"days\": [\r\n\t\t\t\t\t\"MONDAY\", \"TUESDAY\", \"WEDNESDAY\", \"THURSDAY\", \"FRIDAY\", \"SATURDAY\", \"SUNDAY\"\r\n\t\t\t\t]\r\n\t\t\t}\r\n\t\t}\r\n\t],\r\n\t\"tracking\":{\r\n\t\t\"track\":\"SMS\",\r\n\t\t\"type\":\"MY_CAMPAIGN\"\r\n\t}\r\n}")
				.asString();
		System.err.println(response.getStatus());
	}

}
