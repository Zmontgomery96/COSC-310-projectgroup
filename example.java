package chatBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class example {
	
	public static String[] synonyms(String word) {
		String hold = "";
		int i = 0;
		int beg = 0;
		int end = 0;
		String[] strArr = new String[100];
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.dictionaryapi.com/api/v3/references/thesaurus/json/" + word + "?key=f2be3352-0863-45df-a88b-085a31690fea"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			hold = response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			strArr[0] = "Error in code. Caught exception.";
			return strArr;
		}
		
		beg = hold.indexOf("syns");
		end = hold.indexOf("ants");
		
		hold = hold.substring(beg + 8, end - 4);
		
		//To check the synonyms that the API is putting out, include the code below:
			//System.out.println(hold);
		
		while(hold.contains("\"")) {
			end = hold.indexOf("\"", 1);
			
			strArr[i] = hold.substring(1, end);
			
			try {
				hold = hold.substring(end + 2);
			} catch(StringIndexOutOfBoundsException e) {
				hold = hold.substring(end);
			}
			
			i++;
			
			
			if(hold.contains("[") && hold.indexOf("[", 1) < 2) {
				hold = hold.substring(2);
			}
			if(hold.length() < 2)
				hold = "";
		}
		
		return strArr;
		
	}

	public static void main(String[] args){
		String[] test = new String[100];
		int i = 0;
		
		test = synonyms("no");
		
		
		while(test[i] != null) {
			System.out.println(test[i]);
			i++;
		}
	}
}
