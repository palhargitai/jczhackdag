package jczhackdag.fotodump;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
public class Foto {

    private final byte[] content;

    public Foto(final byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
