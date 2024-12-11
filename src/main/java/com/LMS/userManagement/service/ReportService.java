package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.ReportDto;
import com.LMS.userManagement.repository.PurchasedCourseRepository;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    PurchasedCourseRepository purchasedCourseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRankRepository quizRankRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public CommonResponse<ReportDto> getReport(long userId) {
        logger.info("Fetching report for user ID: {}", userId);

        try {
            // Validation of user exists
            boolean isUserExists = userRepository.existsById(userId);
            if (!isUserExists) {
                logger.warn("User with ID {} does not exist", userId);
                throw new IllegalArgumentException("User not found");
            }

            // Purchased Courses Count
            List<String> purchasedCoursesIds = purchasedCourseRepository.findCourseIdsByUserId(userId);
            int purchasedCoursesCount = purchasedCoursesIds.size();
            logger.info("Purchased courses count for user {}: {}", userId, purchasedCoursesCount);

            // Completed Courses Count
            List<String> completedCoursesIds = purchasedCourseRepository.findCompletedCoursesByUserId(userId);
            int completedCoursesCount = completedCoursesIds.size();
            logger.info("Completed courses count for user {}: {}", userId, completedCoursesCount);

            // Incomplete Courses Count
            int inCompletedCoursesCount = purchasedCoursesCount - completedCoursesCount;
            logger.info("Incomplete courses count for user {}: {}", userId, inCompletedCoursesCount);

            // Username
            String username = userRepository.findUserNameByUserId(userId);
            logger.info("Fetched username for user {}: {}", userId, username);

            // Badges Count
            int energyPoints = quizRankRepository.sumOfEnergyPoints(userId);
            int goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
            int silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
            int bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);
            logger.info("User {} - Energy Points: {}, Gold: {}, Silver: {}, Bronze: {}", userId, energyPoints, goldCount, silverCount, bronzeCount);

            // Creating Report DTO
            ReportDto reportDto = ReportDto.builder()
                    .userId(userId)
                    .username(username)
                    .purchasedCourseCount(purchasedCoursesCount)
                    .completedCourseCount(completedCoursesCount)
                    .inCompletedCourseCount(inCompletedCoursesCount)
                    .goldBadgeCount(goldCount)
                    .silverBadgeCount(silverCount)
                    .bronzeBadgeCount(bronzeCount)
                    .build();

            logger.info("Report generated successfully for user ID: {}", userId);
            return CommonResponse.<ReportDto>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message("Report fetched successfully")
                    .data(reportDto)
                    .build();
        } catch (Exception e) {
            logger.error("Error generating report for user ID {}: {}", userId, e.getMessage(), e);
            return CommonResponse.<ReportDto>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message("Failed to generate report")
                    .build();
        }
    }

}
