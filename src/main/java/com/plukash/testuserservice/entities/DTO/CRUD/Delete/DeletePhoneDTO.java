package com.plukash.testuserservice.entities.DTO.CRUD.Delete;

import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeletePhoneDTO extends DTO {
    private String phone;
}
