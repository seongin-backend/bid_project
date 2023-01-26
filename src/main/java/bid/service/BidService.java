package bid.service;

import bid.dto.BidDetailDto;
import bid.dto.BidDetailDtoPivot;
import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
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
    int insertBidTeukseong(List<BidTeukseongVo> bidMasterVoList);
    int insertBidDetail(LinkedList<BidDetailVo> detailVoList);
    List<BidDetailDto> selectBidDetailList(BidDetailVo detailVo);
    List<BidDetailDtoPivot> selectBidDetailListPivot(BidDetailVo detailVo) throws JsonProcessingException;
    int deleteBidDetail(BidDetailVo detailVo);
}
