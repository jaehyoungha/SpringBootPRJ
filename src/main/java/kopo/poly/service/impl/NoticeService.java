package kopo.poly.service.impl;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.persistance.mapper.INoticeMapper;
import kopo.poly.service.INoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("NoticeService") //@을 걸고 서비스에 대한 이름을 정의해줘야만
public class NoticeService implements INoticeService {

    private final INoticeMapper noticeMapper;

    @Autowired // InticeMappe 라는걸 모름 알게 해주기 위해서 사용 resource와 같다고 생각하면됨
    public NoticeService(INoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper; // 여기서 객체로 만듬
    }

    @Override
    public int InsertNoticeInfo(NoticeDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "InsertNoticeInfo start !!");
        int res = noticeMapper.InsertNoticeInfo(pDTO);
        log.info(this.getClass().getName() + "InsertNoticeInfo end !!");
        return res;
    }
    @Override
    public List<NoticeDTO> getNoticeList() throws Exception {
        log.info(this.getClass().getName() + "getNoticeList start !");
        List<NoticeDTO> rList = noticeMapper.getNoticeList();
        log.info(this.getClass().getName() + "getNoticeList end !");
        return rList;
    }
    @Override
    public NoticeDTO getNoticeDetail(NoticeDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getNoticeDetail Start !!");
        NoticeDTO aDTO = noticeMapper.getNoticeDetail(pDTO);
        log.info(this.getClass().getName() + ".getNoticeDetail End !!");
        return aDTO;
    }

    @Override
    public int noticeDelete(NoticeDTO nDTO) throws Exception {
        log.info(this.getClass().getName() + ".noticeDelete ServiceStart !!");
        int res = noticeMapper.noticeDelete(nDTO);
        log.info(this.getClass().getName() + ".noticeDelete Service End !!");
        return res;
    }

    @Override
    public int noticeUpdate(NoticeDTO nDTO) throws Exception {
        int res = noticeMapper.noticeUpdate(nDTO);
        return res;
    }

}
