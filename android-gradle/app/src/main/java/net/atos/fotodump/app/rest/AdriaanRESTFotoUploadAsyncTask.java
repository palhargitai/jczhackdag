package net.atos.fotodump.app.rest;

import android.os.AsyncTask;
import net.atos.fotodump.app.rest.FotoObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;

/**
 * Created by pascal on 6-8-14.
 */
public class AdriaanRESTFotoUploadAsyncTask extends AsyncTask<FotoObject, Void, Void> {

    @Override
    protected Void doInBackground(final FotoObject... params) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new SourceHttpMessageConverter<Source>());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        final String url = String.format("http://161.90.26.139:8080/fotodump/%s", params[0].getNaam());

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("content", new ByteArrayResource(params[0].getContent()) {
            @Override
            public String getFilename() throws IllegalStateException {
                return params[0].getNaam();
            }
        });

        restTemplate.postForLocation(url, parts);
        return null;
    }
}
