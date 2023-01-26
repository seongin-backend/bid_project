package bid.controller;

import bid.dto.BidDetailDto;
import bid.dto.BidDetailDtoPivot;
import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
import bid.service.BidService;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }
    @GetMapping("/bid/master/select")
    public ResponseEntity<?> selectBidMasterList(@RequestBody BidMasterVo bidMasterVo) {
        List<BidMasterDto> bidMasters = bidService.selectBidMasterList(bidMasterVo);
        return new ResponseEntity<List<BidMasterDto>>(bidMasters, HttpStatus.OK);
    }
    @PostMapping("/bid/master/insert")
    public ResponseEntity<?> insertBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.insertBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @PatchMapping("/bid/master/update")
    public ResponseEntity<?> updateBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.updateBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @DeleteMapping("/bid/master/delete")
    public ResponseEntity<?> deleteBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.deleteBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @GetMapping("/bid/teukseong/select")
    public ResponseEntity<?> selectBidTeukseongList(@RequestBody BidTeukseongVo bidTeukseongVo) {
        List<BidTeukseongDto> bidTeukseongVoList = bidService.selectBidTeukseongList(bidTeukseongVo);
        return new ResponseEntity<List<BidTeukseongDto>>(bidTeukseongVoList, HttpStatus.OK);
    }
    @PostMapping("/bid/teukseong/insert")
    public ResponseEntity<?> insertBidTeukseong(@RequestBody LinkedList<BidTeukseongVo> bidTeukseongVoList) {
        int result = 0;
        result = bidService.insertBidTeukseong(bidTeukseongVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @GetMapping("/bid/detail/select")
    public ResponseEntity<?> selectBidDetailList(@RequestBody BidDetailVo detailVo) {
        List<BidDetailDto> bidDetailDtos = bidService.selectBidDetailList(detailVo);
        return new ResponseEntity<List<BidDetailDto>>(bidDetailDtos, HttpStatus.OK);
    }
    @GetMapping("/bid/detail/select/pivot")
    public ResponseEntity<?> selectBidDetailListPivot(@RequestBody BidDetailVo detailVo) throws JsonProcessingException {
        List<BidDetailDtoPivot> bidDetailDtosPivot = bidService.selectBidDetailListPivot(detailVo);
        return new ResponseEntity<List<BidDetailDtoPivot>>(bidDetailDtosPivot, HttpStatus.OK);
    }
    @PostMapping("/bid/detail/insert")
    public ResponseEntity<?> insertBidDetail(@RequestBody LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        result = bidService.insertBidDetail(detailVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @DeleteMapping("/bid/detail/delete")
    public ResponseEntity<?> deleteBidDetail(@RequestBody BidDetailVo detailVo) {
        int result = 0;
        result = bidService.deleteBidDetail(detailVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
