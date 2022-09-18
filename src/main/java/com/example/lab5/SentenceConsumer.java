package com.example.lab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;



@Service
public class SentenceConsumer{
    protected Sentence Sentences;

    public SentenceConsumer() {
        this.Sentences = new Sentence();
    }

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s){
        this.Sentences.badSentences.add(s);
        System.out.println("in BadSentence :"+ Sentences.badSentences);
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s){
        this.Sentences.goodSentences.add(s);
        System.out.println("in goodSentence :"+ Sentences.goodSentences);
    }

    @RabbitListener(queues = "GetQueue")
    public Sentence getSentencs(String s){
        return this.Sentences;
    }

}
