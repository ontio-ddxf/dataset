package com.github.ontio.dataset.bean;

import lombok.Data;

@Data
public class Result {
    public int error;
    public String desc;
    public Object result;

    public Result() {
    }

    public Result(int error, String desc, Object result) {
        this.error = error;
        this.desc = desc;
        this.result = result;
    }

}
