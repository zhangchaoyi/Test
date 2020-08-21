package excel;

import lombok.Data;

import java.io.Serializable;

/**
 * 导出数据的原始记录
 * @Author: chaoyi.zhang
 * @Date: 2020/8/21 10:41
 */
@Data
public class WorkbenchUserRecord implements Serializable {

    private static final long serialVersionUID = 9005282495876600348L;
    private String id;

    private String userName;

    private String userRealName;

    private String email;

    private String roleCode;

    private String roleName;

    private String resourceCode;

    private String resourceName;

    private String resourceType;

    private String url;
}
