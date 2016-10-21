package py.blog.blog.entity;

import lombok.Data;

/**
 * Created by Administrator on 2016/10/12.
 */
@Data
public class MailMessage {
    private String name;
    private String email;
    private String subject;
    private String message;

}
