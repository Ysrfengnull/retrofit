package com.ysr.rf;

import com.ysr.rf.bean.RequestShipperName;
import com.ysr.rf.bean.ResultDetail;
import com.ysr.rf.retrofit.API;
import com.ysr.rf.retrofit.APIService;
import com.ysr.rf.retrofit.BaseRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/11.
 */
public class MainActivity {
    public static void main(String[] s) {
        String LogisticCode = "1000745320654";
        // String requestData = "{LogisticCode:"+LogisticCode+"}";
//        String requestData = "{'OrderCode':'','ShipperCode':'YD','LogisticCode':'3999043346251'}";
        String RequestData = "";
        String DataSign = "";
        try {
            RequestData = HttpUtils.urlEncoder(LogisticCode, "UTF-8");

            String dataSign = HttpUtils.encrypt(LogisticCode, API.AppKey, "UTF-8");
            
            DataSign = HttpUtils.urlEncoder(dataSign, "UTF-8");
            System.out.println("RequestData:"+RequestData.toString());
            System.out.println("DataSign:"+DataSign.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaseRetrofit.getInstance()
                .createReq(APIService.class)
                .searchData(RequestData, API.EBusinessID, 2002, 2, DataSign)
                .enqueue(new Callback<RequestShipperName>() {
                    @Override
                    public void onResponse(Call<RequestShipperName> call, Response<RequestShipperName> response) {
                        System.out.println("第一ok");
                    }

                    @Override
                    public void onFailure(Call<RequestShipperName> call, Throwable t) {
                        System.out.println("失败");
                    }
                });
//        BaseRetrofit.getInstance()
//                .createReq(APIService.class)
//                .searchDetailsData(RequestData, API.EBusinessID, 1002, 2, DataSign)
//                .enqueue(new Callback<ResultDetail>() {
//                    @Override
//                    public void onResponse(Call<ResultDetail> call, Response<ResultDetail> response) {
//                        System.out.println("第二ok");
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResultDetail> call, Throwable t) {
//                        System.out.println("失败");
//                    }
//                });
    }


}
