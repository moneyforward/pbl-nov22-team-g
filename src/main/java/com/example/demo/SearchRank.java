package com.example.demo;

import com.example.demo.pojo.BookList;

import java.util.*;

/*
 score = argmax#c P(c|w) = argmax#c P(w|c)*P(c)/P(w)
 prob(w) = 1.
 prob(c) is customize for users, default 1.
 prob(w|c) calculated by distance + author relevance + isbn relevance
 */

public class SearchRank {
    private final List<BookList> recallSet;

    public SearchRank(List<BookList> recallSet){
        this.recallSet = recallSet;
    }
    private Double minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        int distance = dp[word1.length()][word2.length()];
        return switch (distance){
            case 0: yield 1.;
            case 1: yield .5;
            case 2: yield .1;
            case 3: yield .05;
            default: yield .0;
        };
    }

    // get prob(correct)
    private HashMap<BookList, Double> userScore(){
        // customize of user's score for every book
        HashMap<BookList, Double> scoreMap = new HashMap<>();
        this.recallSet.forEach(book -> scoreMap.put(book, 1.));
        return scoreMap;
    }

    private List<BookList> ranking(String query){
        HashMap<BookList, Double> candidateSet = this.userScore();
        HashMap<BookList, Double> rankedSet = new HashMap<>();

        // calculate score
        for(Map.Entry<BookList, Double> entry: candidateSet.entrySet()){
            BookList candidate = entry.getKey();
            double choiceScore = entry.getValue();

            // isbn search
            int coverScore = candidate.getIsbn().indexOf(query);
            if(coverScore != -1) {
                rankedSet.put(candidate, Math.abs(coverScore-10.)/100.);
            }

            int titleCover = candidate.getTitle().toLowerCase().indexOf(query);
            if(titleCover != -1) {
                rankedSet.put(candidate, Math.abs(titleCover-10.));
            }else {
                double disScore = 0.;
                for(String word: candidate.getTitle().toLowerCase().split(" ")){
                    disScore = Math.max(minDistance(query, word), disScore);
                }
                double score = disScore * choiceScore;
                if(score > 0.){
                    rankedSet.put(candidate, score);
                }
            }

            int authorCover = candidate.getAuthor().toLowerCase().indexOf(query);
            if(authorCover != -1) {
                rankedSet.put(candidate, Math.abs(authorCover-10.)/10.);
            }else {
                double disScore = minDistance(query, candidate.getAuthor().toLowerCase());
                double score = disScore * choiceScore;
                if(score > 0.){
                    rankedSet.put(candidate, score/10);
                }
            }
        }
        List<Map.Entry<BookList, Double>> list = new ArrayList<>(rankedSet.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<BookList> rankResult = new ArrayList<>();
        list.forEach(entry->rankResult.add(entry.getKey()));
        return rankResult.subList(0, 20);
    }

    public List<BookList> getRankList(String query){
        return ranking(query);
    }
}
