package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.QuizBean;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;


@Service
public class QuizService {

    @Autowired
    QuizRankRepository quizRankRepository;

    //@Transactional
    public CommonResponse<BadgeCounts> saveBadge(QuizRank quizRank) {
        Logger logger = LoggerFactory.getLogger(QuizService.class);

        Long userId = quizRank.getUserId();
        String sectionId = quizRank.getSectionId();
        Integer energyPoints = quizRank.getEnergyPoints();
        int badge = quizRank.getBadge();

        try {
            Optional<QuizRank> existingQuizRank = quizRankRepository.findByUserIdAndSectionId(userId, sectionId);

            BadgeCounts data;
            if (existingQuizRank.isPresent()) {
                QuizRank quizRankToUpdate = existingQuizRank.get();
                quizRankToUpdate.setEnergyPoints(energyPoints);
                quizRankToUpdate.setBadge(badge);
                quizRankRepository.save(quizRankToUpdate);

                data = getBadgeCountsForUser(userId, energyPoints);
                logger.info("Badge updated successfully for userId: {} and sectionId: {}", userId, sectionId);

                return CommonResponse.<BadgeCounts>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.BADGE_UPDATED)
                        .data(data)
                        .build();

            } else {
                quizRankRepository.save(quizRank);

                data = getBadgeCountsForUser(userId, energyPoints);
                logger.info("Badge saved successfully for userId: {} and sectionId: {}", userId, sectionId);

                return CommonResponse.<BadgeCounts>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.BADGE_SAVED)
                        .data(data)
                        .build();
            }
        } catch (Exception e) {
            logger.error("Error saving badge for userId: {} and sectionId: {}. Error: {}", userId, sectionId, e.getMessage(), e);

            return CommonResponse.<BadgeCounts>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message("An error occurred while saving the badge")
                    .build();
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

    public CommonResponse<List<QuizBean>> uploadQuizCsv(MultipartFile file) {
        DataFormatter formatter = new DataFormatter();
        List<QuizBean> quizList = null;
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            quizList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                QuizBean quiz = new QuizBean();
                List<String> optionList = new ArrayList<>();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0 -> quiz.setKey((int) currentCell.getNumericCellValue());
                        case 1 -> {
                            String title=formatter.formatCellValue(currentCell);
                            quiz.setTitle(title);}
                        case 2 -> {
                            String question=formatter.formatCellValue(currentCell);
                            quiz.setQuestion(question);}
                        case 3, 4, 5, 6, 7 -> {
                            String opt= formatter.formatCellValue(currentCell);
                            // String opt=currentCell.getStringCellValue();
                            if(opt!=null && !opt.equals("")){
                                optionList.add(opt);
                            }
                        }
                        case 8 -> {
                            String answer= formatter.formatCellValue(currentCell);
                            // currentCell.getStringCellValue();
                            quiz.setAnswer(answer);}
                        default -> {break; }
                    }

                    cellIdx++;
                }
                quiz.setOptions(optionList);
                if (quiz.getKey() != 0) {
                    quizList.add(quiz);
                }
            }

            workbook.close();
            return CommonResponse.<List<QuizBean>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.QUIZ_CSV_UPLOAD)
                    .data(quizList)
                    .build();
        } catch (IOException e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<List<QuizBean>>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .message(Constant.FAILED_QUIZ_CSV_UPLOAD)
                    .data(quizList)
                    .build();
        }
    }



    public CommonResponse<Resource> downloadQuizCsv() {
        Resource resource = null;
        try {
            resource = new ClassPathResource("static/QuizTemplate.xlsx");
            return CommonResponse.<Resource>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.QUIZ_CSV_DOWNLOAD)
                    .data(resource)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Resource>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_QUIZ_CSV_DOWNLOAD)
                    .data(resource)
                    .build();
        }
    }

/*

    public Set<?> parseCsv(MultipartFile file){
        List<String> optionList =new ArrayList<>();
        try {
            Reader reader =new BufferedReader(new InputStreamReader(file.getInputStream()));
            HeaderColumnNameMappingStrategy<QuizBean> strategy=
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(QuizBean.class);
            CsvToBean<QuizBean> csvToBean=new CsvToBeanBuilder<QuizBean>()
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            csvToBean.parse()
                    .stream().map(
                            csvLine->
                                    Quiz.builder()
                                    .key(csvLine.getKey())

                    )
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}