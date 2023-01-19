package bid.service;

import bid.dto.BidMasterDto;
import bid.vo.BidMasterVo;

import java.util.LinkedList;
public interface BidService {
    LinkedList<BidMasterDto> selectBidMaster();
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    int insertBidTeukseong(LinkedList<BidMasterVo> bidMasterVoList);
}
