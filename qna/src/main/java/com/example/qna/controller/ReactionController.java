package com.example.qna.controller;

import com.example.qna.dto.ReactionDto;
import com.example.qna.entity.Reaction;
import com.example.qna.service.ReactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qna/reaction")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;

    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody ReactionDto reactionDto){
        Reaction reaction=new Reaction();
        BeanUtils.copyProperties(reactionDto,reaction);
        reaction.setReactedOn(Instant.now().getEpochSecond());
        reactionService.save(reaction);
    }

    @GetMapping("/fetch/{type}/{id}")
    List<ReactionDto> fetchByValue(@PathVariable(value = "type") String type,@PathVariable(value = "id")String id){
        if (type.equals("answer")){
            List<Reaction> reactions=reactionService.findByAnswerId(id);
            List<ReactionDto> reactionDtos=new ArrayList<>();
            for (Reaction reaction:reactions){
                ReactionDto reactionDto= new ReactionDto();
                BeanUtils.copyProperties(reaction,reactionDto);
                reactionDtos.add(reactionDto);
            }
            return reactionDtos;
        }else{
            List<Reaction> reactions=reactionService.findByQuestionId(id);
            List<ReactionDto> reactionDtos=new ArrayList<>();
            for (Reaction reaction:reactions){
                ReactionDto reactionDto= new ReactionDto();
                BeanUtils.copyProperties(reaction,reactionDto);
                reactionDtos.add(reactionDto);
            }
            return reactionDtos;
        }
    }
}
