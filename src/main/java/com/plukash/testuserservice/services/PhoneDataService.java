package com.plukash.testuserservice.services;

import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreatePhoneDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdatePhoneDTO;
import com.plukash.testuserservice.entities.PhoneData;
import com.plukash.testuserservice.entities.User;
import com.plukash.testuserservice.repositories.PhoneDataRepository;
import com.plukash.testuserservice.services.Interfaces.DataInterface;
import com.plukash.testuserservice.utilities.CustomExceptions.AccessViolationException;
import com.plukash.testuserservice.utilities.CustomExceptions.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhoneDataService implements DataInterface<PhoneData> {

    private final PhoneDataRepository phoneDataRepository;

    private PhoneData findByEmail(String phone) {
        return phoneDataRepository.findByPhone(phone).orElseThrow();
    }


    public PhoneData saveWithReturn(PhoneData pd) {
        return phoneDataRepository.save(pd);
    }


    @Override
    public void save(PhoneData pd) {
        phoneDataRepository.save(pd);
    }

    @Override
    public <U extends DTO> PhoneData createInstance(U dto) {
        return this.saveWithReturn(PhoneData.builder().phone(((CreatePhoneDTO) dto).getPhone()).build());
    }

    @Override
    public void createLink(User user, PhoneData newData) {
        newData.setUser(user);
        this.save(newData);
    }

    @Override
    public <U> void update(User user, U dto) {
        var real_dto = (UpdatePhoneDTO) dto;
        var phone_data = this.findByEmail(real_dto.getOldPhone());
        if (Objects.equals(phone_data.getUser().getId(), user.getId())) {
            if (this.checkUnique(real_dto.getNewPhone())) {
                phone_data.setPhone(real_dto.getNewPhone());
                this.save(phone_data);
            }
        } else {
            throw new AccessViolationException("You cannot change phone for other people!");
        }
    }

    @Override
    public boolean checkUnique(String data) {
        if (phoneDataRepository.countDistinctByPhone(data) == 0) {
            return true;
        } else throw new DuplicateException("You cannot use this phone!");
    }

    @Override
    public <U> void delete(User user, U dto) {
        var real_dto = (UpdatePhoneDTO) dto;
        var phone_data = this.findByEmail(real_dto.getOldPhone());
        if (Objects.equals(phone_data.getUser().getId(), user.getId())) {
            phoneDataRepository.delete(phone_data);
        } else {
            throw new AccessViolationException("You cannot delete phone for other people!");
        }
    }
}
