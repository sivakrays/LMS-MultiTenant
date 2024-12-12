package com.LMS.userManagement.service;


import com.LMS.userManagement.dto.LeaderBoardDto;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.ClassroomDataRepository;
import com.LMS.userManagement.repository.PurchasedCourseRepository;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderBoardService {

    @Autowired
    ClassroomDataRepository classroomDataRepository;

    @Autowired
    PurchasedCourseRepository purchasedCourseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizService quizService;

    @Autowired
    QuizRankRepository quizRankRepository;


    public CommonResponse<List<LeaderBoardDto>> getLeaderBoard(String classRoomId, long userId) {
        Logger logger = LoggerFactory.getLogger(LeaderBoardService.class);
        try {
            // Check if the user is part of the class
            Boolean isUserInClassRoom = classroomDataRepository.existsByUserId(userId);
            if (!isUserInClassRoom) {
                return CommonResponse.<List<LeaderBoardDto>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message("User is not part of this group")
                        .build();
            }

            // Get all user IDs in the classroom
            List<Long> userIds = classroomDataRepository.findUserIdsByClassroomId(classRoomId);
            List<LeaderBoardDto> leaderBoard = new ArrayList<>();

            // Fetch leaderboard data for all users
            for (Long id : userIds) {
                // Get completed courses for each user
                List<String> completedCourses = purchasedCourseRepository.findCompletedCoursesByUserIds(List.of(id));
                int completedCourseCount = completedCourses.size();

                // Fetch user details
                User user = (User) userRepository.findUserByUserId(id);
                String username = user.getName();
                String profileImage = user.getProfileImage();

                // Fetch energy points and badge counts
                Integer energyPoints = quizRankRepository.sumOfEnergyPoints(id);
                int goldCount = quizRankRepository.countByUserIdAndBadge(id, 1);
                int silverCount = quizRankRepository.countByUserIdAndBadge(id, 2);
                int bronzeCount = quizRankRepository.countByUserIdAndBadge(id, 3);

                // Create LeaderBoardDto object for the user
                LeaderBoardDto leaderBoardDto = LeaderBoardDto.builder()
                        .userId(id)
                        .username(username)
                        .profileImage(profileImage)
                        .completedCourseCount(completedCourseCount)
                        .goldCount(goldCount)
                        .silverCount(silverCount)
                        .bronzeCount(bronzeCount)
                        .energyPoints(energyPoints)
                        .build();

                // Add to leaderboard list
                leaderBoard.add(leaderBoardDto);
            }

            // Sort leaderboard by energy points in descending order
            leaderBoard.sort((a, b) -> b.getEnergyPoints().compareTo(a.getEnergyPoints()));

            // Assign ranks based on sorted leaderboard
            for (int i = 0; i < leaderBoard.size(); i++) {
                LeaderBoardDto leaderBoardDto = leaderBoard.get(i);
                leaderBoardDto.setRank(i + 1);  // Rank starts from 1
            }

            return CommonResponse.<List<LeaderBoardDto>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message("Leaderboard fetched successfully")
                    .data(leaderBoard)
                    .build();

        } catch (Exception e) {
            logger.error("Error fetching leaderboard for Classroom ID: {}. Error: {}", classRoomId, e.getMessage(), e);

            // Return an error response
            return CommonResponse.<List<LeaderBoardDto>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message("An error occurred while fetching the leaderboard")
                    .build();
        }
    }

}
