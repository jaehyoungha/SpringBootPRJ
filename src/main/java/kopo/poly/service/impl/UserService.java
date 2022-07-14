package kopo.poly.service.impl;


import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IuserMapper;
import kopo.poly.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserService")
public class UserService implements IUserService {
    private final IuserMapper userMapper;

    public UserService(IuserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int InsertUserInfo(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "getInsertUser start !!");
        int res = userMapper.InsertUserInfo(pDTO);
        log.info(this.getClass().getName() + "getInsertUser start !!");
         return res;
    }




}
