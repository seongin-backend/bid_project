package bid.mapper;

import bid.dao.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.LinkedList;

@Mapper
public interface BidMapper {
    BidMasterDao selectBidMaster(HashMap<String, String> paramMap);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    LinkedList<BidTeukseongDao> selectBidTeukseongList(HashMap<String, String> paramMap);
    LinkedList<BidTeukseongDaoPivot> selectBidTeukseongListPivot(HashMap<String, String> paramMap);
    int insertBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int insertBidDetail(BidDetailVo bidDetailVo);
    LinkedList<BidDetailDao> selectBidDetailList(HashMap<String, String> paramMap);
    LinkedList<BidDetailDaoPivot> selectBidDetailListPivot(HashMap<String, String> paramMap);
    int deleteBidDetail(BidDetailVo detailVo);
    int updateBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int updateBidDetail(BidDetailVo bidDetailVo);
}
