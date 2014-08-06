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

    private final Map<String,Foto> fotos = new HashMap<>();

    @RequestMapping(value = "/{naam}", method = RequestMethod.GET)
    public Foto get(@PathVariable final String name) {
        return fotos.get(name);
    }

    @RequestMapping(value = "/{naam}/content", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getContent(@PathVariable final String name) {
        final byte[] content = fotos.get(name).getContent();

        return new ResponseEntity<byte[]>(content, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Foto> list() {
        return fotos.values();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String toevoegenFoto(@RequestParam("naam") final String name, @RequestParam("content") final MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                final byte[] bytes = file.getBytes();

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final BufferedOutputStream stream = new BufferedOutputStream(
                        byteArrayOutputStream);

                stream.write(bytes);
                stream.close();

                final Foto foto = new Foto(name, byteArrayOutputStream.toByteArray());
                fotos.put(name, foto);

                return "Bestand met naam " + name + " succesvol geupload";
            } catch (final Exception e) {
                return "De volgende fout is opgetreden bij het uploaden van bestand met naam " + name + " => " + e.getMessage();
            }
        } else {
            return "Bestand " + name + " is leeg.";
        }
    }
}