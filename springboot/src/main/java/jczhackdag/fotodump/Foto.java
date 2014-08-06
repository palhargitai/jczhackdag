package jczhackdag.fotodump;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
public class Foto {

    private final String naam;

    private final byte[] content;

    public Foto(final String naam, final byte[] content) {
        this.content = content;
        this.naam = naam;
    }

    public byte[] getContent() {
        return content;
    }

    public String getNaam() {
        return naam;
    }
}
