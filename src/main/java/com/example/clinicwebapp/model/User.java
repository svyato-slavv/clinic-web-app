package com.example.clinicwebapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "clinic_schema")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", schema = "clinic_schema", allocationSize = 1)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Date created_at;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User created_by;
    @Column
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updated_by;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            schema = "clinic_schema",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;
}
