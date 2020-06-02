package service.impl;

import model.EAction;
import model.Result;
import model.Transaction;
import service.ITranscationReport;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TranscationReportImpl implements ITranscationReport {

    @Override
    public List<Result> getPositions(List<Transaction> input) {
        //先按版本号排序，保证新增一定在前
        List<Transaction> transactionList = input.stream().sorted(Comparator.comparing(Transaction::getVersion)).collect(Collectors.toList());
        //以SecurityCode 为标识，创建一个队列，用于依次累计计算
        Map<String, Transaction> result = new LinkedHashMap<>();
        //每条记录动态进入计算队列
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction transaction = transactionList.get(i);
            //如果队列中已经有这个code 了，则获取这个code 的信息
            Transaction temp = null;
            if (result.containsKey(transaction.getSecurityCode())) {
                temp = result.get(transaction.getSecurityCode());
            }
            //新增的记录的版本号一定是1
            if (transaction.getActions() == EAction.Insert && transaction.getVersion() == 1) {
                //新增入队
                if (temp == null) {
                    result.put(transaction.getSecurityCode(), transaction);
                } else {
                    int quantity = temp.getQuantityValue() + transaction.getQuantityValue();
                    temp.setQuantity(quantity);
                }
            } else if (transaction.getVersion() > 1 && temp != null) {
                temp.setDirect(transaction.getDirect());
                temp.setVersion(transaction.getVersion());
                temp.setTradeID(transaction.getTradeID());
                temp.setTransactionID(transaction.getTransactionID());
                //取消动作把当前数量置为0
                if (transaction.getActions() == EAction.Cancel) {
                    temp.setQuantity(0);
                } else {
                    //修改动作将最新的修改记录覆盖到当前code 的低版本上
                    temp.setQuantity(transaction.getQuantity());
                }
            }
        }
        //计算完成，输出结果
        List<Result> output = new ArrayList<>();
        for (String key : result.keySet()) {
            Transaction transaction = result.get(key);
            Result temp = new Result();
            temp.setSecurityCode(transaction.getSecurityCode());
            temp.setQuantity(transaction.getQuantityValue());
            output.add(temp);
        }
        return output;
    }
}
