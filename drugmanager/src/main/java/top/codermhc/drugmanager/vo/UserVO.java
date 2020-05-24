package top.codermhc.drugmanager.vo;

import lombok.Data;
import top.codermhc.drugmanager.base.entity.Department;
import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;

@Data
public class UserVO {

    private User user;

    private UserAuthentication userAuthentication;

    private Role role;

    private Department department;

}
