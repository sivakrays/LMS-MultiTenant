package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.QuizBean;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
<<<<<<< HEAD
    public ResponseEntity<?> saveBadge(QuizRank quizRank) {
=======
    public CommonResponse<BadgeCounts> saveBadge(QuizRank quizRank) {
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        Long userId = quizRank.getUserId();
        UUID subSectionId = quizRank.getSubSectionId();
        Integer energyPoints = quizRank.getEnergyPoints();
        int badge =quizRank.getBadge();
        Optional<QuizRank> obj = quizRankRepository.findByUserIdAndSubSectionId(userId, subSectionId);
<<<<<<< HEAD
        if (obj.isPresent()) {
            QuizRank quizRank1 = obj.get();
            quizRank1.setEnergyPoints(energyPoints);
            quizRank1.setBadge(badge);
            quizRankRepository.save(quizRank1);
            BadgeCounts data= getBadgeCountsForUser(userId,energyPoints);
            return ResponseEntity.status(HttpStatus.OK).body(data);
=======
        BadgeCounts data = null;
        try {
            if (obj.isPresent()) {
                QuizRank quizRank1 = obj.get();
                quizRank1.setEnergyPoints(energyPoints);
                quizRank1.setBadge(badge);
                quizRankRepository.save(quizRank1);
                data = getBadgeCountsForUser(userId, energyPoints);
                return CommonResponse.<BadgeCounts>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.BADGE_UPDATED)
                        .data(data)
                        .build();
            } else {
                quizRankRepository.save(quizRank);
                BadgeCounts data1 = getBadgeCountsForUser(userId, energyPoints);
                return CommonResponse.<BadgeCounts>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.BADGE_SAVED)
                        .data(data1)
                        .build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<BadgeCounts>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_BADGE)
                    .data(data)
                    .build();
        }
    }
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795

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

<<<<<<< HEAD
    public ResponseEntity<?> uploadQuizCsv(MultipartFile file) throws IOException {
=======
    public CommonResponse<List<QuizBean>> uploadQuizCsv(MultipartFile file) {
        List<QuizBean> quizList = null;
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<QuizBean> quizList = new ArrayList<>();

        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

<<<<<<< HEAD
            Iterator<Cell> cellsInRow = currentRow.iterator();

            QuizBean quiz = new QuizBean();
            List<String> optionList=new ArrayList<>();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                switch (cellIdx) {
                    case 0 -> quiz.setKey((int) currentCell.getNumericCellValue());
                    case 1 -> quiz.setTitle(currentCell.getStringCellValue());
                    case 2 -> quiz.setQuestion(currentCell.getStringCellValue());
                    case 3, 4, 5, 6, 7 -> {
                        String opt=currentCell.getStringCellValue();
                        if(opt!=null && !opt.equals("")){
                            optionList.add(opt);
                        }
                    }
                    case 8 -> quiz.setAnswer(currentCell.getStringCellValue());
                    default -> {break; }
                }


                cellIdx++;
            }
            quiz.setOptions(optionList);
            if (quiz.getKey()!=0) {
                quizList.add(quiz);
            }
=======
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
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_QUIZ_CSV_UPLOAD)
                    .data(quizList)
                    .build();
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        }

        workbook.close();


    return ResponseEntity.ok(quizList);
    }

    public ResponseEntity<?> downloadQuizCsv() throws MalformedURLException {
        Resource resource=null;
    //  File file= new File("resources/projectResources/QuizTemplate.xlsx");
try {
    resource=new ClassPathResource("static/QuizTemplate.xlsx");
}catch (Exception e) {
    return ResponseEntity.ok(e.getMessage());
}
        return ResponseEntity.ok(resource);

<<<<<<< HEAD
=======

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
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
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
