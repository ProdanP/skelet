package eu.bufa.prodan.myapplication.rest.util.listener;

import com.google.gson.Gson;

import java.io.IOException;

import eu.bufa.prodan.myapplication.mvp.BaseView;
import eu.bufa.prodan.myapplication.rest.util.ExceptionStatusCode;
import eu.bufa.prodan.myapplication.rest.util.interceptors.NoConnectivityException;
import eu.bufa.prodan.myapplication.rest.util.model.ErrorResponse;
import eu.bufa.prodan.myapplication.rest.util.model.FieldValidationError;
import eu.bufa.prodan.myapplication.utils.LogHelper;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * * if result is empty a Flowable will not emit any events , neither onNext, nor onError.
 * Use a Maybe instead of Flowable and ResponseMaybeListener to listen for emitted items.
 *
 * @param <T>
 */
public class ResponseFlowableListener<T> extends DisposableSubscriber<T> {
    private BaseView mBaseView;
    private boolean mDisplayError;
    private boolean mDisplayLoading;

    public ResponseFlowableListener(BaseView baseView, boolean mDisplayError, boolean mDisplayLoading) {
        mBaseView = baseView;
        this.mDisplayError = mDisplayError;
        this.mDisplayLoading = mDisplayLoading;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseView != null && mDisplayLoading)
            mBaseView.displayLoading(true);
    }

    @Override
    public void onNext(T response) {
        LogHelper.log("ResponseFlowableListener: ", "RESPONSE: " + response);
        if (response == null) {
            onError(new NullPointerException());
        }
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof NoConnectivityException) {
            if (mBaseView != null) {
                mBaseView.noInternetConnection();
            }
        } else if (t instanceof HttpException) {
            handleErrorMessage(t);
        } else {
            if (mBaseView != null) {
                // mBaseView.displayError(ErrorsLabels.getBadRequestError(),null) ;
            }
        }
        if (mBaseView != null) {
            mBaseView.displayLoading(false);
        }
    }

    @Override
    public void onComplete() {
        if (mBaseView != null)
            mBaseView.displayLoading(false);
        LogHelper.log("ResponseFlowableListener", "OnComplete");
    }

    private void handleErrorMessage(Throwable t) {
        ResponseBody responseBody = ((HttpException) t).response().errorBody();
        if (responseBody != null && !ExceptionStatusCode.isHttp502Exception(t)) {
            try {
                ErrorResponse errorResponse = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                if (mBaseView != null) {
                    if (errorResponse.getError() != null) {
                        String errorMessageConstant = errorResponse.getError().getMessage();
                       /* if(errorMessageConstant.equals(ErrorConstants.NO_USER_PRESENT) ||
                                errorMessageConstant.equals(ErrorConstants.INVALID_TOKEN)){
                            mBaseView.noUserErrorLogout();
                        } else if(mDisplayError)
                            mBaseView.displayError(ErrorsLabels.getErrorMessageByKey(errorMessageConstant), null);*/
                    } else if (errorResponse.getFieldValidationErrors() != null) {
                        //for (FieldValidationError error : errorResponse.getFieldValidationErrors())
                        // mBaseView.displayError(ErrorsLabels.getErrorMessageByKey(error.getMessages().get(0)),error.getField().get(0));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (mBaseView != null) {
                // mBaseView.displayError(ErrorsLabels.getBadRequestError(),null) ;
            }
        }
    }
}
