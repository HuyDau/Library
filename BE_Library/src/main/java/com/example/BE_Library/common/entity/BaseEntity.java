package com.example.BE_Library.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    public static final String ID = "id";
    public static final String CREATED_AT = "created_at";
    public static final String CREATED_BY = "created_by";
    public static final String UPDATED_AT = "updated_at";
    public static final String UPDATED_BY = "updated_by";

    @CreatedDate
    @Column(name = CREATED_AT, updatable = false)
    private Date createdAt;

    @CreatedBy
    @Column(name = CREATED_BY, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = UPDATED_AT)
    private Date updatedAt;

    @LastModifiedBy
    @Column(name = UPDATED_BY)
    private String updatedBy;
}
