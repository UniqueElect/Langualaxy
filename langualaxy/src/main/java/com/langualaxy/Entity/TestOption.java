    package com.langualaxy.Entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    @Entity
    @Table(name = "test_options")
    public class TestOption {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String text;
    //    @Column(columnDefinition = "TINYINT(1)")
        private boolean correct;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "question_id")
        private Question question;

    }
