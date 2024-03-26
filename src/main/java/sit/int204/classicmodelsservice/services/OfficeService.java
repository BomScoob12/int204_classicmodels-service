package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.repositories.OfficeRepository;

import java.util.List;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository repository;

    public List<Office> getAllOffice() {
        return repository.findAll();
    }

    public Office getOffice(String officeCode) {
        return repository.findById(officeCode).orElseThrow(() -> new ItemNotFoundException("Office Id " + officeCode + " DOES NOT EXIST !!!"));
    }

    @Transactional
    public Office createNewOffice(Office office) {
        return repository.save(office);
    }

    @Transactional
    public void removeOffice(String officeCode) {
        Office office = this.getOffice(officeCode);
        repository.delete(office);
    }

    public Office updateOffice(String officeCode, Office office) {
        if (office.getId() != null && !office.getId().trim().isEmpty()) {
            if (!office.getId().equals(officeCode)) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Conflict Office code  !!! (" + officeCode + " vs " + office.getId() + ")");
            }
        }
        Office existingOffice = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "Office Id " + officeCode + " DOES NOT EXIST !!!"));
        return repository.save(office);
    }
}