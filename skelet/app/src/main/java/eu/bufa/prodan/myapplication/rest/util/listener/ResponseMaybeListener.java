package eu.bufa.prodan.myapplication.rest.util.listener;

import com.google.gson.Gson;

import java.io.IOException;

import eu.bufa.prodan.myapplication.mvp.BaseView;
import eu.bufa.prodan.myapplication.rest.util.model.ErrorResponse;
import eu.bufa.prodan.myapplication.utils.LogHelper;
import io.reactivex.observers.DisposableMaybeObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Used when api don't return value aka empty response - 204 code
 * This will trigger Maybe to complete.
 * if result is empty a Flowable will not emit any events , neither onNext, nor onError.
 *
 * @param <T>
 */

public class ResponseMaybeListener<T> extends DisposableMaybeObserver<T> {
    private BaseView mBaseView;
    private boolean mDisplayError;
    private boolean mDisplayLoading;

    public ResponseMaybeListener(BaseView mBaseView, boolean mDisplayError, boolean mDisplayLoading) {
        this.mBaseView = mBaseView;
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
    public void onSuccess(T response) {
        LogHelper.log("MaybeResponse: ", "RESPONSE: " + response);
        if (response == null) {
            onError(new NullPointerException());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            handleErrorMessage(e);
        } else {
            if (mBaseView != null) {
                //mBaseView.displayError(ErrorsLabels.getBadRequestError(), null);
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
        if (responseBody != null) {
            try {
                ErrorResponse errorResponse = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                if (mBaseView != null) {
                   /* if (errorResponse.getError() != null) {
                        String errorMessageConstant = errorResponse.getError().getMessage();
                        if (errorMessageConstant.equals(ErrorConstants.NO_USER_PRESENT) ||
                                errorMessageConstant.equals(ErrorConstants.INVALID_TOKEN)) {
                            mBaseView.noUserErrorLogout();
                        } else if (mDisplayError)
                            mBaseView.displayError(ErrorsLabels.getErrorMessageByKey(errorMessageConstant), null);
                    } else if (errorResponse.getFieldValidationErrors() != null) {
                        for (FieldValidationError error : errorResponse.getFieldValidationErrors())
                            mBaseView.displayError(ErrorsLabels.getErrorMessageByKey(error.getMessages().get(0)), error.getField().get(0));
                    }*/
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (mBaseView != null) {
                //    mBaseView.displayError(ErrorsLabels.getBadRequestError(), null);
            }
        }
    }
}
