package bid.controller;

import bid.dto.BidMasterDto;
import bid.dto.BidTeukseongDto;
import bid.service.BidService;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
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
    @GetMapping("/bid/select")
    public ResponseEntity<?> selectBid() {
        List<BidMasterDto> bidMasters = bidService.selectBidMaster();
        return new ResponseEntity<List<BidMasterDto>>(bidMasters, HttpStatus.OK);
    }
    @PostMapping("/bid/insert")
    public ResponseEntity<?> insertBid(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.insertBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @PatchMapping("/bid/update")
    public ResponseEntity<?> updateBid(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.updateBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @DeleteMapping("/bid/delete")
    public ResponseEntity<?> deleteBid(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.deleteBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @GetMapping("/bid/teukseong/select")
    public ResponseEntity<?> selectBidTeukseong(@RequestBody BidTeukseongVo bidTeukseongVo) {
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
    @PostMapping("/bid/detail/insert")
    public ResponseEntity<?> insertBidDetail(@RequestBody LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        result = bidService.insertBidDetail(detailVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
