package com.kucp1127.KidsLearningApp.LeaderBoard.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScoreDTO {
    private double score;
    private LocalDateTime timeDate;
}
