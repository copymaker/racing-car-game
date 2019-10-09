package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderBoard {

    private final List<Record> records = new ArrayList<>();
    private Record totalRecord = Record.EMPTY_RECORD;

    void addRecord(Map<Car, Integer> map) {
        Record record = Record.from(map);
        records.add(record);
        totalRecord = totalRecord.merge(record);
    }

    public List<Car> findWinningCars() {
        return totalRecord.findLongestDistanceCars();
    }

    public Record getTotalRecord() {
        return totalRecord;
    }
}
