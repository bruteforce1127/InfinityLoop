package com.kucp1127.KidsLearningApp.Email.EmailController;


import com.kucp1127.KidsLearningApp.Email.EmailModel.EmailRequest;
import com.kucp1127.KidsLearningApp.Email.EmailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendSimpleEmail(
                emailRequest.getToEmail(),
                "🚨 Oh no! Your daily streak is at risk! 🚨",
                "Hey there! 👋\n\nIt looks like your child hasn’t completed today’s challenge yet, " +
                        "and their awesome streak is in danger of breaking!" +
                        " 😱 Streaks are a fun way to keep the learning momentum going, " +
                        "and we know how much progress they’ve made so far. " +
                        "🌟\n\nDon’t worry—there’s still time to save the streak! " +
                        "Encourage them to jump back in and tackle today’s challenge." +
                        " Every day counts towards their learning journey, and we’re cheering them on!" +
                        " 📚✨\n\nLet’s get back on track together! 💪"
        );
        return "Email sent successfully";
    }
}
