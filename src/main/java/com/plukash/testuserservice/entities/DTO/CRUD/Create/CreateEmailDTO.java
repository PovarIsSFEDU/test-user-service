package com.plukash.testuserservice.entities.DTO.CRUD.Create;

import com.plukash.testuserservice.entities.DTO.CRUD.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmailDTO extends DTO {
    private String email;
}
