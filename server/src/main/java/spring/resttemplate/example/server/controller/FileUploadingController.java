package spring.resttemplate.example.server.controller;

import jdk.jfr.ContentType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ilnur Nasybullin
 * @since 31.10.2022
 */
@RestController
public class FileUploadingController {

    @GetMapping(
            value = "/download-as-bytes",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public HttpEntity<byte[]> getFileBytes(HttpServletResponse response) {
        byte[] bytes;
        try (InputStream fileStream = getClass().getResourceAsStream("/files/file_example_WAV_10MG.wav")) {
            bytes = fileStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition disposition = ContentDisposition.attachment()
                        .filename("file_example_WAV_10MG.wav")
                        .build();

        headers.setContentDisposition(disposition);

        return new HttpEntity<>(bytes, headers);
    }

}
