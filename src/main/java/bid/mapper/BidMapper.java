package bid.mapper;

import bid.dto.*;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface BidMapper {
    LinkedList<BidMasterDto> selectBidMasterList(BidMasterVo bidMasterVo);
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    LinkedList<BidTeukseongDto> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    LinkedList<BidTeukseongDtoPivot> selectBidTeukseongListPivot(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int insertBidDetail(BidDetailVo bidDetailVo);
    LinkedList<BidDetailDto> selectBidDetailList(BidDetailVo detailVo);
    LinkedList<BidDetailDtoPivot> selectBidDetailListPivot(BidDetailVo detailVo);
    int deleteBidDetail(BidDetailVo detailVo);
    int updateBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int updateBidDetail(BidDetailVo bidDetailVo);
}
