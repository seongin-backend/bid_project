package bid.controller;

import bid.dao.*;
import bid.service.BidService;
import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidRestController {
    private final BidService bidService;

    @GetMapping("/master")
    public ResponseEntity<?> selectBidMaster(@RequestParam HashMap<String, String> paramMap) {
        BidMasterDao bidMasters = bidService.selectBidMaster(paramMap);
        return new ResponseEntity<BidMasterDao>(bidMasters, HttpStatus.OK);
    }
    @PostMapping("/master")
    public ResponseEntity<?> insertBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.insertBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @PatchMapping("/master")
    public ResponseEntity<?> updateBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.updateBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @DeleteMapping("/master")
    public ResponseEntity<?> deleteBidMaster(@RequestBody BidMasterVo bidMasterVo) {
        int result = 0;
        result = bidService.deleteBidMaster(bidMasterVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @GetMapping("/teukseong")
    public ResponseEntity<?> selectBidTeukseongList(@RequestParam HashMap<String, String> paramMap) {
        List<BidTeukseongDao> bidTeukseongVoList = bidService.selectBidTeukseongList(paramMap);
        return new ResponseEntity<List<BidTeukseongDao>>(bidTeukseongVoList, HttpStatus.OK);
    }
    @GetMapping("/teukseong/pivot")
    public ResponseEntity<?> selectBidTeukseongListPivot(@RequestParam HashMap<String, String> paramMap) {
        List<BidTeukseongDaoPivot> bidTeukseongVoListPivot = bidService.selectBidTeukseongListPivot(paramMap);
        return new ResponseEntity<List<BidTeukseongDaoPivot>>(bidTeukseongVoListPivot, HttpStatus.OK);
    }
    @PostMapping("/teukseong")
    public ResponseEntity<?> insertBidTeukseong(@RequestBody LinkedList<BidTeukseongVo> bidTeukseongVoList) {
        int result = 0;
        result = bidService.insertBidTeukseong(bidTeukseongVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @PatchMapping("/teukseong")
    public ResponseEntity<?> updateBidTeukseong(@RequestBody LinkedList<BidTeukseongVo> bidTeukseongVoList) {
        int result = 0;
        result = bidService.updateBidTeukseong(bidTeukseongVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> selectBidDetailList(@RequestParam HashMap<String, String> paramMap) {
        List<BidDetailDao> bidDetailDaos = bidService.selectBidDetailList(paramMap);
        return new ResponseEntity<List<BidDetailDao>>(bidDetailDaos, HttpStatus.OK);
    }
    @GetMapping("/detail/pivot")
    public ResponseEntity<?> selectBidDetailListPivot(@RequestParam HashMap<String, String> paramMap) {
        List<BidDetailDaoPivot> bidDetailDtosPivot = bidService.selectBidDetailListPivot(paramMap);
        return new ResponseEntity<List<BidDetailDaoPivot>>(bidDetailDtosPivot, HttpStatus.OK);
    }
    @PostMapping("/detail")
    public ResponseEntity<?> insertBidDetail(@RequestBody LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        result = bidService.insertBidDetail(detailVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @PatchMapping("/detail")
    public ResponseEntity<?> updateBidDetail(@RequestBody LinkedList<BidDetailVo> detailVoList) {
        int result = 0;
        result = bidService.updateBidDetail(detailVoList);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    @DeleteMapping("/detail")
    public ResponseEntity<?> deleteBidDetail(@RequestBody BidDetailVo detailVo) {
        int result = 0;
        result = bidService.deleteBidDetail(detailVo);
        if(result < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
