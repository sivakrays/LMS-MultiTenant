package com.LMS.userManagement.service;


import com.LMS.userManagement.dto.LeaderBoardDto;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.*;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderBoardService {

    @Autowired
    ClassroomDataRepository classroomDataRepository;

    @Autowired
    PurchasedCourseRepository purchasedCourseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRankRepository quizRankRepository;

    @Autowired
    SectionRepository sectionRepository;


    public CommonResponse<List<LeaderBoardDto>> getLeaderBoard(Integer standard, long userId, Integer daysCount) {
        Logger logger = LoggerFactory.getLogger(LeaderBoardService.class);
        try {
            // Check if the user is part of the class
            boolean isUser = userRepository.existsById(userId);
            if (!isUser) {
                return CommonResponse.<List<LeaderBoardDto>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message("User is not part of this group")
                        .build();
            }

            // Get all user IDs in the standard
            List<Long> userIds = userRepository.findUserIdsByStandard(standard);
            List<LeaderBoardDto> leaderBoard = new ArrayList<>();

            // Fetch leaderboard data for all users
            for (Long id : userIds) {
                // Get completed courses and sections
                List<String> completedCourses = purchasedCourseRepository.findCompletedCoursesByUserIdsAndDays(List.of(id), daysCount);
                int completedCourseCount = completedCourses.size();

                // Fetch user details
                User user = userRepository.findTheUserByUserId(id);
                String username = user.getName();
                String profileImage = user.getProfileImage();

                // Loop through each course to calculate total points and badges
                int totalEnergyPoints = 0, totalGold = 0, totalSilver = 0, totalBronze = 0;

                for (String courseId : completedCourses) {
                    List<String> sectionIds = sectionRepository.findSectionIdsByCourseId(courseId);

                    for (String sectionId : sectionIds) {
                        totalEnergyPoints += quizRankRepository.sumOfEnergyPointsByUserIdAndSectionId(id, sectionId);
                        totalGold += quizRankRepository.countByUserIdSectionIdAndBadge(id, sectionId, 1);
                        totalSilver += quizRankRepository.countByUserIdSectionIdAndBadge(id, sectionId, 2);
                        totalBronze += quizRankRepository.countByUserIdSectionIdAndBadge(id, sectionId, 3);
                    }
                }

                // Create LeaderBoardDto object for the user
                LeaderBoardDto leaderBoardDto = LeaderBoardDto.builder()
                        .userId(id)
                        .username(username)
                        .profileImage(profileImage)
                        .completedCourseCount(completedCourseCount)
                        .goldCount(totalGold)
                        .silverCount(totalSilver)
                        .bronzeCount(totalBronze)
                        .energyPoints(totalEnergyPoints)
                        .build();

                leaderBoard.add(leaderBoardDto);
            }

            // Sort leaderboard by energy points in descending order
            leaderBoard.sort((a, b) -> b.getEnergyPoints().compareTo(a.getEnergyPoints()));

            // Assign ranks based on sorted leaderboard
            for (int i = 0; i < leaderBoard.size(); i++) {
                leaderBoard.get(i).setRank(i + 1);  // Rank starts from 1
            }

            return CommonResponse.<List<LeaderBoardDto>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message("Leaderboard fetched successfully")
                    .data(leaderBoard)
                    .build();

        } catch (Exception e) {
            logger.error("Error fetching leaderboard for User ID: {}. Error: {}", userId, e.getMessage(), e);

            return CommonResponse.<List<LeaderBoardDto>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message("An error occurred while fetching the leaderboard")
                    .build();
        }
    }
}
