package bid.mapper;

import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface BidMapper {
    LinkedList<BidMasterDto> selectBidMaster();
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    LinkedList<BidTeukseongDto> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo);
    int insertBidTeukseong(BidTeukseongVo bidTeukseongVo);
    int insertBidDetail(BidDetailVo bidDetailVo);
}
