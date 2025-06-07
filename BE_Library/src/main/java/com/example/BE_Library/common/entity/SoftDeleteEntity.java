package com.example.BE_Library.common.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SoftDelete;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@SoftDelete(columnName = SoftDeleteEntity.IS_DELETED)
public abstract class SoftDeleteEntity extends BaseEntity{
    public static final String IS_DELETED = "is_deleted";
}
