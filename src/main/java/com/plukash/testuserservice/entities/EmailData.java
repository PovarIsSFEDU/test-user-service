package com.plukash.testuserservice.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "email_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 200)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
