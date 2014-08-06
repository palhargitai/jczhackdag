package jczhackdag.fotodump;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author "Adriaan Wisse (nl27523))"
 */
@RestController
public class FotoController {

//    @RequestMapping("")
//    public Foto post(@RequestParam(value="name", required=false, defaultValue="World") final String name) {
//        return new Greeting(counter.incrementAndGet(),
//    }

    private final Map<String,Foto> fotos = new HashMap<>();

    @RequestMapping(value = "/fotodump", method = RequestMethod.GET)
    public Foto get(@RequestParam(value = "name", required = true) final String name) {
        return fotos.get(name);
    }

    @RequestMapping(value = "/fotodump", method = RequestMethod.POST)
    public String post(@RequestParam("name") final String name, @RequestParam("file") final MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                final byte[] bytes = file.getBytes();

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final BufferedOutputStream stream = new BufferedOutputStream(
                        byteArrayOutputStream);

                stream.write(bytes);
                stream.close();

                final Foto foto = new Foto(byteArrayOutputStream.toByteArray());
                fotos.put(name, foto);

                return "You successfully uploaded file=" + name;
            } catch (final Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name
                    + " because the file was empty.";
        }
    }
}