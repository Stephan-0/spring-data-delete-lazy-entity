package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import java.util.UUID;

@Entity
@Table(name = "parent")
public class ParentEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    @Column(name = "lockversion")
    private Integer version;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parentid")
    private ChildEntity child;

    public UUID getId() {
        return id;
    }

    public ChildEntity getChild() {
        return child;
    }

    public void setChild(final ChildEntity child) {
        child.getParents().add(this);
        this.child = child;
    }
    
    public void removeChild() {
        child.getParents().remove(this);
        this.child = null;
    }

}
