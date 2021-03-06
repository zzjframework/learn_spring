package com.dongz.hrm.domain.system;

import com.dongz.hrm.common.entities.BaseEntity;
import com.dongz.hrm.common.enums.EnableState;
import com.dongz.hrm.common.enums.FormOfEmployment;
import com.dongz.hrm.common.enums.LevelState;
import com.dongz.hrm.common.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dong
 * @date 2020/2/6 15:35
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8912714873258130287L;
    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    @Convert(converter = EnableState.MyConverter.class)
    private EnableState enableState;

    private Long companyId;

    private String companyName;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 入职时间
     */
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    @Convert(converter = FormOfEmployment.MyConverter.class)
    private FormOfEmployment formOfEmployment;

    /**
     * 工号
     */
    private String workNumber;

    /**
     * 管理形式
     */
    private String formOfManagement;

    /**
     * 工作城市
     */
    private String workingCity;

    /**
     * 转正时间
     */
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    @Convert(converter = ServiceStatus.MyConverter.class)
    private ServiceStatus serviceStatus;

    /**
     * 离职时间
     */
    private Date exitTime;

    private String departmentName;

    /**
     * 用户权限等级
     */
    @Convert(converter = LevelState.MyConverter.class)
    private LevelState level;
}
