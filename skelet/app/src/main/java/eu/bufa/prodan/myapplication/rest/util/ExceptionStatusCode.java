package eu.bufa.prodan.myapplication.rest.util;

import com.ebs.psalms.rest.api_service.util.model.ErrorResponse;
import com.ebs.psalms.utils.constants.ErrorConstants;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ExceptionStatusCode {
    public static Boolean isHttp401Error(Throwable throwable) {
        return ((HttpException) throwable).code() == 401;
    }

    public static Boolean isHttp498Error(Throwable throwable){
        return ((HttpException) throwable).code() == 498;
    }

    public static Boolean isHttp502Exception(Throwable throwable){
        return ((HttpException) throwable).code() == 502;
    }


    public static boolean isTokenExpiredException(Throwable throwable) {
        boolean isTokenExpiredException = false;
        if (throwable instanceof HttpException && ExceptionStatusCode.isHttp498Error(throwable)) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            if (responseBody != null) {
                try {
                    ErrorResponse errorResponse = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                    if (errorResponse.getError().getMessage().equals(ErrorConstants.TOKEN_EXPIRED)) {
                        isTokenExpiredException = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return isTokenExpiredException;
    }

}
