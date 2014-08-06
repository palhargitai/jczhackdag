package jczhackdag.fotodump;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
public class Foto {

    private final String name;

    private final byte[] content;

    public Foto(final String name, final byte[] content) {
        this.content = content;
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
