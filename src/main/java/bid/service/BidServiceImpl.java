package bid.service;

import bid.dao.*;
import bid.mapper.BidMapper;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class BidServiceImpl implements BidService{
    private final BidMapper bidMapper;

    public BidServiceImpl(BidMapper bidMapper) {
        this.bidMapper = bidMapper;
    }

    public BidMasterDao selectBidMasterList(HashMap<String, String> paramMap) {
        BidMasterDao bidMasterDao = bidMapper.selectBidMasterList(paramMap);
        return bidMasterDao;
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
    public List<BidTeukseongDao> selectBidTeukseongList(HashMap<String, String> paramMap) {
        LinkedList<BidTeukseongDao> bidTeukseongDaoList = bidMapper.selectBidTeukseongList(paramMap);
        return bidTeukseongDaoList;
    }
    public List<BidTeukseongDaoPivot> selectBidTeukseongListPivot(HashMap<String, String> paramMap) {
        LinkedList<BidTeukseongDaoPivot> bidTeukseongDtoListPivot = bidMapper.selectBidTeukseongListPivot(paramMap);
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
    public List<BidDetailDao> selectBidDetailList(HashMap<String, String> paramMap) {
        LinkedList<BidDetailDao> bidDetailDaos = bidMapper.selectBidDetailList(paramMap);
        return bidDetailDaos;
    }
    public List<BidDetailDaoPivot> selectBidDetailListPivot(HashMap<String, String> paramMap){
        LinkedList<BidDetailDaoPivot> bidDetailDtosPivot = bidMapper.selectBidDetailListPivot(paramMap);
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
