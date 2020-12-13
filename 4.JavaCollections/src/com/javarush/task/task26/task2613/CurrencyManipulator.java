package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode.toUpperCase();
    }

    public void addAmount(int denomination, int count) {
        denominations.merge(denomination, count, Integer::sum);
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream().map(x -> (x.getKey() * x.getValue())).mapToInt(a -> a).sum();
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        List<Integer> denominationsList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                denominationsList.add(entry.getKey());
            }
        }
        denominationsList.sort((x1, x2) -> (x2 - x1));
        List<Integer> listBanknotes = getListBanknotes(expectedAmount, denominationsList);

        if (!listBanknotes.isEmpty()) {
            Map<Integer, Integer> resultWithdrawAmountMap = new TreeMap<>(Collections.reverseOrder());
            for (Integer i : listBanknotes) {
                int values = denominations.get(i) - 1;
                if (values == 0) {
                    denominations.remove(i);
                } else {
                    denominations.put(i, values);
                }
                if (resultWithdrawAmountMap.containsKey(i)) {
                    resultWithdrawAmountMap.put(i, resultWithdrawAmountMap.get(i) + 1);
                } else {
                    resultWithdrawAmountMap.put(i, 1);
                }
            }
            return resultWithdrawAmountMap;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    private List<Integer> getListBanknotes(int expectedAmount, List<Integer> denominationsList) {
        int newExpectedAmount = expectedAmount;
        List<Integer> newDenominationsList = new ArrayList<>();
        newDenominationsList.addAll(denominationsList);
        List<Integer> resultWithdrawAmountList = new ArrayList<>();
        List<Integer> withdrawAmountList = new ArrayList<>();
        int oldBanknote = 0;
        for (int i = 0; i < newDenominationsList.size();) {
            if (newDenominationsList.get(i) > newExpectedAmount) {
                newDenominationsList.remove(i);
            } else {
                int banknote;
                do {
                    banknote = newDenominationsList.remove(i);
                } while (banknote == oldBanknote && newDenominationsList.size() > 0);
                oldBanknote = banknote;
                withdrawAmountList.add(banknote);
                newExpectedAmount -= banknote;
                if (newExpectedAmount == 0 && (withdrawAmountList.size() < resultWithdrawAmountList.size() || resultWithdrawAmountList.isEmpty())) {
                    resultWithdrawAmountList.clear();
                    resultWithdrawAmountList.addAll(withdrawAmountList);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.addAll(newDenominationsList);
                    List<Integer> list = getListBanknotes(newExpectedAmount, newList);
                    if (!list.isEmpty()) {
                        withdrawAmountList.addAll(list);
                        if (withdrawAmountList.size() < resultWithdrawAmountList.size() || resultWithdrawAmountList.isEmpty()) {
                            resultWithdrawAmountList.clear();
                            resultWithdrawAmountList.addAll(withdrawAmountList);
                        }
                    }
                }
                newExpectedAmount += withdrawAmountList.remove(withdrawAmountList.size() - 1);
            }
        }
        return resultWithdrawAmountList;
    }
}
