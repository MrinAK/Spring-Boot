package eu.restfulwebservice.service.dto;

import eu.restfulwebservice.persistence.entity.Role;

import java.sql.Timestamp;

public class ResourceDTO {

    private Long resourceId;

    private String resourceName;

    public ResourceDTO(Long resourceId, String resourceName) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;

    }

    public ResourceDTO(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceDTO() {
    }


    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
