package com.greenwich.comp1640.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "faculty")
public class Faculty {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "coordinator_id", referencedColumnName = "id")
    private User coordinatorManager;
}
