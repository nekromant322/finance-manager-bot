package com.nekromant.finance.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "comment")
  private String commment;

  @Column(name = "sum")
  private Double sum;

  @ManyToOne private Category category;

  @Column(name = "creation_date")
  private ZonedDateTime creationDate;
}
