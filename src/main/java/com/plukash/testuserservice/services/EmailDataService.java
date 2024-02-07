package com.plukash.testuserservice.services;

import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdateEmailDTO;
import com.plukash.testuserservice.entities.EmailData;
import com.plukash.testuserservice.entities.User;
import com.plukash.testuserservice.repositories.EmailDataRepository;
import com.plukash.testuserservice.services.Interfaces.DataInterface;
import com.plukash.testuserservice.utilities.CustomExceptions.AccessViolationException;
import com.plukash.testuserservice.utilities.CustomExceptions.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailDataService implements DataInterface<EmailData> {
    private final EmailDataRepository emailDataRepository;

    private EmailData findByEmail(String email) {
        return emailDataRepository.findByEmail(email).orElseThrow();
    }


    public EmailData saveWithReturn(EmailData ed) {
        return emailDataRepository.save(ed);
    }


    public void save(EmailData ed) {
        emailDataRepository.save(ed);
    }


    public <U extends DTO> EmailData createInstance(U dto) {
        return this.saveWithReturn(EmailData.builder().email(((CreateEmailDTO) dto).getEmail()).build());
    }


    public void createLink(User user, EmailData newData) {
        newData.setUser(user);
        this.save(newData);
    }


    public <U> void update(User user, U dto) {
        var real_dto = (UpdateEmailDTO) dto;
        var email_data = this.findByEmail(real_dto.getOldEmail());
        if (Objects.equals(email_data.getUser().getId(), user.getId())) {
            if (this.checkUnique(real_dto.getNewEmail())) {
                email_data.setEmail(real_dto.getNewEmail());
                this.save(email_data);
            }
        } else {
            throw new AccessViolationException("You cannot change email for other people!");
        }
    }


    public boolean checkUnique(String data) {
        if (emailDataRepository.countDistinctByEmail(data) == 0) {
            return true;
        } else throw new DuplicateException("You cannot use this phone!");
    }


    public <U> void delete(User user, U dto) {
        var real_dto = (UpdateEmailDTO) dto;
        var email_data = this.findByEmail(real_dto.getOldEmail());
        if (Objects.equals(email_data.getUser().getId(), user.getId())) {
            emailDataRepository.delete(email_data);
        } else {
            throw new AccessViolationException("You cannot delete email for other people!");
        }
    }
}
