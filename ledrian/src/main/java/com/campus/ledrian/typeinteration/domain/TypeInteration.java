package com.campus.ledrian.typeinteration.domain;

import com.campus.ledrian.interation.domain.Interation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class TypeInteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "typeInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interation> interactions;

    public TypeInteration() {
    }

    public TypeInteration(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Interation> getInterations() {
        return interactions;
    }

    public void setInterations(List<Interation> interactions) {
        this.interactions = interactions;
    }
    
    

}
