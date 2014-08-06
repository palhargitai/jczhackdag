package jczhackdag.fotodump;

import java.util.ArrayList;
import java.util.List;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
public class Foto {

    private final String naam;

    private final byte[] content;

    private final List<String> tags;

    public Foto(final String naam, final byte[] content) {
        this.content = content;
        this.naam = naam;
        this.tags = new ArrayList<>();
    }

    public byte[] getContent() {
        return content;
    }

    public String getNaam() {
        return naam;
    }

    public List<String> getTags() {
        return tags;
    }

    public void tag(final String tag) {
        this.tags.add(tag);
    }
}
