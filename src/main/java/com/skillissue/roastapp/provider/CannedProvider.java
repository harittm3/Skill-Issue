package com.skillissue.roastapp.provider;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CannedProvider implements RoastProvider {

    private final Map<String, List<String>> canned = Map.of(
            "SDE", List.of(
                    "You call yourself a Software Development Engineer, but your GitHub contribution graph looks like a crime scene photo. Green squares are rarer than your interview callbacks. You LeetCode like you're allergic to correctness — O(n²) solutions with the confidence of someone who's never heard of Big O.",
                    "Your resume says 'proficient in Java' but you still Google 'how to reverse a string java' every single time. You've watched 40 hours of DSA YouTube videos and retained approximately zero algorithms. Recruiters skim your resume for 6 seconds and spend 5 of them laughing.",
                    "You think 'System Design' means drawing three boxes and an arrow labeled 'magic happens here.' Your idea of scaling a system is closing more Chrome tabs. Every mock interview you've done ends the same way: silence, sweat, and a very generous 'we'll get back to you.'",
                    "You've been 'grinding DSA' for eight months and you still can't invert a binary tree without crying a little. Your code reviews come back redder than a stop sign. Somewhere, a whiteboard is having nightmares about you.",
                    "You call bugs 'undocumented features' and hope nobody notices. Your commit messages are just 'fix' repeated forty times like a confession nobody asked for. If confidence shipped code, you'd be a FAANG principal engineer by now — instead you're stuck debugging a for loop since Tuesday."
            ),
            "Data Analyst", List.of(
                    "You put 'proficient in SQL' on your resume but you still write SELECT * and pray. Your dashboards have more misleading pie charts than a conspiracy theorist's PowerPoint. You call it 'data storytelling' — everyone else calls it guessing with extra steps.",
                    "You 'clean data' the way toddlers clean their rooms — shove everything under the rug and call it done. Your Excel formulas are held together with hope and VLOOKUP errors nobody's brave enough to fix. Stakeholders read your reports and immediately schedule a meeting to un-confuse themselves.",
                    "You think correlation implies causation and you will die on that hill. Your idea of statistical rigor is eyeballing a trendline and vibing. Every insight you've ever presented could've been replaced by a Magic 8-Ball with better accuracy.",
                    "You've used Python for exactly one thing: importing pandas and immediately Googling the rest. Your A/B tests have sample sizes smaller than your LinkedIn network. Confidence intervals? You're not even confident in your own conclusions.",
                    "You call yourself 'data-driven' but your decisions are 90% gut feeling and 10% a chart you didn't fully understand. You've presented the same insight three times because nobody remembers what you said the first two. Your pivot tables pivot away from the truth."
            ),
            "Full Stack Engineer", List.of(
                    "You call yourself full-stack but your CSS looks like it was designed during an earthquake. Your backend is a single Express route doing the job of twelve microservices, held together by console.logs you forgot to remove. 'It works on my machine' is basically your love language.",
                    "Your frontend has more div soup than a homeless shelter kitchen. You center a div and call it a personality trait. Your API returns 200 OK even when it fails, because accountability was never part of the stack you learned.",
                    "You've 'built' six To-Do list apps and zero things anyone's ever used. Your idea of state management is fifteen useState hooks fighting each other in the dark. Your deployment process is 'push to main and pray to the Vercel gods.'",
                    "You claim you 'know React' but you still don't understand why your component re-renders forty times a second. Your Git history is a graveyard of commits titled 'final,' 'final final,' and 'actually final.' Users don't rage-quit your app — they just quietly never come back.",
                    "Your database schema looks like it was designed by throwing darts at a whiteboard blindfolded. You've never met a null pointer you didn't personally create. 'Full-stack' should legally require a permit after what you've done to that backend."
            ),
            "AI Engineer", List.of(
                    "You call yourself an AI Engineer but you've never trained anything more complex than your patience for reading documentation. Your 'custom model' is just a ChatGPT wrapper with a fancier README. You say 'fine-tuning' in interviews and hope nobody asks a follow-up question.",
                    "Your understanding of transformers begins and ends with a YouTube thumbnail that said 'Attention Is All You Need' and you never actually read the paper. You've called three different things 'AI' this week and none of them involved a single gradient update. Your resume has more buzzwords than your model has parameters.",
                    "You think prompt engineering is a personality trait now. Your 'RAG pipeline' is a for-loop and a prayer. You've hallucinated more confidence in interviews than your model ever has in production.",
                    "You use 'leverage LLMs' in every sentence like it's a magic spell that replaces actual understanding. Your embeddings are cosine-similarity-searched into oblivion because you don't know what else to do with them. Somewhere, an actual ML researcher just felt a disturbance in the force.",
                    "You call GPU cost optimization 'not my department' and mean it. Your model overfits harder than you oversold your skills on this application. You've read the phrase 'attention mechanism' more times than you've implemented one."
            ),
            "ML Engineer", List.of(
                    "You call yourself an ML Engineer but your model's confusion matrix is less confused than you are about your own career. You've overfit every model you've ever trained and undertrained every skill you actually need. Your loss curve goes down; your credibility does not follow.",
                    "Your idea of hyperparameter tuning is changing the learning rate and hoping for the best like it's a slot machine. You've deployed exactly zero models to production but you've got 'MLOps' on your resume anyway. Your validation accuracy is the only thing you've never actually validated.",
                    "You still don't know the difference between precision and recall but you'll explain 'AI ethics' to anyone who'll listen. Your feature engineering is just throwing every column in and letting XGBoost sort it out. You call that a pipeline; everyone else calls it duct tape.",
                    "You trained a model for six hours and called it a day when it hit 51% accuracy — a coin flip does better and doesn't need a GPU. Your Jupyter notebook has 200 cells and zero of them run in order anymore. You've cited 'data drift' as an excuse for problems that were always just your code.",
                    "Your idea of model interpretability is shrugging and saying 'it's a black box.' You've copy-pasted more Kaggle notebooks than you've written original code. Somewhere, a decision tree is embarrassed to be associated with you."
            ),
            "Cloud Engineer", List.of(
                    "You call yourself a Cloud Engineer but your last AWS bill made the finance team schedule an emergency meeting. You've left three EC2 instances running since March because you were 'pretty sure' you'd need them again. Your idea of infrastructure as code is a folder named 'final_scripts_REAL'.",
                    "You set every S3 bucket to public 'just to test something quickly' and never went back. Your VPC diagram looks like a subway map drawn during turbulence. You've said 'it's probably a networking issue' about literally every bug you've ever caused.",
                    "Your Kubernetes cluster has more crash loops than a toddler learning to walk. You copy-paste Terraform from Stack Overflow and pray the apply doesn't nuke production. You call yourself 'multi-cloud' because you have one free-tier account on two different providers.",
                    "You've never once successfully set up CI/CD without breaking the pipeline for everyone else on a Friday afternoon. Your load balancer configuration has load-balanced exactly zero load. Somewhere, a security team is quietly crying about your IAM permissions.",
                    "You think 'the cloud' just means someone else's problem now, and you've made that very clear in every incident postmortem. Your Linux skills begin and end with `sudo` and blind hope. You've restarted a server as your first, second, and only troubleshooting step for two years running."
            )
    );

    @Override
    public String generateRoast(String role, Map<String, Integer> ratings, int percentage) {
        List<String> options = canned.getOrDefault(role, List.of("No roast available for this role."));
        int index = ThreadLocalRandom.current().nextInt(options.size());
        return options.get(index);
    }
}