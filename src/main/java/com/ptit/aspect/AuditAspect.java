package com.ptit.aspect;

import com.ptit.model.BaseEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Before("execution(* org.springframework.data.repository.CrudRepository.save(..)) && args(entity)")
    public void setAuditFields(Object entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            LocalDateTime now = LocalDateTime.now();
            if (baseEntity.getCreatedAt() == null) {
                baseEntity.setCreatedAt(now);
            }
            baseEntity.setUpdatedAt(now);
        }
    }
}