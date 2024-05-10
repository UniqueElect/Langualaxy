package com.langualaxy.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    public Long getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Test getTest() {
        return test;
    }
    public void setTest(Test test) {
        this.test = test;
    }
}