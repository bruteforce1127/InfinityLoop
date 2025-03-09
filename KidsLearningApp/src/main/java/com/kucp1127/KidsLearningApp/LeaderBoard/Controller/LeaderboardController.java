package com.kucp1127.KidsLearningApp.LeaderBoard.Controller;


import com.kucp1127.KidsLearningApp.LeaderBoard.Model.Leaderboard;
import com.kucp1127.KidsLearningApp.LeaderBoard.Model.ScoreDTO;
import com.kucp1127.KidsLearningApp.LeaderBoard.Model.ScoreRequest;
import com.kucp1127.KidsLearningApp.LeaderBoard.Service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private  LeaderboardService leaderboardService;

    // GET: Retrieve scores for a specific username and category
    @GetMapping("/user/{username}/{category}")
    public List<ScoreDTO> getByUsernameAndCategory(@PathVariable String username,
                                                   @PathVariable String category) {
        return leaderboardService.getScoresByUsernameAndCategory(username, category);
    }

    // GET: Calculate and return average score message for a given category
    @GetMapping("/average/{username}/{category}")
    public String getAverageScoreMessage(
            @PathVariable String username,
            @PathVariable String category) {
        return leaderboardService.getAverageScoreMessage(username, category);
    }


    // POST: Update scores for a given username with new score and category from the request body
    @PostMapping("/user/{username}/score")
    public Leaderboard updateScore(@PathVariable String username,
                                   @RequestBody ScoreRequest scoreRequest) {
        return leaderboardService.updateScore(username, scoreRequest);
    }
}
