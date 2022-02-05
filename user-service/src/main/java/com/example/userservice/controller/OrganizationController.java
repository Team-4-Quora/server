package com.example.userservice.controller;

import com.example.userservice.dto.FollowerDto;
import com.example.userservice.dto.OrganizationDto;
import com.example.userservice.entity.Follower;
import com.example.userservice.entity.Organization;
import com.example.userservice.service.FollowerService;
import com.example.userservice.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private FollowerService followerService;

    @GetMapping("/{id}")
    OrganizationDto getAnOrganization(@PathVariable(name = "id") String orgId){
        Organization organization= organizationService.findById(orgId);
        OrganizationDto organizationDto =new OrganizationDto();
        BeanUtils.copyProperties(organization, organizationDto);
        return organizationDto;
    }


    @PostMapping("/add")
    void addOrg(@RequestBody OrganizationDto organizationDto){
        Organization organization=new Organization();
        BeanUtils.copyProperties(organizationDto,organization);
        organizationService.save(organization);
    }
}
