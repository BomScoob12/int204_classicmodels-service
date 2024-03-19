package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.FileStorageProperties;
import sit.int204.classicmodelsservice.services.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/test")
    public ResponseEntity<Object> testPropertiesMapping() {
        return ResponseEntity.ok(fileService.getFileStorageLocation() + " has been created !!!");
    }

    @PostMapping("")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) {
        fileService.store(file);
        return ResponseEntity.ok("You successfully uploaded " + file.getOriginalFilename());
    }

}
