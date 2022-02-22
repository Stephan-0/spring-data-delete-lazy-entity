package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "child")
public class ChildEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    @Column(name = "lockversion")
    private Integer version;

    @OneToMany(mappedBy = "child", fetch = FetchType.LAZY)
    private final Set<ParentEntity> parents = new HashSet<>();

    public Set<ParentEntity> getParents() {
        return parents;
    }

    // this will fix the issue
    // public Integer getVersion() {
    // return version;
    // }

}
