package net.atos.fotodump.app.rest;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by pascal on 6-8-14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FotoObject {

    @JsonProperty("naam")
    private String naam;

    @JsonProperty("content")
    private byte[] content;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
