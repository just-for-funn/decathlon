package com.dwt.training;

import com.dwt.training.exports.FileExporter;
import com.dwt.training.exports.UserPosition;
import com.dwt.training.imports.EventScore;
import com.dwt.training.imports.FileImporter;
import com.dwt.training.imports.UserScore;

import java.util.List;
import java.util.stream.Collectors;

public class DecathlonConverter {
    FileImporter fileImporter;
    FileExporter fileExporter;
    String inFile;
    String outFile;


    public DecathlonConverter(FileImporter fileImporter, FileExporter fileExporter, String inFile, String outFile) {
        this.fileImporter = fileImporter;
        this.fileExporter = fileExporter;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    void convert(){
        List<UserPosition> positions =  mapToExportable(fileImporter.importFromFile(inFile));
        List<UserPosition> sorted = positions.stream()
                .sorted(this::sortByPozition)
                .collect(Collectors.toList());
        fileExporter.export(sorted , outFile );
    }

    private int sortByPozition(UserPosition u1, UserPosition u2) {
        return u1.getPosition().get(0) - u2.getPosition().get(0);
    }

    private List<UserPosition> mapToExportable(List<UserScore> importFromFile) {
        List<UserPosition> mapped = convertAll(importFromFile);
        setPositions(mapped);
        return mapped;
    }

    private List<UserPosition> convertAll(List<UserScore> importFromFile) {
        return importFromFile.stream().map(this::convert)
                .collect(Collectors.toList());
    }

    private void setPositions(List<UserPosition> mapped) {
        List<Integer> points = mapped.stream().map(UserPosition::getPoints).collect(Collectors.toList());

        PositionCalculator pc =  new PositionCalculator();

        List<List<Integer>> positions = pc.getPositions(points);

        for (int i = 0; i < mapped.size(); i++) {
            mapped.get(i).setPosition(positions.get(i));
        }
    }

    private UserPosition convert(UserScore userScore) {
        return UserPosition.builder()
                .user(userScore.getUser())
                .points(userScore.getTotalPoints())
                .scores(this.convertScore(userScore.getScores()))
                .build();
    }

    private List<UserPosition.EventAndScore> convertScore(List<EventScore> scores) {
        return scores.stream()
                .map(sc-> UserPosition.EventAndScore.builder().score(sc.getScore() ).event( sc.getEvent().getType()).build())
                .collect(Collectors.toList());
    }


}
