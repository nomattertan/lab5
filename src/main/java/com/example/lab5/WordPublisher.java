package com.example.lab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Objects;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;

    public WordPublisher() {
        this.words = new Word();
        this.words.badwords.add("fuck");
        this.words.badwords.add("olo");
        this.words.goodswords.add("happy");
        this.words.goodswords.add("enjoy");
        this.words.goodswords.add("like");}



//    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
//    public ArrayList<String>  addBadWord(@PathVariable("word") String s){
//        this.words.badwords.add(s);
//        return this.words.badwords;
//    }
//
//    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
//    public ArrayList<String>  deleteBadWord(@PathVariable("word") String s){
//        this.words.badwords.remove(s);
//        return this.words.badwords;
//    }
//
//
//    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
//    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
//        this.words.goodswords.add(s);
//        return this.words.goodswords;
//    }
//
//    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
//    public ArrayList<String>  deleteGoodWord(@PathVariable("word") String s){
//        this.words.goodswords.remove(s);
//        return this.words.goodswords;
//    }
//    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
//    public String proofSentence(@PathVariable("sentence") String s){
//        boolean found_good = false;
//        boolean found_bad = false;
//
//        for (int i = 0; i < this.words.goodswords.size(); i++){
//            if(s.contains(this.words.goodswords.get(i))){
//                found_good = true;
//            }
//        }
//
//        for (int i = 0; i < this.words.badwords.size(); i++){
//            if(s.contains(this.words.badwords.get(i))){
//                found_bad = true;
//            }
//        }
//        System.out.println(found_good);
//        System.out.println(found_bad);
//
//        if(found_good && found_bad){
//            rabbitTemplate.convertAndSend("Fanout", "", s);
//            return "good & bad";
//        } else if (found_good) {
//            rabbitTemplate.convertAndSend("Direct", "good", s);
//            return "good";
//        } else if (found_bad){
//            rabbitTemplate.convertAndSend("Direct", "bad", s);
//            return "bad";
//        }
//        return "";
//    }
    @RequestMapping(value = "/addBad", method = RequestMethod.POST)
    public ArrayList<String>  addBadWord(@RequestParam("word") String s){
        this.words.badwords.add(s);
        return this.words.badwords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String>  deleteBadWord(@PathVariable("word") String s){
        this.words.badwords.remove(s);
        return this.words.badwords;
    }


    @RequestMapping(value = "/addGood", method = RequestMethod.POST)
    public ArrayList<String> addGoodWord(@RequestParam("word") String s){
        this.words.goodswords.add(s);
        return this.words.goodswords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String>  deleteGoodWord(@PathVariable("word") String s){
        this.words.goodswords.remove(s);
        return this.words.goodswords;
    }
    @RequestMapping(value = "/proof", method = RequestMethod.POST)
    public String proofSentence(@RequestParam("sentence") String s){
        boolean found_good = false;
        boolean found_bad = false;

        for (int i = 0; i < this.words.goodswords.size(); i++){
            if(s.contains(this.words.goodswords.get(i))){
                found_good = true;
            }
        }

        for (int i = 0; i < this.words.badwords.size(); i++){
            if(s.contains(this.words.badwords.get(i))){
                found_bad = true;
            }
        }
        System.out.println(found_good);
        System.out.println(found_bad);

        if(found_good && found_bad){
            rabbitTemplate.convertAndSend("Fanout", "", s);
            return "good & bad";
        } else if (found_good) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            return "good";
        } else if (found_bad){
            rabbitTemplate.convertAndSend("Direct", "bad", s);
            return "bad";
        }
        return "";
    }
    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){
        Object sentence = rabbitTemplate.convertSendAndReceive("Direct", "try", "huge");
        return  ((Sentence) sentence);
    }
}
