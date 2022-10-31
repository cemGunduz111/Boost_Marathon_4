package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblstock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productid;
    private String name;
    private String brand;
    @Builder.Default
    private Integer stock = 0;
    private Long updated;
    private Long stockInUpdate;
    private Long stockOutUpdate;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_IN_STOCK;
}
