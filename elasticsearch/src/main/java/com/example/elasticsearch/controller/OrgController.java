package com.example.elasticsearch.controller;

import com.example.elasticsearch.document.Organisation;
import com.example.elasticsearch.document.User;
import com.example.elasticsearch.dto.OrganisationDto;
import com.example.elasticsearch.dto.UserDto;
import com.example.elasticsearch.service.OrganisationService;
import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/org")
public class OrgController {


    @Autowired
    private OrganisationService orgService;

    @RabbitListener(queues = "queue.OrgElastic")
    @PostMapping("/save")
    public void save(@RequestBody OrganisationDto orgDto) {
        System.out.println(orgDto.getDescription()+"DescHere");
        Organisation org = new Organisation();
        BeanUtils.copyProperties(orgDto, org);
        orgService.save(org);
    }

    @GetMapping("/all")
    public List<Organisation> getAll() {
        return orgService.getAll();
    }


    @GetMapping("/searchorg/{query}")
    public List<OrganisationDto> searchInOrg(@PathVariable("query") String query) {
        try {
            List<Organisation> orglist = orgService.searchOrg(query);

            List<OrganisationDto> organisationDtos=new ArrayList<>();

            for(Organisation organisation:orglist)
            {

                OrganisationDto organisationDto=new OrganisationDto();
                BeanUtils.copyProperties(organisation,organisationDto);
                organisationDtos.add(organisationDto);

            }

            return  organisationDtos;
        }
        catch (Exception e)
        {
            return  null;
        }

//            JSONObject data = new JSONObject();
//            data.put("Organisation", org);
//            return  prepareReturnObject(200, "Search data", data);
//        } catch (Exception e) {
//            return prepareReturnObject(500, "Some error occurred", null);
//        }
    }

    public JSONObject prepareReturnObject(int status, String message, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
