package service;

import model.Result;
import model.Transaction;

import java.util.List;

public interface ITranscationReport {
    List<Result> getPositions(List<Transaction> transactionList);
}
