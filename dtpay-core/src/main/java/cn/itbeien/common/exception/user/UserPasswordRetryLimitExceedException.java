package cn.itbeien.common.exception.user;



/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 错误最大次数异常类
 * Copyright© 2024 itbeien
 */
public class UserPasswordRetryLimitExceedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount, int lockTime)
    {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount, lockTime });
    }
}
