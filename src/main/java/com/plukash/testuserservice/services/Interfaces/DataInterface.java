package com.plukash.testuserservice.services.Interfaces;

import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Data;
import com.plukash.testuserservice.entities.User;

public interface DataInterface<T extends Data> {
    T saveWithReturn(T t);

    public void save(T t);

    public <U extends DTO> T createInstance(U dto);

    public void createLink(User user, T newData);

    public <U> void update(User user, U dto);

    public boolean checkUnique(String data);

    public <U> void delete(User user, U dto);
}
