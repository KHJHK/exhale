package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;
    private String name;
}
