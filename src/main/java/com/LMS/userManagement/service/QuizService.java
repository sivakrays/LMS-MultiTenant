package com.LMS.userManagement.service;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class QuizService {
    @Autowired
    QuizRankRepository quizRankRepository;

    @Transactional
    public BadgeCounts saveBadge(QuizRank quizRank) {
        Optional<QuizRank> obj = quizRankRepository.findByUserIdAndSubSectionId(quizRank.getUserId(), quizRank.getSubSectionId());
        if (obj.isPresent()) {
            QuizRank quizRank1 = obj.get();
            quizRank1.setEnergyPoints(quizRank.getEnergyPoints());
            quizRank1.setBadge(quizRank.getBadge());
            quizRankRepository.save(quizRank1);
            BadgeCounts data= getBadgeCountsForUser(quizRank1.getUserId(),quizRank1.getEnergyPoints());
            return data;

        }else{
        quizRankRepository.save(quizRank);
            BadgeCounts data1= getBadgeCountsForUser(quizRank.getUserId(),quizRank.getEnergyPoints());
            return data1;
        }

    }

    public BadgeCounts getBadgeCountsForUser(Long userId, Integer energyPoints) {
       int goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
        int silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
        int bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);
        BadgeCounts badgeCounts=new BadgeCounts();
        badgeCounts.setUserId(userId);
        badgeCounts.setEnergyPoints(energyPoints);
        badgeCounts.setGold(goldCount);
        badgeCounts.setSilver(silverCount);
        badgeCounts.setBronze(bronzeCount);
        return badgeCounts;

    }
}
