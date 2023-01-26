package bid.mapper;

import bid.dto.BidDetailDto;
import bid.dto.BidDetailDtoPivot;
import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
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
    int insertBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int insertBidDetail(BidDetailVo bidDetailVo);
    LinkedList<BidDetailDto> selectBidDetailList(BidDetailVo detailVo);
    LinkedList<BidDetailDtoPivot> selectBidDetailListPivot(BidDetailVo detailVo);
    int deleteBidDetail(BidDetailVo detailVo);
}
