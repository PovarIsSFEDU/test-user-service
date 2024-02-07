package com.plukash.testuserservice.entities.DTO.CRUD.Update;

import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneDTO extends DTO {
    private String oldPhone;
    private String newPhone;
}
