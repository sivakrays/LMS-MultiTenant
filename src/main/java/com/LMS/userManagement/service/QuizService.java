package com.LMS.userManagement.service;
import com.LMS.userManagement.enumFile.BadgeType;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class QuizService {
    @Autowired
    QuizRankRepository quizRankRepository;

    //@Transactional
    public ResponseEntity<?> saveBadge(QuizRank quizRank) {
        Long userId = quizRank.getUserId();
        UUID subSectionId = quizRank.getSubSectionId();
        Integer energyPoints = quizRank.getEnergyPoints();
        int badge =quizRank.getBadge();
        Optional<QuizRank> obj = quizRankRepository.findByUserIdAndSubSectionId(userId, subSectionId);
        if (obj.isPresent()) {
            QuizRank quizRank1 = obj.get();
            quizRank1.setEnergyPoints(energyPoints);
            quizRank1.setBadge(badge);
            quizRankRepository.save(quizRank1);
            BadgeCounts data= getBadgeCountsForUser(userId,energyPoints);
            return ResponseEntity.status(HttpStatus.OK).body(data);

        }else{
        quizRankRepository.save(quizRank);
            BadgeCounts data1= getBadgeCountsForUser(userId,energyPoints);
            return ResponseEntity.status(HttpStatus.OK).body(data1);
        }


    }

    public BadgeCounts getBadgeCountsForUser(Long userId, Integer energyPoints) {
       int goldCount = quizRankRepository.countByUserIdAndBadge(userId,1);
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
