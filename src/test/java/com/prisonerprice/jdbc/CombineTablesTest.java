package com.prisonerprice.jdbc;

import com.prisonerprice.model.CombineTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CombineTablesTest {
    private CombineTables combineTables;

    @Before
    public void init(){
        combineTables = new CombineTables();
    }

    @Test
    public void getCombineTablesTest(){
        List<CombineTable> list = combineTables.getCombineTables();
        int exceptionNum = 9;

        for(CombineTable combineTable : list){
            System.out.println(combineTable);
        }

        Assert.assertEquals(exceptionNum, list.size());
    }
}
