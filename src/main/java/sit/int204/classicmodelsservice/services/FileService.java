package sit.int204.classicmodelsservice.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.FileStorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Getter
public class FileService {
    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        System.out.println("----------absolute------------");
        System.out.println(Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath());
        System.out.println("----------normalize ----------");
        System.out.println(Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize());
        try {
            if (!Files.exists(this.fileStorageLocation)) {
                Files.createDirectories(this.fileStorageLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String store(MultipartFile file) {
        System.out.println("Original path file : " + file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation= this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName+ ". Please try again!", e);
        }
    }
}
