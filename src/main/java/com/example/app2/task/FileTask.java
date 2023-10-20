package com.example.app2.task;

import com.example.app2.domain.vo.FileVO;
import com.example.app2.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileTask {
    private final FileService fileService;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkFiles(){
        log.warn("File Checking Task Run......");
        log.warn("============================");

        //어제 DB에 업로드된 파일 조회(file 객체)
        List<FileVO> filesOfYesterdayInDB = fileService.getFilesOfYesterday();

        //file객체를 path객체로 변경(어제 DB에 업로드된 파일들의 경로 조회)
        List<Path>pathsOfYesterdayInDB = filesOfYesterdayInDB.stream()
                .map(file -> Paths.get("C:/upload2", file.getFileUploadPath(), file.getFileUuid() + "_" + file.getFileName()))
                .collect(Collectors.toList());

        //path객체에 DB에 업로드된 파일들의 썸네일이미지 경로도 추가
        filesOfYesterdayInDB.stream()
                .filter(FileVO::isFileIsImage)
                .map(file -> Paths.get("C:/upload2", file.getFileUploadPath() + "t_" + file.getFileUuid() + "_" + file.getFileName()))
                .forEach(pathsOfYesterdayInDB::add);

        //실제 서버 경로(어제)에 있는 파일(upload2 폴더 속 어제경로에 저장되어있는 모든 파일)
        File filesInDirectory = Paths.get("C:/upload2", getUploadPathOfYesterday()).toFile();

        //실제 서버 경로에 있는 모든 파일을 DB에 업로드된 파일, 그 썸네일파일과  비교 후 이를 제외한 모든 파일을 삭제한다.
        if(filesInDirectory.listFiles() != null){
            Arrays.asList(filesInDirectory.listFiles(file -> !pathsOfYesterdayInDB.contains(file.toPath()))).forEach(File::delete);
        }
    }

    //오늘 날짜를 어제 날짜로 만들어서 리턴하는 메소드
    private String getUploadPathOfYesterday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        return simpleDateFormat.format(yesterday.getTime());
    }
}
