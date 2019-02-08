package us.jmay;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(fetchUrl("https://jmay.us"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fetchUrl(String url) throws IOException {
        URL urlObj = new URL(url);
        InputStream rawStream = urlObj.openStream();
        BufferedInputStream stream = new BufferedInputStream(rawStream);
        byte[] body = stream.readAllBytes();
        stream.close();
        rawStream.close();

        return new String(body);
    }
}
