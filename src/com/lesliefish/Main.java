package com.lesliefish;

import com.lesliefish.test01firstexample.FirstExample;
import com.lesliefish.test02datetime.DateTimeTester;
import com.lesliefish.test03transactions.TransactionsTester;
import com.lesliefish.test04batching.BatchingTester;
import com.lesliefish.test05preparedstatement.PreparedStatementTester;

public class Main {

    public static void main(String[] args){
        // FirstExample.test();
        // DateTimeTester.test();
        // TransactionsTester.test();
        // BatchingTester.test();
        PreparedStatementTester.test();
    }
}
