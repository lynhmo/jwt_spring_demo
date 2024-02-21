package com.example.loginauthjwt.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "categoryId", nullable = false)
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
    @Column(name = "productCode", unique = true)
    private String productCode;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Long price;
    @Column(name = "sold")
    private Long sold;

    //========================
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

    public Product(String createdBy, String createAt, String updateBy, String updateAt, Long status, Category category, String productCode, String description, String name, Long price, Long sold) {
        this.category = category;
        this.productCode = productCode;
        this.description = description;
        this.name = name;
        this.price = price;
        this.sold = sold;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
    }

}
