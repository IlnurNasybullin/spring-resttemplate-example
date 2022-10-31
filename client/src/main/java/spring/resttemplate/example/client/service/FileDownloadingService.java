package spring.resttemplate.example.client.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRange;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;

/**
 * @author Ilnur Nasybullin
 * @since 31.10.2022
 */
@Service
public class FileDownloadingService {

    @Value("${url.file}")
    private String fileDownloadingUrl;

    @SneakyThrows
    public void downloadAndSaveFile() {
        var file = Files.createTempFile("download", "tmp");
        Files.setAttribute(file, "filename", "0xff");
        try(var fileOutputStream =
                Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            RequestCallback requestCallback = request -> {
                HttpHeaders headers = request.getHeaders();
                headers.setRange(List.of(HttpRange.createByteRange(Files.size(file))));
                headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));
            };

            ResponseExtractor<Void> responseExtractor = response -> {
                var stream = response.getBody();
                stream.transferTo(fileOutputStream);
                return null;
            };

            new RestTemplate().execute(
                    fileDownloadingUrl, HttpMethod.GET, requestCallback, responseExtractor
            );
        }
        System.out.println(Files.getAttribute(file, "filename"));
    }
}
