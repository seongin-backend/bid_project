package bid.mapper;

import bid.dto.BidMasterDto;
import bid.vo.BidMasterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface BidMapper {
    LinkedList<BidMasterDto> selectBidMaster();
    int insertBidMaster(BidMasterVo bidMasterVo);
    int updateBidMaster(BidMasterVo bidMasterVo);
    int deleteBidMaster(BidMasterVo bidMasterVo);
    int insertBidTeukseong(BidMasterVo bidMasterVo);
}
