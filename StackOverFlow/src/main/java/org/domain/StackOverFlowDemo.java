package org.domain;

import java.util.Arrays;
import java.util.List;

public class StackOverFlowDemo {
    public static void main(String[] args) {
        StackOverFlow system = new StackOverFlow();
        User mayank = system.createUser("Mayank", "mnk@gmail.com");
        User kush = system.createUser("Kush", "kush@gmail.com");
        User dhruv = system.createUser("Dhruv", "dhruv@gmail.com");

        // Mayank asks a question
        Question javaQuestion = system.askQuestion(mayank, "Encapsulation", "What is Java Encapsulation ?", Arrays.asList("java", "oops"));

        // Kush answers Alice's question
        Answer kushAnswer = system.answerQuestion(kush, javaQuestion, "Encapsulation in Java is the practice of hiding data by making fields private and providing controlled access through public getters and setters.");

        //Dhruv comments on the question
        system.addComment(dhruv, javaQuestion, "Great question! I'm also interested in learning about this.");

        // Mayank comments on Bob's answer
        system.addComment(mayank, kushAnswer, "Thanks for the explanation! Could you provide a code example?");

        // Dhruv votes on the question and answer
        system.voteQuestion(mayank, javaQuestion, 1);  // Upvote
        system.voteAnswer(kush, kushAnswer, 1);  // Upvote

        // Kush accepts Bob's answer
        system.acceptAnswer(kushAnswer);


        Question pythonQuestion = system.askQuestion(kush, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                Arrays.asList("python", "list-comprehension"));

        // Mayank answers Kush's question
        Answer mayankAnswer = system.answerQuestion(mayank, pythonQuestion,
                "List comprehensions in Python provide a concise way to create lists...");

        // Dhruv votes on Kush's question and Mayank's answer
        system.voteQuestion(kush, pythonQuestion, 1);  // Upvote
        system.voteAnswer(mayank, mayankAnswer, 1);  // Upvote

        system.acceptAnswer(mayankAnswer);

        // Print out the current state
        System.out.println("Question: " + javaQuestion.getTitle());
        System.out.println("Asked by: " + javaQuestion.getAuthor().getUsername());
        System.out.println("Tags: " + javaQuestion.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion.getVoteCount());
        System.out.println("Comments: " + javaQuestion.getComments().size());
        System.out.println("\nAnswer by " + kushAnswer.getAuthor().getUsername() + ":");
        System.out.println(kushAnswer.getContent());
        System.out.println("Votes: " + kushAnswer.getVoteCount());
        System.out.println("Accepted: " + kushAnswer.isAccepted());
        System.out.println("Comments: " + kushAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Mayank: " + mayank.getReputation());
        System.out.println("Kush: " + kush.getReputation());
        System.out.println("Dhruv: " + dhruv.getReputation());


        // Demonstrate search functionality
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = system.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        System.out.println("\nSearch Results for 'python':");
        searchResults = system.searchQuestions("python");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        // Demonstrate getting questions by user
        System.out.println("\nMayank's Questions:");
        List<Question> bobQuestions = system.getQuestionsByUser(mayank);
        for (Question q : bobQuestions) {
            System.out.println(q.getTitle());
        }
    }


}
