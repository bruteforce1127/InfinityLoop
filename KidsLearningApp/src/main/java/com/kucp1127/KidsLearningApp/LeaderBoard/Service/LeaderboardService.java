package com.kucp1127.KidsLearningApp.LeaderBoard.Service;

import com.kucp1127.KidsLearningApp.LeaderBoard.Model.Leaderboard;
import com.kucp1127.KidsLearningApp.LeaderBoard.Model.Score;
import com.kucp1127.KidsLearningApp.LeaderBoard.Model.ScoreDTO;
import com.kucp1127.KidsLearningApp.LeaderBoard.Model.ScoreRequest;
import com.kucp1127.KidsLearningApp.LeaderBoard.Repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    // Retrieve scores by username and category
    public List<ScoreDTO> getScoresByUsernameAndCategory(String username, String category) {
        return leaderboardRepository.findById(username)
                .map(leaderboard -> leaderboard.getScores().stream()
                        .filter(score -> score.getCategory().equalsIgnoreCase(category))
                        .map(score -> new ScoreDTO(score.getScore(), score.getTimeDate()))
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    // Calculate average score for a given category (across all leaderboards) and return a message
    public String getAverageScoreMessage(String username, String category) {
        return leaderboardRepository.findById(username)
                .map(leaderboard -> {
                    List<Score> userScores = leaderboard.getScores().stream()
                            .filter(score -> score.getCategory().equalsIgnoreCase(category))
                            .collect(Collectors.toList());

                    if (userScores.isEmpty()) {
                        return "No scores available for username: " + username + " in category: " + category;
                    }

                    double sum = userScores.stream().mapToDouble(Score::getScore).sum();
                    double avg = sum / userScores.size();
                    String avgFormatted = String.format("%.2f", avg);

                    if (avg < 5) {
                        return "Below average, needs to work hard. Its average is " + avgFormatted;
                    } else if (avg <= 6.5) {
                        return "Average, needs to work little more to sharpen its skills. Its average is " + avgFormatted;
                    } else if (avg <= 8.5) {
                        return "Above average, is good but needs to work a little more to sharpen its skills. Its average is " + avgFormatted;
                    } else {
                        return "Excellent, needs to practice to shape its skills and focus on other weaker topics. Its average is " + avgFormatted;
                    }
                })
                .orElse("User " + username + " not found.");
    }


    // Update the scores for a given username with a new score
    public Leaderboard updateScore(String username, ScoreRequest scoreRequest) {
        Leaderboard leaderboard = leaderboardRepository.findById(username)
                .orElseGet(() -> {
                    Leaderboard newLeaderboard = new Leaderboard();
                    newLeaderboard.setUsername(username);
                    newLeaderboard.setScores(new ArrayList<>()); // Ensure scores list is initialized
                    return newLeaderboard;
                });

        Score newScore = new Score();
        newScore.setCategory(scoreRequest.getCategory());
        newScore.setScore(scoreRequest.getScore());
        newScore.setTimeDate(LocalDateTime.now());

        leaderboard.getScores().add(newScore);
        return leaderboardRepository.save(leaderboard);
    }

    // Create an empty leaderboard with only the username
    public Leaderboard createEmptyLeaderBoard(String username) {
        Leaderboard leaderBoard = new Leaderboard();
        leaderBoard.setUsername(username);
        leaderBoard.setScores(new ArrayList<>()); // Ensure an empty score list is initialized

        return leaderboardRepository.save(leaderBoard);
    }
}
