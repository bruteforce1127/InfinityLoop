package com.kucp1127.KidsLearningApp.ChildProfile.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ChildProfile {

    // Using the username as the primary key
    @Id
    private String username;

    // Coins the child has earned
    private int coins;

    // Unlocked avatars stored as a set of strings
    @ElementCollection
    @CollectionTable(name = "child_unlocked_avatars", joinColumns = @JoinColumn(name = "child_username"))
    @Column(name = "avatar")
    private Set<String> unlockedAvatars = new HashSet<>();

    // One-to-Many relationship with GameRecord
    @OneToMany(mappedBy = "childProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GameRecord> gameRecords = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "daily_question_correct", joinColumns = @JoinColumn(name = "child_username"))
    private List<DailyQuestionCorrect> dailyQuestionCorrect;

    // Helper method to add an avatar
    public void addAvatar(String avatar) {
        this.unlockedAvatars.add(avatar);
    }

}
