package companies.yandex.contest.DTask;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTask {

    public static void main(String[] args) throws IOException, ParseException {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String uri = r.readLine();
        String port = r.readLine();
        String a = r.readLine();
        String b = r.readLine();

        String urlString = getUrl(uri, port, a, b);
        List<Integer> responseArray = getResponse(urlString);
        Collections.sort(responseArray);
        responseArray.forEach(System.out::println);
    }

    @SuppressWarnings("unchecked")
    private static List<Integer> getResponse(String urlString) throws IOException, ParseException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream responseStream = connection.getInputStream();
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(
                    new InputStreamReader(responseStream, StandardCharsets.UTF_8));
            List<Integer> response = new ArrayList<>(jsonArray.size());
            jsonArray.forEach(number -> response.add(Integer.parseInt(String.valueOf(number))));
            return response;
        }
        return Collections.emptyList();
    }

    private static String getUrl(String uri, String port, String a, String b) {
        return uri.concat(":").concat(port)
                .concat("?")
                .concat("a=").concat(a)
                .concat("&")
                .concat("b=").concat(b);
    }
}
