package com.baskapp.baskappsocial.data.models;

import com.baskapp.baskappsocial.data.models.enums.PlayerPosition;
import com.baskapp.baskappsocial.data.models.enums.UserRule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRule rule;

    private String picture;

    @Column(nullable = false)
    private Boolean open = true;

    @OneToOne(mappedBy = "profile")
    private User user;

    @ManyToMany(mappedBy = "players")
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "coach")
    private List<Team> coachingTeams = new ArrayList<>();
}
