package bid.service;

import bid.dto.BidMasterDto;
import bid.mapper.BidMapper;
import bid.vo.BidMasterVo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class BidServiceImpl implements BidService{

    private final BidMapper bidMapper;

    public BidServiceImpl(BidMapper bidMapper) {
        this.bidMapper = bidMapper;
    }

    public LinkedList<BidMasterDto> selectBidMaster() {
        LinkedList<BidMasterDto> bidMasters = bidMapper.selectBidMaster();
        return bidMasters;
    }

    @Override
    public int insertBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.insertBidMaster(bidMasterVo);
        return result;
    }

    @Override
    public int updateBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.updateBidMaster(bidMasterVo);
        return result;
    }

    @Override
    public int deleteBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.deleteBidMaster(bidMasterVo);
        return result;
    }

    @Override
    public int insertBidTeukseong(LinkedList<BidMasterVo> bidMasterVoList) {
        int result = 0;
        for (BidMasterVo bidMasterVo : bidMasterVoList) {
            bidMapper.insertBidTeukseong(bidMasterVo);
            result++;
        }
        return result;
    }
}
