package kopo.poly.persistance.mapper;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IuserMapper {
    int InsertUserInfo(UserInfoDTO pDTO) throws Exception;
}
