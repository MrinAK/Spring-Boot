package eu.restfulwebservice.service;

import eu.restfulwebservice.persistence.entity.Resource;
import eu.restfulwebservice.persistence.repository.ResourceRepository;
import eu.restfulwebservice.service.dto.ResourceDTO;
import eu.restfulwebservice.service.exeption.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void create(ResourceDTO resourceDTO) {

        Resource resource = Resource.create(
                resourceDTO.getResourceId(),
                resourceDTO.getResourceName());
        resourceRepository.save(resource);
    }

    public List<ResourceDTO> findAll() {
        return resourceRepository.findAll()
                .stream()
                .map(resource -> new ResourceDTO(resource.getResourceId(),
                        resource.getResourceName()))
                .collect(Collectors.toList());
    }

    public ResourceDTO findById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(ResourceNotFound::new);

        return new ResourceDTO(resource.getResourceId(), resource.getResourceName());
    }

    @Transactional
    public ResourceDTO updateById(Long resourceId, ResourceDTO resourceDTO) {

        Resource existing = resourceRepository.findById(resourceId).orElseThrow(ResourceNotFound::new);

        Resource newResource = Resource.create(resourceDTO.getResourceId(),
                resourceDTO.getResourceName());
        existing.update(newResource);
        resourceRepository.save(existing);

        return new ResourceDTO(existing.getResourceId(), existing.getResourceName());
    }

    @Transactional
    public void deleteById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(ResourceNotFound::new);
        resourceRepository.deleteById(resourceId);
    }
}
