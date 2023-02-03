package bid.mapper;

import bid.dto.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface BidMapper {
    LinkedList<BidMasterDao> selectBidMasterList(BidMasterVo bidMasterVo);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    LinkedList<BidTeukseongDao> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    LinkedList<BidTeukseongDaoPivot> selectBidTeukseongListPivot(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int insertBidDetail(BidDetailVo bidDetailVo);
    LinkedList<BidDetailDao> selectBidDetailList(BidDetailVo detailVo);
    LinkedList<BidDetailDaoPivot> selectBidDetailListPivot(BidDetailVo detailVo);
    int deleteBidDetail(BidDetailVo detailVo);
    int updateBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int updateBidDetail(BidDetailVo bidDetailVo);
}
