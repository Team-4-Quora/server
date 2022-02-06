package com.example.elasticsearch.controller;

import com.example.elasticsearch.document.Organisation;
import com.example.elasticsearch.dto.OrganisationDto;
import com.example.elasticsearch.service.OrganisationService;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private OrganisationService orgService;

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
    public JSONObject searchInOrg(@PathVariable("query") String query) {
        try {
            List<Organisation> org = orgService.searchOrg(query);

            //todo:get the set of questions based on Answers and respond back List<QuestionDTO>

            JSONObject data = new JSONObject();
            data.put("Organisation", org);
            return  prepareReturnObject(200, "Search data", data);
        } catch (Exception e) {
            return prepareReturnObject(500, "Some error occurred", null);
        }
    }

    public JSONObject prepareReturnObject(int status, String message, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
