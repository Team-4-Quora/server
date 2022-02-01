package com.example.userservice.controller;

import com.example.userservice.dto.OrganizationsDto;
import com.example.userservice.entity.Organizations;
import com.example.userservice.service.OrganizationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
public class OrganizationsController {
    @Autowired
    private OrganizationsService organizationsService;

    @GetMapping("/{id}")
    OrganizationsDto getAnOrganization(@PathVariable(name = "id") String orgId){
        Organizations organization=organizationsService.findById(orgId);
        OrganizationsDto organizationsDto=new OrganizationsDto();
        BeanUtils.copyProperties(organization,organizationsDto);
        return organizationsDto;
    }
}
