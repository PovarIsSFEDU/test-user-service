package com.plukash.testuserservice.entities;

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
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 13)
    private String phone;
}
