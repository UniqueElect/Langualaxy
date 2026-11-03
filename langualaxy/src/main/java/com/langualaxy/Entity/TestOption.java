    package com.langualaxy.Entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @Entity
    @NoArgsConstructor
    @Table(name = "test_options")
    public class TestOption {
        public TestOption(String text, boolean correct, Question question){
            this.text = text;
            this.correct = correct;
            this.question = question;
        }
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
