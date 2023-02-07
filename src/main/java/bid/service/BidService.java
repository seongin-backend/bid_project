package bid.service;

import bid.dao.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface BidService {
    BidMasterDao selectBidMasterList(HashMap<String, String> paramMap);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    List<BidTeukseongDao> selectBidTeukseongList(HashMap<String, String> paramMap);
    List<BidTeukseongDaoPivot> selectBidTeukseongListPivot(HashMap<String, String> paramMap);
    int insertBidTeukseong(List<BidTeukseongVo> bidMasterVoList);
    int updateBidTeukseong(LinkedList<BidTeukseongVo> bidTeukseongVoList);
    List<BidDetailDao> selectBidDetailList(HashMap<String, String> paramMap);
    List<BidDetailDaoPivot> selectBidDetailListPivot(HashMap<String, String> paramMap);
    int insertBidDetail(LinkedList<BidDetailVo> detailVoList);
    int updateBidDetail(LinkedList<BidDetailVo> detailVoList);
    int deleteBidDetail(BidDetailVo detailVo);

}
