package cn.itbeien.terminal.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception
{
    private String code;
    private String msg;

    public BusinessException(String code ,String msg){
        super(code+":"+msg);
    }


}
