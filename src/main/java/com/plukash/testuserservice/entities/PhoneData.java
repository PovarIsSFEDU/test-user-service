package com.plukash.testuserservice.entities;

import com.plukash.testuserservice.entities.DTO.CRUD.Data;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneData extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 13)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
