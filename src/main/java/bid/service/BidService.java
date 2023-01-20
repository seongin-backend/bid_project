package bid.service;

import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;

import java.util.LinkedList;
import java.util.List;

public interface BidService {
    List<BidMasterDto> selectBidMaster();
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    List<BidTeukseongDto> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(List<BidTeukseongVo> bidMasterVoList);
    int insertBidDetail(LinkedList<BidDetailVo> detailVoList);
}
