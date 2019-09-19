package com.demo.roombooking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity {
  @Id @GeneratedValue protected Long id;

  @JsonIgnore protected LocalDateTime creationDate;

  @JsonIgnore protected LocalDateTime modificationDate;

  @JsonIgnore @Version protected int version;

  @PrePersist
  protected void onCreate() {
    creationDate = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    modificationDate = LocalDateTime.now();
  }
}
