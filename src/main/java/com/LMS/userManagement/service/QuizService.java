package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.QuizBean;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
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

    public ResponseEntity<?> uploadQuizCsv(MultipartFile file) throws IOException {
        DataFormatter formatter = new DataFormatter();
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

            Iterator<Cell> cellsInRow = currentRow.iterator();

            QuizBean quiz = new QuizBean();
            List<String> optionList=new ArrayList<>();

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
            if (quiz.getKey()!=0) {
                quizList.add(quiz);
            }
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

    }

/*
    public Set<?> parseCsv(MultipartFile file){
        try {
            Reader reader =new BufferedReader(new InputStreamReader(file.getInputStream()));
            HeaderColumnNameMappingStrategy<QuizCsvRepresentation> strategy=
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(QuizCsvRepresentation.class);
            CsvToBean<QuizCsvRepresentation> csvToBean=
                    new CsvToBeanBuilder<QuizCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
          return   csvToBean.parse()
                    .stream().map(
                            csvLine->
                                    QuizData.builder()
                                            .key(csvLine.getKey())
                                            .title(csvLine.getTitle())
                                            .question(csvLine.getQuestion())
                                            .option1(csvLine.getOption1())
                                            .option2(csvLine.getOption2())
                                            .option3(csvLine.getOption3())
                                            .option4(csvLine.getOption4())
                                            .option5(csvLine.getOption5())
                                            .build()

                    )
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> uploadCommonCsv(MultipartFile file) {
     Set<?> quiz=   parseCsv(file);
     return ResponseEntity.ok(quiz);
    }*/
}
