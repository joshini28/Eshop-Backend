package com.thejoshini.ESHOP.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private String resourcename;
    private String fieldname;
    private Long fieldvalue;
    private String name;
    public ResourceNotFoundException( String resourcename, String fieldname, Long fieldvalue) {
        super(String.format("%s not found with %s : %s",resourcename,fieldname,fieldvalue));
        this.resourcename = resourcename;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
    }

    public ResourceNotFoundException( String resourcename, String fieldname, String name) {
        super(String.format("%s not found with %s : %s",resourcename,fieldname,name));
        this.resourcename = resourcename;
        this.fieldname = fieldname;
        this.name=name;
    }
}

