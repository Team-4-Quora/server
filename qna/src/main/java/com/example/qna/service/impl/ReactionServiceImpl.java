package com.example.qna.service.impl;

import com.example.qna.entity.Answer;
import com.example.qna.entity.Question;
import com.example.qna.entity.Reaction;
import com.example.qna.kafka.dto.Qna;
import com.example.qna.kafka.service.QnaService;
import com.example.qna.repository.AnswerRepository;
import com.example.qna.repository.QuestionRepository;
import com.example.qna.repository.ReactionRepository;
import com.example.qna.service.AnswerService;
import com.example.qna.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QnaService qnaService;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void save(Reaction reaction) {
        Reaction dbReacton = null;
        String type="";
        if(reaction.getAnswerId()!=null){
            dbReacton = findByAnswerIdAndReactionBy(reaction.getAnswerId(),reaction.getReactionBy());
            type="answer";
        } else {
            dbReacton= findByQuestionIdAndReactionBy(reaction.getQuestionId(),reaction.getReactionBy());
            type="question";
        }

        if(dbReacton==null){
            reactionRepository.save(reaction);
            if(type.equals("answer")){
                Answer answer= answerRepository.findById(reaction.getAnswerId()).get();
                Qna qna=new Qna();
                qna.setPostId(answer.getId());
                qna.setContentType("Answer");
                qna.setPostName(answer.getMessage());
                qna.setReactionType(reaction.getLike()?"Like":"Dislike");
                qna.setUserId(answer.getAnswerBy());
                qnaService.sendMessage(qna);
            }else{
                    Question question= questionRepository.findById(reaction.getQuestionId()).get();
                    Qna qna=new Qna();
                    qna.setPostId(question.getId());
                    qna.setContentType("Question");
                    qna.setPostName(question.getText());
                    qna.setReactionType(reaction.getLike()?"Like":"Dislike");
                    qna.setUserId(question.getQuestionBy());
                    qnaService.sendMessage(qna);
            }
        }
        else if(dbReacton.getLike()==reaction.getLike()){
            reactionRepository.delete(dbReacton);
        }else if(dbReacton.getLike()!=reaction.getLike()) {
            dbReacton.setLike(!dbReacton.getLike());
            reactionRepository.save(dbReacton);
        }
    }

    @Override
    public List<Reaction> findByQuestionId(String id) {
        return reactionRepository.findByQuestionId(id);
    }

    @Override
    public List<Reaction> findByAnswerId(String id) {
        return reactionRepository.findByAnswerId(id);
    }

    @Override
    public void delete(String id) {
        reactionRepository.deleteById(id);
    }



    @Override
    public Reaction findByQuestionIdAndReactionBy(String id, String email) {
        return reactionRepository.findByQuestionIdAndReactionBy(id,email);
    }

    @Override
    public Reaction findByAnswerIdAndReactionBy(String id, String email) {
        return reactionRepository.findByAnswerIdAndReactionBy(id,email);
    }

    @Override
    public void deleteByAnswerId(String id) {
        reactionRepository.deleteByAnswerId(id);
    }
}