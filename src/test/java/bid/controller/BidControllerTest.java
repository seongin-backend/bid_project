package bid.controller;

import bid.vo.BidMasterVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BidControllerTest {

    private static final BidMasterVo bidMasterVo = new BidMasterVo("", "20230126", "00001"
                                                        , "01", "BAJUNKI001", "", ""
                                                        , "", "", "", "");
    private static final Map<String, String> masterParamMap = new HashMap<>() {
        {
            put("guraeDate", "20230126");
            put("baljunkiCompanyCode", "00001");
            put("baljunkiGubnCode", "01");
            put("baljunkiId", "BAJUNKI001");
        }
    };

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 입찰_마스터_조회() throws Exception {
        ResultActions resultActions;
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(masterParamMap);

        resultActions = mockMvc.perform(
                get("/bid/master")
                        .param(string)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("findAll"))
        ;
    }

}
