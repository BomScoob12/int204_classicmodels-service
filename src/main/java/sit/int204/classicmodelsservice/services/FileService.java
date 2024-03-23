package sit.int204.classicmodelsservice.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.FileStorageProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File operation error: " + fileName, e);
        }
    }

    public void deleteFile(String filename) {
        try {
            Path file = this.fileStorageLocation.resolve(filename);
            boolean status = Files.deleteIfExists(file);
            if (status) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
                throw new RuntimeException("File not found " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("File operation error: " + e.getMessage());
        }
    }

    //another way
    public void removeFile(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("Success");
            } else {
                System.out.println("Failed");
                throw new RuntimeException("File not found " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("File operation error: " + e.getMessage());
        }
    }

    public List<String> getAllFile() {
        try (Stream<Path> stream = Files.list(this.fileStorageLocation)) {
            return stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
