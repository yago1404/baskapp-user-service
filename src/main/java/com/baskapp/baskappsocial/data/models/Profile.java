package com.baskapp.baskappsocial.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String cellphone;

    @Column(nullable = false)
    private Date birthday;

    private int height;

    private String position;

    @Column(nullable = false)
    private String rule;

    private String picture;

    @Column(nullable = false)
    private Boolean open = true;

    @OneToOne(mappedBy = "profile")
    private User user;
}
