package com.lin.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by wenxuelin on 2016/11/22.
 */
public class UdfTest extends UDF {

    public void evaluate() {
        System.out.println("testing");
    }

    public float evaluate(float x, float y) {
        return x*y;
    }

}
