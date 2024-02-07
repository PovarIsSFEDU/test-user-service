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
public class UpdateEmailDTO  extends DTO {
    private String oldEmail;
    private String newEmail;
}
