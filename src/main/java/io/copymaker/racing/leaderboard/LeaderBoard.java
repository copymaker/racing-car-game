package io.copymaker.racing.leaderboard;

import io.copymaker.racing.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderBoard {

    private final List<Record> records = new ArrayList<>();
    private Record totalRecord = Record.EMPTY_RECORD;

    public void addRecord(Map<Car, Integer> map) {
        Record record = Record.from(map);
        records.add(record);
        totalRecord = totalRecord.merge(record);
    }

    public List<Car> findWinningCars() {
        return totalRecord.findLongestDistanceCars();
    }

    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    public Record getTotalRecord() {
        return totalRecord;
    }
}
