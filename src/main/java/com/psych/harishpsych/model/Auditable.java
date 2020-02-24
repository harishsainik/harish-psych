package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {
    @Id
    @Getter @Setter
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", allocationSize = 10)
    private Long id;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, updatable = false)
    @CreatedDate
    private Date created_at = new Date();

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable=false)
    private Date updated_at = new Date();
}
