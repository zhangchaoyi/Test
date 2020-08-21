package excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/8/21 10:41
 */
@Data
public class WorkbenchUser implements Serializable {

    private static final long serialVersionUID = 6983756488927896742L;

    private String id;

    private String userName;

    private String userRealName;

    private String email;

    private Set<String> roleNames;

    private Set<String> resourceNames;

}
