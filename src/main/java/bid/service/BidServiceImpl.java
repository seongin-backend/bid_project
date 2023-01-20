package bid.service;

import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
import bid.mapper.BidMapper;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

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
    public int insertBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.insertBidMaster(bidMasterVo);
        return result;
    }
    public int updateBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.updateBidMaster(bidMasterVo);
        return result;
    }
    public int deleteBidMaster(BidMasterVo bidMasterVo) {
        int result = bidMapper.deleteBidMaster(bidMasterVo);
        return result;
    }
    public List<BidTeukseongDto> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo) {
        LinkedList<BidTeukseongDto> bidTeukseongDtoList = bidMapper.selectBidTeukseongList(bidTeukseongVo);
        return bidTeukseongDtoList;
    }
    public int insertBidTeukseong(List<BidTeukseongVo> bidTeukseongVoList) {
        int result = 0;
        for (BidTeukseongVo bidTeukseongVo : bidTeukseongVoList) {
            bidMapper.insertBidTeukseong(bidTeukseongVo);
            result++;
        }
        return result;
    }
    public int insertBidDetail(LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        for (BidDetailVo bidDetailVo : detailVoList) {
            bidMapper.insertBidDetail(bidDetailVo);
            result++;
        }
        return result;
    }
}
