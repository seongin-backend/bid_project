package bid.service;

import bid.dto.*;
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

    public LinkedList<BidMasterDao> selectBidMasterList(BidMasterVo bidMasterVo) {
        LinkedList<BidMasterDao> bidMasters = bidMapper.selectBidMasterList(bidMasterVo);
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
    public List<BidTeukseongDao> selectBidTeukseongList(BidTeukseongVo bidTeukseongVo) {
        LinkedList<BidTeukseongDao> bidTeukseongDaoList = bidMapper.selectBidTeukseongList(bidTeukseongVo);
        return bidTeukseongDaoList;
    }
    public List<BidTeukseongDaoPivot> selectBidTeukseongListPivot(BidTeukseongVo bidTeukseongVo) {
        LinkedList<BidTeukseongDaoPivot> bidTeukseongDtoListPivot = bidMapper.selectBidTeukseongListPivot(bidTeukseongVo);
        return bidTeukseongDtoListPivot;
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
    public int updateBidDetail(LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        for (BidDetailVo bidDetailVo : detailVoList) {
            bidMapper.updateBidDetail(bidDetailVo);
            result++;
        }
        return result;
    }
    public List<BidDetailDao> selectBidDetailList(BidDetailVo detailVo) {
        LinkedList<BidDetailDao> bidDetailDaos = bidMapper.selectBidDetailList(detailVo);
        return bidDetailDaos;
    }
    public List<BidDetailDaoPivot> selectBidDetailListPivot(BidDetailVo detailVo){
        LinkedList<BidDetailDaoPivot> bidDetailDtosPivot = bidMapper.selectBidDetailListPivot(detailVo);
        return bidDetailDtosPivot;
    }
    public int deleteBidDetail(BidDetailVo detailVo) {
        int result = bidMapper.deleteBidDetail(detailVo);
        return result;
    }
    public int updateBidTeukseong(LinkedList<BidTeukseongVo> bidTeukseongVoList) {
        int result = 0;
        for (BidTeukseongVo bidTeukseongVo : bidTeukseongVoList) {
            bidMapper.updateBidTeukseong(bidTeukseongVo);
            result++;
        }
        return result;
    }
}
