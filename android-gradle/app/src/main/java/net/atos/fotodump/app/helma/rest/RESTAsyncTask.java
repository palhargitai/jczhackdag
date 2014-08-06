package net.atos.fotodump.app.helma.rest;

import android.os.AsyncTask;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pascal on 6-8-14.
 */
public class RESTAsyncTask extends AsyncTask<FotoObject, Void, Void> {

    @Override
    protected Void doInBackground(FotoObject... params) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        final String url = "http://161.90.26.139:8080/fotodump";
        restTemplate.postForLocation(url, params[0]);
        return null;
    }
}
