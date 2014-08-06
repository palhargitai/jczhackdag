package net.atos.fotodump.app.rest;

import android.os.AsyncTask;
import net.atos.fotodump.app.rest.FotoObject;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pascal on 6-8-14.
 */
public class AdriaanRESTAsyncTask extends AsyncTask<FotoObject, Void, Void> {

    @Override
    protected Void doInBackground(FotoObject... params) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);

        RestTemplate restTemplate = new RestTemplate();
        final String url = String.format("http://161.90.26.139:8080/fotodump/%s", params[0].getNaam());

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("content", params[0].getContent());

        restTemplate.postForLocation(url, params[0], parts);
        return null;
    }
}
