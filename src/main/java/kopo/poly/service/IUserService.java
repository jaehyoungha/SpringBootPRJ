package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

public interface IUserService {
    int InsertUserInfo(UserInfoDTO pDTO) throws Exception;
}
