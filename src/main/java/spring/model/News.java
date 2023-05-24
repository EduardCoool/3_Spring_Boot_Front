package spring.model;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String author;

    private boolean moderationAdmin;

    private boolean moderationGPT;

    private int assessmentAggression;

    public News() {
    }

    public News(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public News(Long id, String content, String author, boolean moderationAdmin, boolean moderationGPT) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.moderationAdmin = moderationAdmin;
        this.moderationGPT = moderationGPT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isModerationAdmin() {
        return moderationAdmin;
    }

    public void setModerationAdmin(boolean moderationAdmin) {
        this.moderationAdmin = moderationAdmin;
    }

    public boolean isModerationGPT() {
        return moderationGPT;
    }

    public void setModerationGPT(boolean moderationGPT) {
        this.moderationGPT = moderationGPT;
    }

    public int getAssessmentAggression() {
        return assessmentAggression;
    }

    public void setAssessmentAggression(int assessmentAggression) {
        this.assessmentAggression = assessmentAggression;
    }
}
