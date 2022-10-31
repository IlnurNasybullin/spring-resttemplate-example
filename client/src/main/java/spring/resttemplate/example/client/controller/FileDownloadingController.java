package spring.resttemplate.example.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.resttemplate.example.client.service.FileDownloadingService;

/**
 * @author Ilnur Nasybullin
 * @since 31.10.2022
 */
@RestController
@RequiredArgsConstructor
public class FileDownloadingController {

    private final FileDownloadingService fileDownloadingService;

    @PostMapping("/download-file")
    public void downloadFile() {
        fileDownloadingService.downloadAndSaveFile();
    }

}
