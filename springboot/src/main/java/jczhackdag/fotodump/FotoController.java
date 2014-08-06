package jczhackdag.fotodump;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
@RestController
@RequestMapping("/fotodump")
public class FotoController {

    private final Map<String, Foto> fotos = new HashMap<>();

    @RequestMapping(value = "/{naam}", method = RequestMethod.GET)
    public Foto get(@PathVariable final String naam) {
        return fotos.get(naam);
    }

    @RequestMapping(value = "tag/{naam}/{tag}", method = RequestMethod.PUT)
    public void tag(@PathVariable final String naam, @PathVariable final String tag) {
        final Foto foto = fotos.get(naam);
        foto.tag(tag);
    }

    @RequestMapping(value = "/{naam}/content", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getContent(@PathVariable final String naam) {
        final byte[] content = fotos.get(naam).getContent();

        return new ResponseEntity<byte[]>(content, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Foto> list() {
        return fotos.values();
    }

    @RequestMapping(value = "/{naam}", method = RequestMethod.POST)
    public String toevoegenFoto(@PathVariable final String naam,
                                @RequestParam(value = "content", required = true) final MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                final byte[] bytes = file.getBytes();

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final BufferedOutputStream stream = new BufferedOutputStream(
                        byteArrayOutputStream);

                stream.write(bytes);
                stream.close();

                final Foto foto = new Foto(naam, byteArrayOutputStream.toByteArray());
                fotos.put(naam, foto);

                return "Bestand met naam " + naam + " succesvol geupload (PS Pascal Rulez!!!!)";
            } catch (final Exception e) {
                return "De volgende fout is opgetreden bij het uploaden van bestand met naam " + naam + " => " + e.getMessage();
            }
        } else {
            return "Bestand " + naam + " is leeg.";
        }
    }
}