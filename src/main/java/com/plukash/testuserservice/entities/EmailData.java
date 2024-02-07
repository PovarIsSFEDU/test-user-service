package com.plukash.testuserservice.entities;

import com.plukash.testuserservice.entities.DTO.CRUD.Data;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "email_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailData extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 200)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
