package com.commerce.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BasicInfo {

    @CreatedBy // @PrePersist
    @Column(updatable = false)
    private String regId;

    @LastModifiedBy // @PreUpdate
    private String modId;
}
