package service.impl;


import model.EAction;
import model.EDirect;
import model.Result;
import model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.ITranscationReport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TranscationReportImplTest {


    /**
     * 6条记录
     */
    @Test
    public void getPositions() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionID(1);
        transaction1.setTradeID(1);
        transaction1.setVersion(1);
        transaction1.setSecurityCode("REL");
        transaction1.setQuantity(50);
        transaction1.setActions(EAction.Insert);
        transaction1.setDirect(EDirect.Buy);
        transactionList.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionID(2);
        transaction2.setTradeID(2);
        transaction2.setVersion(1);
        transaction2.setSecurityCode("ITC");
        transaction2.setQuantity(40);
        transaction2.setActions(EAction.Insert);
        transaction2.setDirect(EDirect.Sell);
        transactionList.add(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setTransactionID(3);
        transaction3.setTradeID(3);
        transaction3.setVersion(1);
        transaction3.setSecurityCode("INF");
        transaction3.setQuantity(70);
        transaction3.setActions(EAction.Insert);
        transaction3.setDirect(EDirect.Buy);
        transactionList.add(transaction3);


        Transaction transaction4 = new Transaction();
        transaction4.setTransactionID(4);
        transaction4.setTradeID(1);
        transaction4.setVersion(2);
        transaction4.setSecurityCode("REL");
        transaction4.setQuantity(60);
        transaction4.setActions(EAction.Update);
        transaction4.setDirect(EDirect.Buy);
        transactionList.add(transaction4);


        Transaction transaction5 = new Transaction();
        transaction5.setTransactionID(5);
        transaction5.setTradeID(2);
        transaction5.setVersion(2);
        transaction5.setSecurityCode("ITC");
        transaction5.setQuantity(30);
        transaction5.setActions(EAction.Cancel);
        transaction5.setDirect(EDirect.Buy);
        transactionList.add(transaction5);

        Transaction transaction6 = new Transaction();
        transaction6.setTransactionID(6);
        transaction6.setTradeID(4);
        transaction6.setVersion(1);
        transaction6.setSecurityCode("INF");
        transaction6.setQuantity(20);
        transaction6.setActions(EAction.Insert);
        transaction6.setDirect(EDirect.Sell);
        transactionList.add(transaction6);
        System.out.println("transaction 1~6:");
        outputParams(transactionList);
        ITranscationReport report = new TranscationReportImpl();
        final List<Result> positions = report.getPositions(transactionList);
        outputvalue(positions);
    }

    /**
     * 两条记录
     */
    @Test
    public void getPositions2() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionID(1);
        transaction1.setTradeID(1);
        transaction1.setVersion(1);
        transaction1.setSecurityCode("REL");
        transaction1.setQuantity(50);
        transaction1.setActions(EAction.Insert);
        transaction1.setDirect(EDirect.Buy);
        transactionList.add(transaction1);
        ITranscationReport report = new TranscationReportImpl();


        Transaction transaction2 = new Transaction();
        transaction2.setTransactionID(2);
        transaction2.setTradeID(2);
        transaction2.setVersion(1);
        transaction2.setSecurityCode("ITC");
        transaction2.setQuantity(40);
        transaction2.setActions(EAction.Insert);
        transaction2.setDirect(EDirect.Sell);
        transactionList.add(transaction2);
        System.out.println("transaction 1、2:");
        outputParams(transactionList);

        final List<Result> positions = report.getPositions(transactionList);

        outputvalue(positions);
    }

    /**
     * 只有一条记录
     */
    @Test
    public void getPositions1() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionID(1);
        transaction1.setTradeID(1);
        transaction1.setVersion(1);
        transaction1.setSecurityCode("REL");
        transaction1.setQuantity(50);
        transaction1.setActions(EAction.Insert);
        transaction1.setDirect(EDirect.Buy);
        transactionList.add(transaction1);
        ITranscationReport report = new TranscationReportImpl();
        System.out.println("transaction 1:");
        outputParams(transactionList);
        final List<Result> positions = report.getPositions(transactionList);

        outputvalue(positions);
    }

    private void outputParams(List<Transaction> transactionList) {
        System.out.println("Input parameter :");
        System.out.println("  TransactionID | TradeID | Version | SecurityCode | Quantity | Insert/Update/Cancel | Buy/Sell");
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction temp = transactionList.get(i);
            System.out.println(String.format("  %s | %s | %d | %s | %d | %s | %s |", temp.getTransactionID(), temp.getTradeID(),
                    temp.getVersion(), temp.getSecurityCode(), temp.getQuantity(), temp.getActions(), temp.getDirect()
            ));
        }
    }

    private void outputvalue(List<Result> positions) {
        System.out.println("Output :");
        for (int i = 0; i < positions.size(); i++) {
            System.out.println(String.format("  | %s  | %s%d|", positions.get(i).getSecurityCode(), positions.get(i).getQuantity() > 0 ? "+" : "", positions.get(i).getQuantity()));
        }
        System.out.println("/******************************output end*******************************************/");
    }
}
