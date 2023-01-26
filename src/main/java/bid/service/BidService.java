package bid.service;

import bid.dto.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.LinkedList;
import java.util.List;

public interface BidService {
    List<BidMasterDto> selectBidMasterList(BidMasterVo bidMasterVo);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    List<BidTeukseongDto> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    List<BidTeukseongDtoPivot> selectBidTeukseongListPivot(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(List<BidTeukseongVo> bidMasterVoList);
    int updateBidTeukseong(LinkedList<BidTeukseongVo> bidTeukseongVoList);
    List<BidDetailDto> selectBidDetailList(BidDetailVo detailVo);
    List<BidDetailDtoPivot> selectBidDetailListPivot(BidDetailVo detailVo);
    int insertBidDetail(LinkedList<BidDetailVo> detailVoList);
    int updateBidDetail(LinkedList<BidDetailVo> detailVoList);
    int deleteBidDetail(BidDetailVo detailVo);

}
