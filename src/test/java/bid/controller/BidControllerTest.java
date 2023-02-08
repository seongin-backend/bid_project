package bid.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 입찰_마스터_조회() throws Exception {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("guraeDate", "20230126");
        stringStringHashMap.put("baljunkiCompanyCode", "00001");
        stringStringHashMap.put("baljunkiGubnCode", "01");
        stringStringHashMap.put("baljunkiId", "BAJUNKI001");
        ResultActions resultActions = mockMvc.perform(
                get("/bid/master")
                        .param("guraeDate", stringStringHashMap.get("guraeDate"))
                        .param("baljunkiCompanyCode", stringStringHashMap.get("baljunkiCompanyCode"))
                        .param("baljunkiGubnCode", stringStringHashMap.get("baljunkiGubnCode"))
                        .param("baljunkiId", stringStringHashMap.get("baljunkiId"))
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidMasterList"))
                .andExpect(jsonPath("$.bidId", is("20230126_00001_01_BAJUNKI001"))) //PK채번 테스트
                .andExpect(jsonPath("$.guraeDate", is(stringStringHashMap.get("guraeDate"))))
                .andExpect(jsonPath("$.baljunkiCompanyCode", is(stringStringHashMap.get("baljunkiCompanyCode"))))
                .andExpect(jsonPath("$.baljunkiGubnCode", is(stringStringHashMap.get("baljunkiGubnCode"))))
                .andExpect(jsonPath("$.baljunkiId", is(stringStringHashMap.get("baljunkiId"))))

                .andExpect(jsonPath("$.teukiRemk", is("특이사항")))
                .andExpect(jsonPath("$.baljunkiCompanySign", is("발전기회사서명이미지주소")))
                .andExpect(jsonPath("$.submitDate").exists())
                .andExpect(jsonPath("$.submitTime").exists())
                .andExpect(jsonPath("$.submitEmplNumb", is("vtw1606")))
                .andExpect(jsonPath("$.submitEmplSign", is("접수자서명이미지주소")))
        ;
    }

    @Test
    void 입찰_특성_조회() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/teukseong")
                        .param("bidId", bidId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidTeukseongList")) 
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuGubnCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuGubnName", is("열간 기동소요시간")))
                .andExpect(jsonPath("$[0].submitVal", is("8")))
                .andExpect(jsonPath("$[0].updateRemk", is("시간 더 걸림")))
        ;
    }

    @Test
    void 입찰_특성_조회_피벗() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/teukseong/pivot")
                        .param("bidId", bidId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidTeukseongListPivot"))
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].submit_VAL_01_01", is("8")))
                .andExpect(jsonPath("$[0].submit_VAL_01_02", is("3")))
                .andExpect(jsonPath("$[0].update_REMK_01_01", is("시간 더 걸림")))
                .andExpect(jsonPath("$[0].update_REMK_01_02", is("시간 줄음")))
        ;
    }

    @Test
    void 입찰_디테일_조회() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        String bidGubnCode = "01";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/detail")
                        .param("bidId", bidId)
                        .param("bidGubnCode", bidGubnCode)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidDetailList"))
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].bidGubnCode", is(bidGubnCode))) //pk
                .andExpect(jsonPath("$[0].guraeTimeGubnCode").exists()) //pk
                .andExpect(jsonPath("$[0].guraeTime").exists()) //pk
                .andExpect(jsonPath("$[0].gubnCode").exists()) //pk
                .andExpect(jsonPath("$[0].gubnName").exists())
                .andExpect(jsonPath("$[0].submitVal").exists())
        ;
    }

    @Test
    void 입찰_디테일_조회_피벗() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        String bidGubnCode = "01";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/detail/pivot")
                        .param("bidId", bidId)
                        .param("bidGubnCode", bidGubnCode)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidDetailListPivot"))
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].bidGubnCode", is(bidGubnCode))) //pk
                .andExpect(jsonPath("$[0].gubnCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].gubnName", is("공급가능용량")))
                .andExpect(jsonPath("$[0].d_1", is("100")))
                .andExpect(jsonPath("$[0].d_2", is("200")))
                .andExpect(jsonPath("$[0].d_3", is("300")))
        ;
    }


}

