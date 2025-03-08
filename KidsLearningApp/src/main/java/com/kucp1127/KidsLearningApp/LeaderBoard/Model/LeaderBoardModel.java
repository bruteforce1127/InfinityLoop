package com.kucp1127.KidsLearningApp.LeaderBoard.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardModel {
    @Id
    private String username;
    private int mathAverage;
    private int gkAverage;
    private int reasoningAverage;
    private int readingAverage;
    private int overallAverage;
}
