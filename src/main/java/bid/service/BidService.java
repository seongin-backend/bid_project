package bid.service;

import bid.dto.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;

import java.util.LinkedList;
import java.util.List;

public interface BidService {
    List<BidMasterDao> selectBidMasterList(BidMasterVo bidMasterVo);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    List<BidTeukseongDao> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    List<BidTeukseongDaoPivot> selectBidTeukseongListPivot(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(List<BidTeukseongVo> bidMasterVoList);
    int updateBidTeukseong(LinkedList<BidTeukseongVo> bidTeukseongVoList);
    List<BidDetailDao> selectBidDetailList(BidDetailVo detailVo);
    List<BidDetailDaoPivot> selectBidDetailListPivot(BidDetailVo detailVo);
    int insertBidDetail(LinkedList<BidDetailVo> detailVoList);
    int updateBidDetail(LinkedList<BidDetailVo> detailVoList);
    int deleteBidDetail(BidDetailVo detailVo);

}
