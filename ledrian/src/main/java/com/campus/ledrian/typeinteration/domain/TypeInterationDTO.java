
package com.campus.ledrian.typeinteration.domain;


public class TypeInterationDTO {
        private Long id;
    private String type;
    
    public TypeInterationDTO() {
    }

    public TypeInterationDTO(Long id, String type) {
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
}
