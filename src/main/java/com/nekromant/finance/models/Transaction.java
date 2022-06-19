package com.nekromant.finance.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Builder
@Data
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sum")
  private Double sum;

  @Column(name = "category_id")
  Long categoryId;

  @Column(name = "client_id")
  Long clientId;

  @Column(name = "creation_date")
  private ZonedDateTime creationDate;

  @Column(name = "comment")
  private String comment;
}
