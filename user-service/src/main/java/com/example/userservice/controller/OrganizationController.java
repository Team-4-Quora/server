package com.example.userservice.controller;

import com.example.userservice.dto.FollowerDto;
import com.example.userservice.dto.OrganizationDto;
import com.example.userservice.entity.Follower;
import com.example.userservice.entity.Organization;
import com.example.userservice.kafka.dto.Pages;
import com.example.userservice.kafka.service.PagesService;
import com.example.userservice.service.FollowerService;
import com.example.userservice.service.OrganizationService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private FollowerService followerService;

    @Autowired
    private PagesService pagesService;


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DirectExchange exchangeOrgElastic;


    @GetMapping("/{id}")
    OrganizationDto getAnOrganization(@PathVariable(name = "id") String orgId){
        Organization organization= organizationService.findById(orgId);
        OrganizationDto organizationDto =new OrganizationDto();
        BeanUtils.copyProperties(organization, organizationDto);
        return organizationDto;
    }
    @GetMapping("/email/{id}")
    OrganizationDto fetchOrganization(@PathVariable(name = "id") String Id){
        List<Organization> organization= organizationService.findByEmail(Id);
        OrganizationDto organizationDto =new OrganizationDto();
        BeanUtils.copyProperties(organization.get(0), organizationDto);
        return organizationDto;
    }

    @PostMapping("/add")
    void addOrg(@RequestBody OrganizationDto organizationDto){
        Organization organization=new Organization();
        BeanUtils.copyProperties(organizationDto,organization);
        organizationService.save(organization);
        rabbitTemplate.convertAndSend(exchangeOrgElastic.getName(),"routing.OrgElastic",organization);
    }

    @PostMapping("/send/analytics")
    void sendAnalytics(@RequestBody Pages page){
        pagesService.sendOrgData(page);
    }
}
