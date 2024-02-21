package com.example.loginauthjwt.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "categoryCode", unique = true)
    private String categoryCode;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    //===========================
    @Column(name = "createBy")
    private String createdBy;
    @Column(name = "createAt")
    private String createAt;
    @Column(name = "updateBy")
    private String updateBy;
    @Column(name = "updateAt")
    private String updateAt;
    @Column(name = "status")
    private Long status;

    public Category(String name, String categoryCode, Long status, String description, String createBy, String updateBy, String createAt, String updateAt) {
        this.categoryCode = categoryCode;
        this.description = description;
        this.name = name;
        this.createdBy = createBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
    }
}
