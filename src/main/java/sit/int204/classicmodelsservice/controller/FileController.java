package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.services.FileService;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/test")
    public ResponseEntity<Object> testPropertiesMapping() {
        return ResponseEntity.ok(fileService.getFileStorageLocation() + " has been created !!!");
    }
    @GetMapping("")
    public ResponseEntity<Object> testPropertiesMapping2() {
        return ResponseEntity.ok(fileService.getAllFile());
    }

    @PostMapping("")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) {
        fileService.store(file);
        return ResponseEntity.ok("You successfully uploaded " + file.getOriginalFilename());
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileService.loadFileAsResource(filename);
        String fileName = file.getFilename();
        assert fileName != null;
        int lastDotPos = fileName.lastIndexOf(".");
        String extension = fileName.substring(lastDotPos);
        System.out.println("File extension: " + extension);
        if (extension.equalsIgnoreCase("pdf")) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(file);
        } else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file);
        }
    }

    @DeleteMapping("/{filename:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.ok().body(filename + " Has been removed.");
    }
}
