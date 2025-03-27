package cn.itbeien.common.exception;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 业务异常
 * 业务异常
 * Copyright© 2024 itbeien
 */
public final class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     *
     * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public BusinessException()
    {
    }

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

    public BusinessException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public BusinessException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}